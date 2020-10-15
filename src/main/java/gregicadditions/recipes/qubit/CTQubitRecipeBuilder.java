package gregicadditions.recipes.qubit;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregtech.api.recipes.CountableIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.stream.Collectors;

@ZenClass("mods.gtadditions.recipe.QubitRecipeBuilder")
@ZenRegister
public class CTQubitRecipeBuilder {

    private final QubitRecipeBuilder backingBuilder;

    public CTQubitRecipeBuilder(QubitRecipeBuilder backingBuilder) {
        this.backingBuilder = backingBuilder;
    }

    @ZenMethod
    public CTQubitRecipeBuilder duration(int duration) {
        this.backingBuilder.duration(duration);
        return this;
    }

    @ZenMethod
    public CTQubitRecipeBuilder EUt(int EUt) {
        this.backingBuilder.EUt(EUt);
        return this;
    }

    @ZenMethod
    public CTQubitRecipeBuilder hidden() {
        this.backingBuilder.hidden();
        return this;
    }

    @ZenMethod
    public CTQubitRecipeBuilder inputs(IIngredient... ingredients) {
        this.backingBuilder.inputsIngredients(Arrays.stream(ingredients)
                .map(s -> new CountableIngredient(new CraftTweakerIngredientWrapper(s), s.getAmount()))
                .collect(Collectors.toList()));
        return this;
    }

    @ZenMethod
    public CTQubitRecipeBuilder notConsumable(IIngredient ingredient) {
        this.backingBuilder.notConsumable(new CraftTweakerIngredientWrapper(ingredient));
        return this;
    }

    //note that fluid input predicates are not supported
    @ZenMethod
    public CTQubitRecipeBuilder fluidInputs(ILiquidStack... ingredients) {
        this.backingBuilder.fluidInputs(Arrays.stream(ingredients)
                .map(CraftTweakerMC::getLiquidStack)
                .collect(Collectors.toList()));
        return this;
    }

    @ZenMethod
    public CTQubitRecipeBuilder property(String key, int value) {
        boolean applied = this.backingBuilder.applyProperty(key, value);
        if (!applied) {
            throw new IllegalArgumentException("Property " +
                    key + " cannot be applied to recipe type " +
                    backingBuilder.getClass().getSimpleName());
        }
        return this;
    }

    @ZenMethod
    public CTQubitRecipeBuilder property(String key, IItemStack item) {
        boolean applied = this.backingBuilder.applyProperty(key, CraftTweakerMC.getItemStack(item));
        if (!applied) {
            throw new IllegalArgumentException("Property " +
                    key + " cannot be applied to recipe type " +
                    backingBuilder.getClass().getSimpleName() + " for Item " + CraftTweakerMC.getItemStack(item).getDisplayName());
        }
        return this;
    }

    @ZenMethod
    public void buildAndRegister() {
        this.backingBuilder.buildAndRegister();
    }

    @ZenMethod
    @Override
    public String toString() {
        return this.backingBuilder.toString();
    }

    public static class CraftTweakerIngredientWrapper extends Ingredient {

        private final IIngredient ingredient;

        public CraftTweakerIngredientWrapper(IIngredient ingredient) {
            super(ingredient.getItems().stream()
                    .map(CraftTweakerMC::getItemStack)
                    .toArray(ItemStack[]::new));
            this.ingredient = ingredient;
        }

        @Override
        public boolean apply(@Nullable ItemStack itemStack) {
            itemStack = itemStack.copy();
            //because CT is dump enough to compare stack sizes by default...
            itemStack.setCount(ingredient.getAmount());
            return ingredient.matches(CraftTweakerMC.getIItemStack(itemStack));
        }
    }

}
