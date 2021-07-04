package gregicadditions.recipes.multiinput;

import com.google.common.collect.ImmutableMap;
import gregicadditions.GAValues;
import gregicadditions.recipes.multiinput.impl.MultiInputFluid;
import gregicadditions.recipes.multiinput.impl.IMultiInputItem;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
import gregtech.api.util.ValidationResult;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class MultiInputRecipeBuilder extends RecipeBuilder<MultiInputRecipeBuilder> {

    private final List<IMultiInputItem> multiInputItems = new ArrayList<>();
    private final List<MultiInputFluid> multiInputFluids = new ArrayList<>();
    private int recipeTier = 0;

    public MultiInputRecipeBuilder() {
    }

    public MultiInputRecipeBuilder(RecipeBuilder<MultiInputRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    public MultiInputRecipeBuilder(Recipe recipe, RecipeMap<MultiInputRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    @Override
    public MultiInputRecipeBuilder copy() {
        return new MultiInputRecipeBuilder(this);
    }

    /**
     * Overloaded method of fluidInputs that accepts a MultiInputFluid.<br><br>
     *
     * Should be used like in this example:
     * <pre>
     *     .fluidInputs(N_DOPANTS.getIngredient(1000))
     * </pre>
     * where "N_DOPANTS" is a MultiInputFluid and "1000" is the base amount of the
     * fluid in mb to be consumed in the recipe.
     *
     * @param fluid The MultiInput fluid list to use for this recipe.
     * @return this
     */
    public MultiInputRecipeBuilder fluidInputs(MultiInputFluid fluid) {
        if (fluid.getBaseAmount() <= 0) {
            GTLog.logger.error("Fluid input amount for MultiInputs must be greater than zero", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.multiInputFluids.add(fluid);
        return this;
    }

    /**
     * Overloaded method of inputs that accepts a MultiInputItem.<br><br>
     *
     * Should be used like in this example:
     * <pre>
     *     .inputs(N_DOPANTS.getIngredient(10))
     * </pre>
     * where "N_DOPANTS" is a MultiInputItem and "10" is the base number of
     * items to be consumed in the recipe.
     *
     * @param item The MultiInput Item list to use for this recipe.
     * @return this
     */
    public MultiInputRecipeBuilder inputs(IMultiInputItem item) {
        if (item.getBaseAmount() <= 0) {
            GTLog.logger.error("Item input amount for MultiInputs must be greater than zero", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.multiInputItems.add(item);
        return this;
    }

    public MultiInputRecipeBuilder fluidInputs(MultiInputFluid ... fluids) {
        for (MultiInputFluid fluid : fluids) {
            if (fluid.getBaseAmount() <= 0) {
                GTLog.logger.error("Fluid input amount for MultiInputs must be greater than zero", new IllegalArgumentException());
                recipeStatus = EnumValidationResult.INVALID;
            }
            this.multiInputFluids.add(fluid);
        }

        return this;
    }

    public MultiInputRecipeBuilder fluidInputs(FluidStack recipeInputFluid, MultiInputFluid ... recipeMultiInputFluids) {
        for (MultiInputFluid fluid : recipeMultiInputFluids) {
            if (fluid.getBaseAmount() <= 0) {
                GTLog.logger.error("Fluid input amount for MultiInputs must be greater than zero", new IllegalArgumentException());
                recipeStatus = EnumValidationResult.INVALID;
            }
            this.multiInputFluids.add(fluid);
        }

        this.fluidInputs.add(recipeInputFluid);

        return this;
    }


    /**
     * The tier of this recipe, used to calculate how much of a material is consumed on craft.<br>
     * Default: 0
     *
     * @param recipeTier The tier of the recipe, between [0, {@link GAValues}.V.length - 1] inclusive.
     * @return this
     */
    public MultiInputRecipeBuilder setRecipeTier(int recipeTier) {
        if (!GTUtility.isBetweenInclusive(0, GAValues.V.length - 1, recipeTier)) {
            GTLog.logger.error("Tier should be between 0 and " + (GAValues.V.length - 1) + "inclusive", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.recipeTier = recipeTier;
        return this;
    }

    @Override
    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new RecipeMultiInput(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        ImmutableMap.of(), duration, EUt, hidden, multiInputItems, multiInputFluids, recipeTier));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("multiInputItems", multiInputItems)
                .append("multiInputFluids", multiInputFluids)
                .append("recipeTier", recipeTier)
                .toString();
    }
}
