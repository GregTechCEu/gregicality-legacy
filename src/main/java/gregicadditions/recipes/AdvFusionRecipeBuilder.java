package gregicadditions.recipes;

import com.google.common.collect.ImmutableMap;
import gregicadditions.utils.GALog;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdvFusionRecipeBuilder extends RecipeBuilder<AdvFusionRecipeBuilder> {

    private int coilTier;

    public AdvFusionRecipeBuilder(Recipe recipe, RecipeMap<AdvFusionRecipeBuilder> recipeMap, int coilTier) {
        super(recipe, recipeMap);
        this.coilTier = coilTier;
    }

    public AdvFusionRecipeBuilder() {
    }

    public AdvFusionRecipeBuilder(RecipeBuilder<AdvFusionRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public AdvFusionRecipeBuilder copy() {
        return new AdvFusionRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("coilTier")) {
            this.coilTier(((Number) value).intValue());
            return true;
        }
        return false;
    }

    public AdvFusionRecipeBuilder coilTier(int coilTier) {
        if (coilTier <= 0) {
            GALog.logger.error("Advanced Fusion Coil tier cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.coilTier = coilTier;
        return this;
    }

    @Override
    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        ImmutableMap.of("coil_tier", coilTier),
                        duration, EUt, hidden));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("coil_tier", coilTier)
                .toString();
    }
}
