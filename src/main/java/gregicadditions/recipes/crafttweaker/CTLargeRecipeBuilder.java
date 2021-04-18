package gregicadditions.recipes.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregicadditions.recipes.map.LargeRecipeBuilder;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.crafttweaker.CTRecipeBuilder;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.stream.Collectors;

@ZenClass("mods.gtadditions.recipe.LargeRecipeBuilder")
@ZenRegister
@SuppressWarnings("unused")
public class CTLargeRecipeBuilder {

    private final LargeRecipeBuilder largeRecipeBuilder;


    public CTLargeRecipeBuilder(LargeRecipeBuilder backingBuilder) {
        this.largeRecipeBuilder = backingBuilder;
    }

    @ZenMethod
    public CTLargeRecipeBuilder dupeForSmall() {
        this.largeRecipeBuilder.dupeForSmall();
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder duration(int duration) {
        this.largeRecipeBuilder.duration(duration);
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder EUt(int EUt) {
        this.largeRecipeBuilder.EUt(EUt);
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder hidden() {
        this.largeRecipeBuilder.hidden();
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder inputs(IIngredient... ingredients) {
        this.largeRecipeBuilder.inputsIngredients(Arrays.stream(ingredients).map((s) ->
                new CountableIngredient(new CTRecipeBuilder.CraftTweakerIngredientWrapper(s), s.getAmount())).collect(Collectors.toList()));
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder notConsumable(IIngredient ingredient) {
        this.largeRecipeBuilder.notConsumable(new CTRecipeBuilder.CraftTweakerIngredientWrapper(ingredient));
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder fluidInputs(ILiquidStack... ingredients) {
        this.largeRecipeBuilder.fluidInputs(Arrays.stream(ingredients).map(CraftTweakerMC::getLiquidStack).collect(Collectors.toList()));
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder outputs(IItemStack... ingredients) {
        this.largeRecipeBuilder.outputs(Arrays.stream(ingredients).map(CraftTweakerMC::getItemStack).collect(Collectors.toList()));
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder chancedOutput(IItemStack outputStack, int chanceValue, int tierChanceBoost) {
        this.largeRecipeBuilder.chancedOutput(CraftTweakerMC.getItemStack(outputStack), chanceValue, tierChanceBoost);
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder fluidOutputs(ILiquidStack... ingredients) {
        this.largeRecipeBuilder.fluidOutputs(Arrays.stream(ingredients).map(CraftTweakerMC::getLiquidStack).collect(Collectors.toList()));
        return this;
    }

    @ZenMethod
    public CTLargeRecipeBuilder property(String key, int value) {
        boolean applied = this.largeRecipeBuilder.applyProperty(key, value);
        if (!applied) {
            throw new IllegalArgumentException("Property " + key + " cannot be applied to recipe type " + this.largeRecipeBuilder.getClass().getSimpleName());
        } else {
            return this;
        }
    }

    @ZenMethod
    public CTLargeRecipeBuilder property(String key, IItemStack item) {
        boolean applied = this.largeRecipeBuilder.applyProperty(key, CraftTweakerMC.getItemStack(item));
        if (!applied) {
            throw new IllegalArgumentException("Property " + key + " cannot be applied to recipe type "
                    + this.largeRecipeBuilder.getClass().getSimpleName() + " for Item " + CraftTweakerMC.getItemStack(item).getDisplayName());
        } else {
            return this;
        }
    }

    @ZenMethod
    public void buildAndRegister() {
        this.largeRecipeBuilder.buildAndRegister();
    }

    @ZenMethod
    public String toString() {
        return this.largeRecipeBuilder.toString();
    }
}
