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
public class QubitConsumerRecipeBuilder extends RecipeBuilder<QubitConsumerRecipeBuilder> {

    private int qubit;

    public QubitConsumerRecipeBuilder() {
    }

    public QubitConsumerRecipeBuilder(Recipe recipe, RecipeMap<QubitConsumerRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.qubit = recipe.getIntegerProperty("qubitConsume");
    }

    public QubitConsumerRecipeBuilder(RecipeBuilder<QubitConsumerRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public QubitConsumerRecipeBuilder copy() {
        return new QubitConsumerRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("qubit")) {
            this.qubit(((Number) value).intValue());
            return true;
        }
        return true;
    }

    public QubitConsumerRecipeBuilder qubit(int qubit) {
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
                        ImmutableMap.of("qubitConsume", qubit),
                        duration, EUt, hidden));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("qubitConsume", qubit)
                .toString();
    }


}
