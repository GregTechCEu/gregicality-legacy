package gregicadditions.recipes.helper;

import forestry.core.fluids.Fluids;
import gregicadditions.GAValues;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.multiblockpart.GAMetaTileEntityEnergyHatch;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipe;
import gregicadditions.utils.GALog;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.*;
import gregtech.api.recipes.recipes.FuelRecipe;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdditionMethods {
// TODO COMMENTS
    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, ItemStack... itemInputs) {
        List<ItemStack> inputs = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (ItemStack s : itemInputs) {
            inputs.add(s);
            //if(GAConfig.Misc.enableRecipeRemovalLogging) {
                names.add(s.getDisplayName() + " x " + s.getCount());
            //}
        }

        if(map.removeRecipe(map.findRecipe(Long.MAX_VALUE, inputs, Collections.emptyList(), Integer.MAX_VALUE))/* && GAConfig.Misc.enableRecipeRemovalLogging*/) {
            GALog.logger.info("Removed Recipe for Item Input(s): " + names);
        }
        else {
            //if(GAConfig.Misc.enableRecipeRemovalLogging) {
                GALog.logger.warn("Failed to Remove Recipe for Item Input(s): " + names);
            //}
        }
    }

    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, FluidStack... fluidInputs) {
        List<FluidStack> inputs = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (FluidStack s : fluidInputs) {
            inputs.add(s);
            //if(GAConfig.Misc.enableRecipeRemovalLogging) {
                names.add(s.getFluid().getName() + " x " + s.amount);
            //}
        }

        if(map.removeRecipe(map.findRecipe(Long.MAX_VALUE, Collections.emptyList(), inputs, Integer.MAX_VALUE))/* && GAConfig.Misc.enableRecipeRemovalLogging*/) {
            GALog.logger.info("Removed Recipe for Fluid Input(s): " + names);
        }
        else {
            //if(GAConfig.Misc.enableRecipeRemovalLogging) {
                GALog.logger.warn("Failed to Remove Recipe for Fluid Input(s): " + names);
            //}
        }
    }

    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, ItemStack[] itemInputs, FluidStack[] fluidInputs) {
        List<ItemStack> itemIn = new ArrayList<>();
        List<String> fluidNames = new ArrayList<>();
        List<String> itemNames = new ArrayList<>();
        for (ItemStack s : itemInputs) {
            itemIn.add(s);
            //if(GAConfig.Misc.enableRecipeRemovalLogging) {
                itemNames.add(s.getDisplayName() + " x " + s.getCount());
            //}
        }

        List<FluidStack> fluidIn = new ArrayList<>();
        for (FluidStack s : fluidInputs) {
            fluidIn.add(s);
            //if(GAConfig.Misc.enableRecipeRemovalLogging) {
                fluidNames.add(s.getFluid().getName() + " x " + s.amount);
            //}
        }

        if(map.removeRecipe(map.findRecipe(Long.MAX_VALUE, itemIn, fluidIn, Integer.MAX_VALUE))/* && GAConfig.Misc.enableRecipeRemovalLogging*/) {
            GALog.logger.info("Removed Recipe for inputs: Items: " + itemNames + " Fluids: " + fluidNames);
        }
        else {
            //if(GAConfig.Misc.enableRecipeRemovalLogging) {
                GALog.logger.info("Failed to Remove Recipe for inputs: Items: " + itemNames + " Fluids: " + fluidNames);
            //}
        }
    }
    public static <R extends RecipeBuilder<R>> void removeAllRecipes(RecipeMap<R> map) {

        List<Recipe> recipes = new ArrayList<>(map.getRecipeList());

        for (Recipe r : recipes)
            map.removeRecipe(r);

        //if(GAConfig.Misc.enableRecipeRemovalLogging) {
            GALog.logger.info("Removed all recipes for Recipe Map: " + map.unlocalizedName);
        //}
    }

    public static <T extends MetaTileEntity & ITieredMetaTileEntity> void registerMachineRecipe(T[] metaTileEntities, Object... recipe) {
        for (T metaTileEntity : metaTileEntities) {
            if (metaTileEntity != null)
                ModHandler.addShapedRecipe(String.format("ga_%s", metaTileEntity.getMetaName()), metaTileEntity.getStackForm(),
                        prepareRecipe(metaTileEntity.getTier(), Arrays.copyOf(recipe, recipe.length)));
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
            ModHandler.addShapedRecipe(String.format("ga%s", mte.getMetaName()), mte.getStackForm(),
                    prepareRecipe(mte.getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    public static void registerMachineRecipes(List<GAMetaTileEntityEnergyHatch> metaTileEntities, Object... recipe) {
        for (GAMetaTileEntityEnergyHatch mte : metaTileEntities) {
            ModHandler.addShapedRecipe(String.format("ga%s", mte.getMetaName()), mte.getStackForm(),
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
