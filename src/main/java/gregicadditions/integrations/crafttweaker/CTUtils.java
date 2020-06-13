package gregicadditions.integrations.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregicadditions.Gregicality;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

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
    public static void removeRecipesByOutPut(RecipeMap<?> recipeMap, IItemStack[] outputs, ILiquidStack[] fluidOutputs) {
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
                        if (output.isItemEqual(itemStack) && output.getCount() == itemStack.getCount()) {
                            matches = false;
                            break;
                        }
                    }
                }
                for (FluidStack fluidOutput : ((Recipe) recipe).getFluidOutputs()) {
                    for (FluidStack fluidStack : mcFluidOutputs) {
                        if (!fluidOutput.isFluidStackIdentical(fluidStack)) {
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
}
