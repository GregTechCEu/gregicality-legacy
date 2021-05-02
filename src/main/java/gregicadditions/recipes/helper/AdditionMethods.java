package gregicadditions.recipes.helper;

import gregicadditions.GAConfig;
import gregicadditions.machines.GATileEntities;
import gregicadditions.utils.GALog;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdditionMethods {

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

    private static Object[] prepareRecipe(int tier, Object... recipe) {
        for (int i = 3; i < recipe.length; i++) {
            if (recipe[i] instanceof GACraftingComponents) {
                recipe[i] = ((GACraftingComponents) recipe[i]).getIngredient(tier);
            }
        }
        return recipe;
    }
}
