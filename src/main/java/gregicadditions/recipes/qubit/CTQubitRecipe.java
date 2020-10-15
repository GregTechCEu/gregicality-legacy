package gregicadditions.recipes.qubit;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.liquid.MCLiquidStack;
import gregtech.api.recipes.crafttweaker.InputIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;
import java.util.stream.Collectors;

@ZenClass("mods.gtadditions.recipe.QubitRecipe")
@ZenRegister
public class CTQubitRecipe {

    private final QubitRecipeMap recipeMap;
    private final QubitRecipe backingRecipe;

    public CTQubitRecipe(QubitRecipeMap recipeMap, QubitRecipe backingRecipe) {
        this.recipeMap = recipeMap;
        this.backingRecipe = backingRecipe;
    }

    @ZenGetter("inputs")
    public List<InputIngredient> getInputs() {
        return this.backingRecipe.getInputs().stream()
                .map(InputIngredient::new)
                .collect(Collectors.toList());
    }

    @ZenGetter("fluidInputs")
    public List<ILiquidStack> getFluidInputs() {
        return this.backingRecipe.getFluidInputs().stream()
                .map(MCLiquidStack::new)
                .collect(Collectors.toList());
    }

    @ZenMethod
    public boolean hasInputFluid(ILiquidStack liquidStack) {
        return this.backingRecipe.hasInputFluid(CraftTweakerMC.getLiquidStack(liquidStack));
    }

    @ZenGetter("duration")
    public int getDuration() {
        return this.backingRecipe.getDuration();
    }

    @ZenGetter("EUt")
    public int getEUt() {
        return this.backingRecipe.getEUt();
    }

    @ZenGetter("hidden")
    public boolean isHidden() {
        return this.backingRecipe.isHidden();
    }


    @ZenMethod
    public boolean remove() {
        return this.recipeMap.removeRecipe(this.backingRecipe);
    }

}
