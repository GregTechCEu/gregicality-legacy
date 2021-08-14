package gregicadditions.recipes.helper;

import com.google.common.base.Preconditions;
import forestry.core.fluids.Fluids;
import gregicadditions.GAValues;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipe;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.*;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

/**
 * Some Helper Methods for Recipe Addition and Removal
 */
public class HelperMethods {

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
     * - The MetaTileEntity input can be an Array or a List.
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

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(List<T> metaTileEntities, Object... recipe) {
        for (T mte : metaTileEntities) {
            ModHandler.addShapedRecipe(String.format("ga_%s", mte.getMetaName()), mte.getStackForm(),
                    prepareRecipe(mte.getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    private static Object[] prepareRecipe(int tier, Object... recipe) {
        //Don't assume that the recipes are all 3x3. Some recipes are 1x3 shaped, which will fail if the 3x3 assumption is made
        for (int i = 0; i < recipe.length; i++) {
            if (recipe[i] instanceof GACraftingComponents) {
                recipe[i] = ((GACraftingComponents) recipe[i]).getIngredient(tier);
            }
        }
        return recipe;
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
        RecipeMaps.COMBUSTION_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
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

    public static void registerHotCoolantTurbineFuel(FluidStack input, Material output, int duration, int tier) {
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
        private Material material;
        private Fluids fluids;

        public GenericFluid(Fluids fluids) {
            this.fluids = fluids;
        }

        public GenericFluid(Material material) {
            Preconditions.checkArgument(material.hasFluid());
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
