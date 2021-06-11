package gregicadditions.recipes.impl;

import com.google.common.collect.ImmutableMap;
import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.ValidationResult;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @see Recipe
 */

@MethodsReturnNonnullByDefault
public class QubitConsumerRecipeBuilder extends RecipeBuilder<QubitConsumerRecipeBuilder> {

    private static final List<FluidStack> SOLDER_FLUIDS = new ArrayList<>();

    static {
        for (String fluid : GAConfig.Misc.solderingFluidList) {
            String[] fluidSplit = fluid.split(":");
            int amount = GAUtility.setBetweenInclusive(Integer.parseInt(fluidSplit[1]), 1, 64000);

            FluidStack fluidStack = FluidRegistry.getFluidStack(fluidSplit[0], amount);
            if (fluidStack != null) SOLDER_FLUIDS.add(fluidStack);
        }
    }

    private int qubit;
    private int solderMultiplier = 1;
    private boolean noSolder = false;

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

    public QubitConsumerRecipeBuilder solderMultiplier(int multiplier) {
        this.solderMultiplier = multiplier;
        return this;
    }

    public QubitConsumerRecipeBuilder noSolder() {
        this.noSolder = true;
        return this;
    }

    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        ImmutableMap.of("qubitConsume", qubit),
                        duration, EUt, hidden));
    }

    @Override
    public void buildAndRegister() {
        if (!noSolder && fluidInputs.isEmpty()) {
            for (FluidStack fluidStack : SOLDER_FLUIDS) {
                recipeMap.addRecipe(this.copy()
                        .fluidInputs(new FluidStack(fluidStack.getFluid(), Math.min(64000, fluidStack.amount * solderMultiplier)))
                        .build());
            }
        } else {
            recipeMap.addRecipe(build());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("qubitConsume", qubit)
                .toString();
    }
}
