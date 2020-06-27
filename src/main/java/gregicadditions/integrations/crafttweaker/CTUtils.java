package gregicadditions.integrations.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.block.IBlock;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregicadditions.Gregicality;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.api.util.GTLog;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@ZenClass("mods.gtadditions.recipe.Utils")
@ZenRegister
public class CTUtils {


    @ZenMethod("removeRecipeByOutput")
    @Optional.Method(modid = Gregicality.MODID)
    public static void removeRecipeByOutput(RecipeMap<?> recipeMap, IItemStack[] outputs, ILiquidStack[] fluidOutputs, boolean useAmounts) {
        List<Recipe> recipesToRemove = new ArrayList<>();
        boolean matches;
        List<ItemStack> mcItemOutputs = outputs == null ? Collections.emptyList() :
                Arrays.stream(outputs)
                        .map(CraftTweakerMC::getItemStack)
                        .collect(Collectors.toList());

        List<FluidStack> mcFluidOutputs = fluidOutputs == null ? Collections.emptyList() :
                Arrays.stream(fluidOutputs)
                        .map(CraftTweakerMC::getLiquidStack)
                        .collect(Collectors.toList());

        for (Object recipe : recipeMap.getRecipeList()) {
            matches = true;
            if (recipe instanceof Recipe) {
                for (ItemStack output : ((Recipe) recipe).getOutputs()) {
                    for (ItemStack itemStack : mcItemOutputs) {
                        if (!output.isItemEqual(itemStack)) {
                            if (useAmounts && output.getCount() != itemStack.getCount()) {
                                matches = false;
                                break;
                            } else if (!useAmounts) {
                                matches = false;
                                break;
                            }
                        }
                    }
                }
                for (FluidStack fluidOutput : ((Recipe) recipe).getFluidOutputs()) {
                    for (FluidStack fluidStack : mcFluidOutputs) {
                        if (useAmounts && !fluidOutput.isFluidStackIdentical(fluidStack)) {
                            matches = false;
                            break;
                        } else if (!useAmounts && !fluidOutput.isFluidEqual(fluidStack)) {
                            matches = false;
                            break;
                        }
                    }
                }
                if (matches) {
                    recipesToRemove.add((Recipe) recipe);
                }
            }
        }
        for (Recipe recipe : recipesToRemove) {
            recipeMap.removeRecipe(recipe);
        }
    }




    @ZenMethod("registerStoneType")
    @Optional.Method(modid = Gregicality.MODID)
    public static void registerStoneType(String className, String fieldName, int id, String tool, String name)
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> c = Class.forName(className);
        Field f = c.getDeclaredField(fieldName);
        Block block = (Block) f.get(f);
        StoneType stoneType = new StoneType(id, name, new ResourceLocation(Gregicality.MODID, "blocks/" + name),
                SoundType.STONE, OrePrefix.ore, Materials.Stone, tool, 0, block::getDefaultState, state -> state.getBlock() == block);
        GTLog.logger.info("Registered stone type with name " + name + " and ID " + id);
    }
    
}
