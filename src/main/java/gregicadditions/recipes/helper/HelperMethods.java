package gregicadditions.recipes.helper;

import forestry.core.fluids.Fluids;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.multiblockpart.GAMetaTileEntityEnergyHatch;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipe;
import gregicadditions.utils.GALog;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.*;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

/**
 * Some Helper Methods for Recipe Addition and Removal
 */
public class HelperMethods {

    /**
     * Removes all Recipes matching given inputs and fluid inputs from a given RecipeMap.
     * An example of how to use it:
     *
     * <cr>
     *     removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES,
     *         new ItemStack[]{
     *             OreDictUnifier.get(OrePrefix.dust, Materials.SodiumHydroxide, 3)
     *         },
     *         new FluidStack[]{
     *             Materials.HypochlorousAcid.getFluid(1000),
     *             Materials.AllylChloride.getFluid(1000)
     *         });
     * </cr>
     *
     * This method also has varargs parameter methods for when there is only ItemStack or FluidStack inputs.
     *
     * @param map         The RecipeMap to search over.
     * @param itemInputs  The ItemStack[] containing all Recipe item inputs.
     * @param fluidInputs The FluidStack[] containing all Recipe fluid inputs.
     *
     * @return true if a recipe was removed, false otherwise.
     */
    public static <R extends RecipeBuilder<R>> boolean removeRecipesByInputs(RecipeMap<R> map, ItemStack[] itemInputs, FluidStack[] fluidInputs) {

        List<String> fluidNames = new ArrayList<>();
        List<String> itemNames = new ArrayList<>();

        List<ItemStack> itemIn = new ArrayList<>();
        for (ItemStack s : itemInputs) {
            itemIn.add(s);
            if(GAConfig.Misc.enableRecipeRemovalLogging) {
                itemNames.add(String.format("%s x %d", s.getDisplayName(), s.getCount()));
            }
        }

        List<FluidStack> fluidIn = new ArrayList<>();
        for (FluidStack s : fluidInputs) {
            fluidIn.add(s);
            if(GAConfig.Misc.enableRecipeRemovalLogging) {
                fluidNames.add(String.format("%s x %d", s.getFluid().getName(), s.amount));
            }
        }

        boolean wasRemoved = map.removeRecipe(map.findRecipe(Long.MAX_VALUE, itemIn, fluidIn, Integer.MAX_VALUE));
        if (GAConfig.Misc.enableRecipeRemovalLogging) {
            if (wasRemoved)
                GALog.logger.info("Removed Recipe for inputs: Items: {} Fluids: {}", itemNames, fluidNames);
            else GALog.logger.info("Failed to Remove Recipe for inputs: Items: {} Fluids: {}", itemNames, fluidNames);
        }
        return wasRemoved;
    }

    public static <R extends RecipeBuilder<R>> boolean removeRecipesByInputs(RecipeMap<R> map, ItemStack... itemInputs) {
        return removeRecipesByInputs(map, itemInputs, new FluidStack[0]);
    }

    public static <R extends RecipeBuilder<R>> boolean removeRecipesByInputs(RecipeMap<R> map, FluidStack... fluidInputs) {
        return removeRecipesByInputs(map, new ItemStack[0], fluidInputs);
    }

    /**
     * Removes all Recipes from a given RecipeMap. This method cannot fail at recipe removal, but if called at
     * the wrong time during recipe registration, it may be an incomplete or overly-complete recipe removal.
     * An example of how to use it:
     *
     * <cr>
     *     removeAllRecipes(RecipeMaps.BREWING_RECIPES);
     * </cr>
     *
     * @param map The RecipeMap to clear all recipes from.
     */
    public static <R extends RecipeBuilder<R>> void removeAllRecipes(RecipeMap<R> map) {

        List<Recipe> recipes = new ArrayList<>(map.getRecipeList());

        for (Recipe r : recipes)
            map.removeRecipe(r);

        if(GAConfig.Misc.enableRecipeRemovalLogging)
            GALog.logger.info("Removed all recipes for Recipe Map: {}", map.unlocalizedName);
    }

    /**
     * Tiered Machine Recipe Registration method.
     * An example of how to use it:
     *
     * <cr>
     *     registerMachineRecipe(GATileEntities.DIODES,
     *                 "CCC", "XMX", "CCC",
     *                 'M', HULL,
     *                 'C', CABLE_SINGLE,
     *                 'X', MetaItems.SMALL_COIL);
     * </cr>
     *
     * - The MetaTileEntity input can be an Array, a List, or an Array of {@link GATileEntities.MTE}.
     * - The Recipe Input must be Strings of up to 3 characters.
     * - The inputs must be in pairs of (char, [input]).
     * - Inputs can be a:
     *     - String representing an OreDictionary entry
     *     - ItemStack
     *     - {@link GACraftingComponents} or {@link gregtech.loaders.recipe.CraftingComponent}
     *     - {@link gregtech.api.unification.stack.UnificationEntry}
     *
     * @param ingredientOffset The starting position in the Array or List of MetaTileEntities
     * @param metaTileEntities The group of MetaTileEntities to register the same recipe for.
     * @param recipe           The Recipe Layout, detailed above.
     */
    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(int ingredientOffset, T[] metaTileEntities, Object... recipe) {
        for (T metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaName()), metaTileEntity.getStackForm(),
                        prepareRecipe(metaTileEntity.getTier() + ingredientOffset, Arrays.copyOf(recipe, recipe.length)));
        }
    }

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(T[] metaTileEntities, Object... recipe) {
        registerMachineRecipe(0, metaTileEntities, recipe);
    }

    public static void registerMachineRecipe(GATileEntities.MTE<?>[] metaTileEntities, Object... recipe) {
        for (GATileEntities.MTE<?> metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaTileEntity().getMetaName()), metaTileEntity.getMetaTileEntity().getStackForm(),
                        prepareRecipe(metaTileEntity.getITieredMetaTileEntity().getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(List<T> metaTileEntities, Object... recipe) {
        for (T mte : metaTileEntities) {
            ModHandler.addShapedRecipe(String.format("ga_%s", mte.getMetaName()), mte.getStackForm(),
                    prepareRecipe(mte.getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    // Don't mind the extra "s" on the method name, just Java not recognizing
    // 2 Lists with different generic types as different parameters for overloading.
    public static void registerMachineRecipes(List<GAMetaTileEntityEnergyHatch> metaTileEntities, Object... recipe) {
        for (GAMetaTileEntityEnergyHatch mte : metaTileEntities) {
            ModHandler.addShapedRecipe(String.format("ga_%s", mte.getMetaName()), mte.getStackForm(),
                    prepareRecipe(mte.getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    private static Object[] prepareRecipe(int tier, Object... recipe) {
        for (int i = 3; i < recipe.length; i++) {
            if (recipe[i] instanceof GACraftingComponents) {
                recipe[i] = ((GACraftingComponents) recipe[i]).getIngredient(tier);
            }
        }
        return recipe;
    }

    /**
     * Removes Crafting Table Recipes with a range of names, being {@link GTValues} voltage names.
     * An example of how to use it:
     *
     * <cr>
     *     removeTieredRecipeByName("gregtech:transformer_", EV, UV);
     * </cr>
     *
     * This will remove recipes with names:
     *
     * <cr>
     *     gregtech:transformer_ev
     *     gregtech:transformer_iv
     *     gregtech:transformer_luv
     *     gregtech:transformer_zpm
     *     gregtech:transformer_uv
     * </cr>
     *
     * Note that it uses {@link GTValues} and not {@link GAValues} because GTValues.MAX is a valid index (being 9)
     * for GTCE Machine Recipes.
     *
     * @param recipeName The base name of the Recipes to remove.
     * @param startTier  The starting tier index, inclusive.
     * @param endTier    The ending tier index, inclusive.
     */
    public static void removeTieredRecipeByName(String recipeName, int startTier, int endTier) {
        // Purposefully uses GTValues for having MAX be index 9 for GTCE recipe removal
        for (int i = startTier; i <= endTier; i++)
            removeRecipeByName(String.format("%s%s", recipeName, GTValues.VN[i].toLowerCase()));
    }

    /**
     * Removes a Crafting Table Recipe with the given name.
     *
     * @param recipe The ResourceLocation of the Recipe.
     *               Can also accept a String.
     */
    public static void removeRecipeByName(ResourceLocation recipe) {
        if (GAConfig.Misc.enableRecipeRemovalLogging) {
            String recipeName = recipe.toString();
            if (ForgeRegistries.RECIPES.containsKey(recipe))
                GALog.logger.info("Removed Recipe with Name: {}", recipeName);
            else GALog.logger.warn("Failed to Remove Recipe with Name: {}", recipeName);
        }
        ModHandler.removeRecipeByName(recipe);
    }

    public static void removeRecipeByName(String recipeName) {
        removeRecipeByName(new ResourceLocation(recipeName));
    }

    /**
     * Removes all Crafting Table Recipes with the given output.
     * NOTE: This should be used sparingly, as it is much less
     * efficient than {@link HelperMethods#removeRecipeByName}.
     *
     * @param output The Recipe output to remove recipes from.
     */
    public static void removeCraftingRecipes(ItemStack output) {
        int removedRecipes = ModHandler.removeRecipes(output);

        if (GAConfig.Misc.enableRecipeRemovalLogging) {
            if (removedRecipes != 0)
                GALog.logger.info("Removed {} Recipe(s) with Output: {}", removedRecipes, output.getDisplayName());
            else GALog.logger.warn("Failed to Remove Recipe with Output: {}", output.getDisplayName());
        }
    }

    /**
     * Wrapper method for removing Furnace Smelting Recipes.
     * Wrapped for easy logging info.
     *
     * @param input The input of the Furnace Recipe to remove.
     */
    public static void removeFurnaceRecipe(ItemStack input) {
        boolean wasRemoved = ModHandler.removeFurnaceSmelting(input);
        if (GAConfig.Misc.enableRecipeRemovalLogging) {
            if (wasRemoved)
                GALog.logger.info("Removed Recipe for Input: {}", input.getDisplayName());
            else GALog.logger.warn("Failed to Remove Smelting Recipe for Input: {}", input.getDisplayName());
        }
    }

    /**
     * Tests if a Crafting Table recipe has only one unique Item input.
     *
     * @param recipe The Recipe to check.
     * @return An int, being -1 if multiple input items, or [1, 9] if
     *         only one unique input Item, meaning the number of input items in the recipe.
     */
    public static int getSingleInputCount(IRecipe recipe) {

        int recipeSize = recipe.getIngredients().size();
        ItemStack topLeft = getTopLeft(recipe);

        if (recipe.getRecipeOutput().isEmpty())
            return -1;

        if (topLeft == null)
            return -1;

        if (recipeSize == 0)
            return -1;

        if (recipeSize == 1)
            return 1;

        for (int i = 1; i < recipeSize; i++) {
            ItemStack input = getRecipeInput(recipe, i);
            if (input == null || !topLeft.isItemEqual(input))
                return -1;
        }
        return recipeSize;
    }

    /**
     * Returns the ItemStack at the top-left corner of a Shaped Recipe.
     *
     * @param recipe The Crafting Table Recipe to check.
     *
     * @return The ItemStack at the top left corner, null otherwise.
     *         Behavior with shapeless recipes may be unexpected.
     */
    public static ItemStack getTopLeft(IRecipe recipe) {
        return getRecipeInput(recipe, 0);
    }

    /**
     * Returns the ItemStack at the specified index of the Shaped Crafting Recipe.
     *
     * @param recipe The Recipe to check.
     * @param index  The index of the recipe grid.
     * @return The ItemStack at specified index, or null if none or invalid parameters.
     */
    public static ItemStack getRecipeInput(IRecipe recipe, int index) {

        if (recipe == null)
            return null;

        if (index < 0 || index > 9)
            return null;

        if (recipe.getIngredients().size() <= index)
            return null;

        if (recipe.getIngredients().get(index).getMatchingStacks().length == 0)
            return null;

        return recipe.getIngredients().get(index).getMatchingStacks()[0];
    }

    /**
     * Sets the first character of the String to Uppercase.
     *
     * @param input The String to capitalize.
     *
     * @return input, capitalized.
     */
    public static String titleCase(String input) {
        return input.substring(0, 1).toUpperCase(Locale.US) + input.substring(1);
    }

    /**
     * Creates a Cell ItemStack with a specified fluid and given count.
     * An example of how to use it:
     *
     * <cr>
     *     getFilledCell(Materials.HydrochloricAcid.getMaterialFluid(), 4)
     * </cr>
     *
     * The above will return 4 cells containing HCl.
     *
     * @param fluid The Fluid to fill the cell.
     * @param count The number of cells in the ItemStack.
     *
     * @return The Cell ItemStack.
     */
    public static ItemStack getFilledCell(Fluid fluid, int count) {
        ItemStack fluidCell = MetaItems.FLUID_CELL.getStackForm().copy();
        IFluidHandlerItem fluidHandlerItem = fluidCell.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        try {
            assert fluidHandlerItem != null;
            fluidHandlerItem.fill(new FluidStack(fluid, 1000), true);

        } catch (Exception e) {
            GALog.logger.error("The fluid " + fluid.toString() + " failed to do something with getFilledCell");
            GALog.logger.error(e);
            fluidHandlerItem.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
        }
        fluidCell = fluidHandlerItem.getContainer();
        fluidCell.setCount(count);
        return fluidCell;
    }

    public static ItemStack getFilledCell(Fluid fluid) {
        return getFilledCell(fluid, 1);
    }

    /**
     * Used to test if a given ItemStack has a specified OreDictionary prefix.
     * An example of how to use it:
     *
     * <cr>
     *     assertTrue(
     *         hasPrefix(OreDictUnifier.get(OrePrefix.dust, Materials.Iron), "dust", "dustTiny")
     *     );
     *     assertNotTrue(
     *         hasPrefix(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Iron), "dust", "dustTiny")
     *     );
     * </cr>
     *
     * @param stack  The ItemStack to test.
     * @param prefix The String prefix to the OreDictionary name.
     * @param ignore Strings to ignore prefixes for. For example:
     *
     *               If prefix == "dust", and
     *                  ignore == "dustTiny",
     *               it will ignore OreDictionary entries with the prefix "dustTiny",
     *               but not "dust[something else]".
     *
     * @return true if ItemStack has prefix, false otherwise.
     */
    public static boolean hasOrePrefix(ItemStack stack, String prefix, String... ignore) {
        for (int i : OreDictionary.getOreIDs(stack)) {
            if (OreDictionary.getOreName(i).startsWith(prefix)) {
                boolean valid = true;
                for (String s : ignore) {
                    if (OreDictionary.getOreName(i).startsWith(s)) valid = false;
                }
                if (!valid) continue;
                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////
    //              Fuel Recipe Helpers              //
    ///////////////////////////////////////////////////

    public static void registerPlasmaFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.PLASMA_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerDieselGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.DIESEL_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerSteamGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.STEAM_TURBINE_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerGasGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.GAS_TURBINE_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerSemiFluidGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.SEMI_FLUID_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerNaquadahReactorFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.NAQUADAH_REACTOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerHyperReactorFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.HYPER_REACTOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerRocketFuel(FluidStack fuelStack, int duration, int tier) {
        GARecipeMaps.ROCKET_FUEL_RECIPES.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerHotCoolantTurbineFuel(FluidStack input, FluidMaterial output, int duration, int tier) {
        GARecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(input, duration, GAValues.V[tier], output.getFluid(input.amount)));
    }

    public static void registerQubitGeneratorFuel(OrePrefix prefix, Material material, int duration, int tier) {
        GARecipeMaps.SIMPLE_QUBIT_GENERATOR.recipeBuilder()
                .qubit(1)
                .input(prefix, material)
                .duration(duration)
                .EUt(GAValues.V[tier])
                .buildAndRegister();
    }

    /**
     * Small Helper class useful for Forestry compatibility. If we are generating a recipe with either a
     * GT Fluid or a Forestry Fluid, we can use this class to make it easier to code.
     * An example of how to use it:
     *
     * <cr>
     *     boolean forestry = Loader.isModLoaded(MODID_FR) && GAConfig.Misc.ForestryIntegration;
     *
     *     GenericFluid Ethanol = forestry ?
     *             new GenericFluid(Fluids.BIO_ETHANOL) :
     *             new GenericFluid(Materials.Ethanol);
     *
     *     registerDieselGeneratorFuel(Ethanol.getFluid(2), 12, LV);
     * </cr>
     */
    public static class GenericFluid {

        // Only one of these must be initialized for any given instance of GenericFluid
        private FluidMaterial material;
        private Fluids fluids;

        public GenericFluid(Fluids fluids) {
            this.fluids = fluids;
        }

        public GenericFluid(FluidMaterial material) {
            this.material = material;
        }

        /**
         * Create a FluidStack of the Material.
         *
         * @return A FluidStack with specified amount
         */
        public FluidStack getFluid(int amount) {
            if (material == null)
                return fluids.getFluid(amount);
            else
                return material.getFluid(amount);
        }
    }
}
