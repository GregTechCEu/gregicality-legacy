package gregicadditions.recipes.qubit;

import com.google.common.collect.ImmutableMap;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.ValidationResult;
import mcp.MethodsReturnNonnullByDefault;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @see Recipe
 */

@MethodsReturnNonnullByDefault
public class QubitProducerRecipeBuilder extends RecipeBuilder<QubitProducerRecipeBuilder> {

    private int qubit;

    public QubitProducerRecipeBuilder() {
    }

    public QubitProducerRecipeBuilder(Recipe recipe, RecipeMap<QubitProducerRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.qubit = recipe.getIntegerProperty("qubitProduce");
    }

    public QubitProducerRecipeBuilder(RecipeBuilder<QubitProducerRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public QubitProducerRecipeBuilder copy() {
        return new QubitProducerRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("qubit")) {
            this.qubit(((Number) value).intValue());
            return true;
        }
        return true;
    }

    public QubitProducerRecipeBuilder qubit(int qubit) {
        if (qubit <= 0) {
            GTLog.logger.error("qubit cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.qubit = qubit;
        return this;
    }

    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        ImmutableMap.of("qubitProduce", qubit),
                        duration, EUt, hidden));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("qubitProduce", qubit)
                .toString();
    }


}
