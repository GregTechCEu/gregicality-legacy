package gregicadditions.recipes;

import com.google.common.collect.ImmutableMap;
import gregicadditions.GAMaterials;
import gregicadditions.utils.GALog;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class AdvFusionRecipeBuilder extends RecipeBuilder<AdvFusionRecipeBuilder> {

    private int coilTier;
    private long euStart = 0;
    public static List<FluidStack> coolants = new ArrayList<>();

    public AdvFusionRecipeBuilder(Recipe recipe, RecipeMap<AdvFusionRecipeBuilder> recipeMap, int coilTier) {
        super(recipe, recipeMap);
        this.coilTier = coilTier;
    }

    public AdvFusionRecipeBuilder() {
    }

    static {
        coolants.add(Materials.Water.getFluid(10000));
        coolants.add(GAMaterials.Cryotheum.getFluid(1000));
        coolants.add(GAMaterials.SupercooledCryotheum.getFluid(100));
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
        } else if (key.equals("euStart")) {
            this.euStart(((Number) value).intValue());
        }
        return false;
    }

    public AdvFusionRecipeBuilder coilTier(int coilTier) {
        if (coilTier <= 0) {
            GALog.logger.error("Advanced Fusion Coil tier cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.coilTier = coilTier;
        this.euStart =  this.euStart == 0 ? (int) (16 * 10000000 * Math.pow(2, coilTier)) : this.euStart;
        return this;
    }

    public AdvFusionRecipeBuilder euStart(long eu) {
        if (eu <= 0) {
            GALog.logger.error("Advanced Fusion EU to start cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.euStart = eu;
        return this;
    }

    @Override
    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        ImmutableMap.of("coil_tier", coilTier, "eu_to_start", euStart),
                        duration, EUt, hidden));
    }

    @Override
    public void buildAndRegister() {
        if (fluidInputs.size() == 2) {
            for (FluidStack fluidStack : coolants) {
                recipeMap.addRecipe(this.copy().fluidInputs(fluidStack).build());
            }
        } else {
            recipeMap.addRecipe(build());
            GALog.logger.info("Potentially incorrect Advanced Fusion Recipe with fluid inputs not equal to 2 added.");
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("coil_tier", coilTier)
                .append("eu_to_start", euStart)
                .toString();
    }
}
