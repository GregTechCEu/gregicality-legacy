package gregicadditions.recipes.map;

import com.google.common.collect.ImmutableMap;
import gregicadditions.utils.GALog;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import mcp.MethodsReturnNonnullByDefault;
import org.apache.commons.lang3.builder.ToStringBuilder;

@MethodsReturnNonnullByDefault
public class NuclearReactorBuilder extends RecipeBuilder<NuclearReactorBuilder> {

    private int baseHeatProduction;

    public NuclearReactorBuilder() {
    }

    public NuclearReactorBuilder(Recipe recipe, RecipeMap<NuclearReactorBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.baseHeatProduction = recipe.getIntegerProperty("baseHeatProduction");
    }

    public NuclearReactorBuilder(RecipeBuilder<NuclearReactorBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public NuclearReactorBuilder copy() {
        return new NuclearReactorBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("temperature")) {
            this.baseHeatProduction(((Number) value).intValue());
            return true;
        }
        return true;
    }

    public NuclearReactorBuilder baseHeatProduction(int baseHeatProduction) {
        if (baseHeatProduction <= 0) {
            GALog.logger.error("Base heat production cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.baseHeatProduction = baseHeatProduction;
        return this;
    }

    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        ImmutableMap.of("base_heat_production", baseHeatProduction),
                        duration, EUt, hidden));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("base_heat_production", baseHeatProduction)
                .toString();
    }
}
