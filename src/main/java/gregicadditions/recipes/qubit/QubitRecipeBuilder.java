package gregicadditions.recipes.qubit;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ValidationResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

/**
 * @see Recipe
 */

public class QubitRecipeBuilder {

    protected QubitRecipeMap recipeMap;

    protected List<CountableIngredient> inputs;

    protected List<FluidStack> fluidInputs;

    protected int duration, EUt;
    protected long qubit;
    protected boolean hidden = false;

    protected EnumValidationResult recipeStatus = EnumValidationResult.VALID;

    public QubitRecipeBuilder() {
        this.inputs = NonNullList.create();
        this.fluidInputs = new ArrayList<>(0);
    }

    protected QubitRecipeBuilder(QubitRecipe recipe, QubitRecipeMap recipeMap) {
        this.recipeMap = recipeMap;
        this.inputs = NonNullList.create();
        this.inputs.addAll(recipe.getInputs());

        this.fluidInputs = GTUtility.copyFluidList(recipe.getFluidInputs());

        this.duration = recipe.getDuration();
        this.EUt = recipe.getEUt();
        this.qubit = recipe.getMinQubit();
        this.hidden = recipe.isHidden();
    }

    @SuppressWarnings("CopyConstructorMissesField")
    protected QubitRecipeBuilder(QubitRecipeBuilder recipeBuilder) {
        this.recipeMap = recipeBuilder.recipeMap;
        this.inputs = NonNullList.create();
        this.inputs.addAll(recipeBuilder.getInputs());

        this.fluidInputs = GTUtility.copyFluidList(recipeBuilder.getFluidInputs());
        this.duration = recipeBuilder.duration;
        this.EUt = recipeBuilder.EUt;
        this.qubit = recipeBuilder.qubit;
        this.hidden = recipeBuilder.hidden;
    }

    public boolean applyProperty(String key, Object value) {
        return false;
    }

    public boolean applyProperty(String key, ItemStack item) {
        return false;
    }

    public QubitRecipeBuilder inputs(ItemStack... inputs) {
        return inputs(Arrays.asList(inputs));
    }

    public QubitRecipeBuilder inputs(Collection<ItemStack> inputs) {
        if (GTUtility.iterableContains(inputs, stack -> stack == null || stack.isEmpty())) {
            GTLog.logger.error("Input cannot contain null or empty ItemStacks. Inputs: {}", inputs);
            GTLog.logger.error("Stacktrace:", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        inputs.forEach(stack -> {
            if (!(stack == null || stack.isEmpty())) {
                this.inputs.add(CountableIngredient.from(stack));
            }
        });
        return (QubitRecipeBuilder) this;
    }

    public QubitRecipeBuilder input(String oredict, int count) {
        return inputs(CountableIngredient.from(oredict, count));
    }

    public QubitRecipeBuilder input(OrePrefix orePrefix, Material material) {
        return inputs(CountableIngredient.from(orePrefix, material, 1));
    }

    public QubitRecipeBuilder input(OrePrefix orePrefix, Material material, int count) {
        return inputs(CountableIngredient.from(orePrefix, material, count));
    }

    public QubitRecipeBuilder inputs(CountableIngredient... inputs) {
        List<CountableIngredient> ingredients = new ArrayList<>();
        for (CountableIngredient input : inputs) {
            if (input.getCount() < 0) {
                GTLog.logger.error("Count cannot be less than 0. Actual: {}.", input.getCount());
                GTLog.logger.error("Stacktrace:", new IllegalArgumentException());
            } else {
                ingredients.add(input);
            }
        }

        return inputsIngredients(ingredients);
    }

    public QubitRecipeBuilder inputsIngredients(Collection<CountableIngredient> ingredients) {
        this.inputs.addAll(ingredients);
        return this;
    }

    public QubitRecipeBuilder notConsumable(ItemStack itemStack) {
        return inputs(CountableIngredient.from(itemStack, 0));
    }

    public QubitRecipeBuilder notConsumable(OrePrefix prefix, Material material) {
        return input(prefix, material, 0);
    }

    public QubitRecipeBuilder notConsumable(Ingredient ingredient) {
        return inputs(new CountableIngredient(ingredient, 0));
    }

    public QubitRecipeBuilder notConsumable(MetaItem<?>.MetaValueItem item) {
        return inputs(CountableIngredient.from(item.getStackForm(), 0));
    }


    public QubitRecipeBuilder fluidInputs(FluidStack... inputs) {
        return fluidInputs(Arrays.asList(inputs));
    }

    public QubitRecipeBuilder fluidInputs(Collection<FluidStack> inputs) {
        if (inputs.contains(null)) {
            GTLog.logger.error("Fluid input cannot contain null FluidStacks. Inputs: {}", inputs);
            GTLog.logger.error("Stacktrace:", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.fluidInputs.addAll(inputs);
        this.fluidInputs.removeIf(Objects::isNull);
        return this;
    }


    public QubitRecipeBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    public QubitRecipeBuilder EUt(int EUt) {
        this.EUt = EUt;
        return this;
    }

    public QubitRecipeBuilder Qubit(long qubit) {
        this.qubit = qubit;
        return this;
    }

    public QubitRecipeBuilder hidden() {
        this.hidden = true;
        return this;
    }

    public QubitRecipeBuilder setRecipeMap(QubitRecipeMap recipeMap) {
        this.recipeMap = recipeMap;
        return this;
    }

    public QubitRecipeBuilder copy() {
        return new QubitRecipeBuilder(this);
    }

    public ValidationResult<QubitRecipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new QubitRecipe(inputs, fluidInputs, duration, EUt, qubit, hidden));
    }

    protected EnumValidationResult finalizeAndValidate() {
        return validate();
    }


    protected EnumValidationResult validate() {
        if (EUt == 0) {
            GTLog.logger.error("EU/t cannot be equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (duration <= 0) {
            GTLog.logger.error("Duration cannot be less or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (recipeStatus == EnumValidationResult.INVALID) {
            GTLog.logger.error("Invalid recipe, read the errors above: {}", this);
        }
        return recipeStatus;
    }

    public void buildAndRegister() {
        ValidationResult<QubitRecipe> validationResult = build();
        recipeMap.addRecipe(validationResult);
    }

    ///////////////////
    //    Getters    //
    ///////////////////

    public List<CountableIngredient> getInputs() {
        return inputs;
    }

    public List<FluidStack> getFluidInputs() {
        return fluidInputs;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("recipeMap", recipeMap)
                .append("inputs", inputs)
                .append("fluidInputs", fluidInputs)
                .append("duration", duration)
                .append("EUt", EUt)
                .append("Qubit", qubit)
                .append("hidden", hidden)
                .append("recipeStatus", recipeStatus)
                .toString();
    }
}
