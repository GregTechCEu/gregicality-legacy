package gregicadditions.recipes;

import com.google.common.collect.ImmutableMap;
import gregicadditions.utils.GALog;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.Materials.*;

@MethodsReturnNonnullByDefault
public class AdvFusionRecipeBuilder extends RecipeBuilder<AdvFusionRecipeBuilder> {

    private int coilTier;
    private long euStart;
    public static Map<FluidStack, Fluid> COOLANTS = new HashMap<>();
    private int euReturn;


    public AdvFusionRecipeBuilder(Recipe recipe, RecipeMap<AdvFusionRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.coilTier = recipe.getIntegerProperty("coil_tier");
        this.euStart = recipe.getProperty("eu_to_start");
        this.euReturn = recipe.getIntegerProperty("eu_return");
    }

    public AdvFusionRecipeBuilder() {
    }


    static {
        COOLANTS.put(Steam.getFluid(570), SupercriticalSteam.fluid);
        COOLANTS.put(Deuterium.getFluid(240), SupercriticalDeuterium.fluid);
        COOLANTS.put(SodiumPotassiumAlloy.getFluid(120), SupercriticalSodiumPotassiumAlloy.fluid);
        COOLANTS.put(Sodium.getFluid(100), SupercriticalSodium.fluid);
        COOLANTS.put(FLiNaK.getFluid(50), SupercriticalFLiNaK.fluid);
        COOLANTS.put(FLiBe.getFluid(55), SupercriticalFLiBe.fluid);
        COOLANTS.put(LeadBismuthEutectic.getFluid(60), SupercriticalLeadBismuthEutectic.fluid);
    }

    public AdvFusionRecipeBuilder(RecipeBuilder<AdvFusionRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    public AdvFusionRecipeBuilder(RecipeBuilder<AdvFusionRecipeBuilder> recipeBuilder, int coilTier, long euStart, int euReturn) {
        super(recipeBuilder);
        this.coilTier = coilTier;
        this.euStart = euStart;
        this.euReturn = euReturn;
    }

    @Override
    public AdvFusionRecipeBuilder copy() {
        return new AdvFusionRecipeBuilder(this, this.coilTier, this.euStart, this.euReturn);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        switch (key) {
            case "coilTier":
                this.coilTier(((Number) value).intValue());
                return true;
            case "eu_to_start":
                this.euStart(((Number) value).longValue());
                return true;
            case "euReturn":
                this.euReturn(((Number) value).intValue());
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

    public AdvFusionRecipeBuilder euReturn(int percentage) {
        if (percentage < 0) {
            GALog.logger.error("Advanced Fusion EU return cannot be less than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.euReturn = percentage;
        return this;
    }

    @Override
    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        ImmutableMap.of("coil_tier", this.coilTier, "eu_to_start", this.euStart, "eu_return", this.euReturn),
                        duration, EUt, hidden));
    }

    @Override
    public void buildAndRegister() {
        if (fluidInputs.size() == 2) {
            if (euReturn > 0) {
                long eu = (euStart + ((long) EUt) * duration) * euReturn / 100;
                for (FluidStack fluidStack : COOLANTS.keySet()) {
                    FluidStack fluidInput = fluidStack.copy();
                    int amount = Math.max((int) ((eu / (2048 * 10000)) * fluidInput.amount), 0);
                    fluidInput.amount = amount;
                    FluidStack fluidOutput = new FluidStack(COOLANTS.get(fluidStack), amount);
                    recipeMap.addRecipe(this.copy()
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .build());
                }
            } else {
                recipeMap.addRecipe(this.build());
            }
        } else {
            recipeMap.addRecipe(this.build());
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
