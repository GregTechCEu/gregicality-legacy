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

public class AdditionMethods {
// TODO COMMENTS
    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, ItemStack... itemInputs) {
        removeRecipesByInputs(map, itemInputs, new FluidStack[0]);
    }

    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, FluidStack... fluidInputs) {
        removeRecipesByInputs(map, new ItemStack[0], fluidInputs);
    }

    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, ItemStack[] itemInputs, FluidStack[] fluidInputs) {

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
    }
    public static <R extends RecipeBuilder<R>> void removeAllRecipes(RecipeMap<R> map) {

        List<Recipe> recipes = new ArrayList<>(map.getRecipeList());

        for (Recipe r : recipes)
            map.removeRecipe(r);

        if(GAConfig.Misc.enableRecipeRemovalLogging)
            GALog.logger.info("Removed all recipes for Recipe Map: {}", map.unlocalizedName);
    }

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(T[] metaTileEntities, Object... recipe) {
        registerMachineRecipe(0, metaTileEntities, recipe);
    }

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(int ingredientOffset, T[] metaTileEntities, Object... recipe) {
        for (T metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaName()), metaTileEntity.getStackForm(),
                        prepareRecipe(metaTileEntity.getTier() + ingredientOffset, Arrays.copyOf(recipe, recipe.length)));
        }
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

    public static void removeTieredRecipeByName(String recipeName, int startTier, int endTier) {
        // Purposefully uses GTValues for having MAX be index 9 for GTCE recipe removal
        for (int i = startTier; i <= endTier; i++)
            removeRecipeByName(String.format("%s%s", recipeName, GTValues.VN[i].toLowerCase()));
    }

    public static void removeRecipeByName(String recipeName) {
        removeRecipeByName(new ResourceLocation(recipeName));
    }

    public static void removeRecipeByName(ResourceLocation recipe) {
        if (GAConfig.Misc.enableRecipeRemovalLogging) {
            String recipeName = recipe.toString();
            if (ForgeRegistries.RECIPES.containsKey(recipe))
                GALog.logger.info("Removed Recipe with Name: {}", recipeName);
            else GALog.logger.warn("Failed to Remove Recipe with Name: {}", recipeName);
        }
        ModHandler.removeRecipeByName(recipe);
    }

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

    public static boolean isSingleIngredient(IRecipe recipe) {

        if (recipe.getIngredients().size() == 0)
            return false;

        if (recipe.getIngredients().get(0).getMatchingStacks().length == 0)
            return false;

        for (int i = 1; i < recipe.getIngredients().size(); i++) {
            if (recipe.getIngredients().get(i).getMatchingStacks().length == 0
                    || !recipe.getIngredients().get(0).getMatchingStacks()[0]
                       .isItemEqual(recipe.getIngredients().get(i).getMatchingStacks()[0])) {
                return false;
            }
        }
        return true;
    }

    public static boolean outputIsNot(IRecipe recipe, Block... blocks) {
        for (Block block : blocks) {
            if (Block.getBlockFromItem(recipe.getRecipeOutput().getItem()) == block)
                return false;
        }
        return true;
    }

    public static boolean doesStackStartWithOre(ItemStack stack, String ore) {
        for (int id : OreDictionary.getOreIDs(stack)) {
            if (OreDictionary.getOreName(id).startsWith(ore)) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getTopLeft(IRecipe recipe) {
        try {
            return recipe.getIngredients().get(0).getMatchingStacks()[0];
        } catch (Exception e) {
            return null;
        }
    }

    public static String titleCase(String input) {
        return input.substring(0, 1).toUpperCase(Locale.US) + input.substring(1);
    }

    public static ItemStack getFilledCell(Fluid fluid, int count) {
        ItemStack fluidCell = MetaItems.FLUID_CELL.getStackForm().copy();
        IFluidHandlerItem fluidHandlerItem = fluidCell.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        try {
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

    public static boolean hasPrefix(ItemStack stack, String prefix, String... ignore) {
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

    public static class GenericFluid {

        private FluidMaterial material;
        private Fluids fluids;

        public GenericFluid(FluidMaterial material) {
            this.material = material;
        }

        public GenericFluid(Fluids fluids) {
            this.fluids = fluids;
        }

        public FluidStack getFluid(int amount) {
            if (material == null)
                return fluids.getFluid(amount);
            else
                return material.getFluid(amount);
        }
    }
}
