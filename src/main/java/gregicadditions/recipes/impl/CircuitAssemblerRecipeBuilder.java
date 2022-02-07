package gregicadditions.recipes.impl;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.ValidationResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class CircuitAssemblerRecipeBuilder extends RecipeBuilder<CircuitAssemblerRecipeBuilder> {
    private static final List<FluidStack> SOLDER_FLUIDS = new ArrayList<>();

    static {
        for (String fluid : GAConfig.Misc.solderingFluidList) {
            String[] fluidSplit = fluid.split(":");
            int amount = GAUtility.setBetweenInclusive(Integer.parseInt(fluidSplit[1]), 1, 64000);

            FluidStack fluidStack = FluidRegistry.getFluidStack(fluidSplit[0], amount);
            if (fluidStack != null) SOLDER_FLUIDS.add(fluidStack);
        }
    }

    private int solderMultiplier = 1;
    private boolean noSolder = false;

    public CircuitAssemblerRecipeBuilder() {
    }

    public CircuitAssemblerRecipeBuilder(Recipe recipe, RecipeMap<CircuitAssemblerRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public CircuitAssemblerRecipeBuilder(RecipeBuilder<CircuitAssemblerRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    public CircuitAssemblerRecipeBuilder solderMultiplier(int multiplier) {
        this.solderMultiplier = multiplier;
        return this;
    }

    public CircuitAssemblerRecipeBuilder noSolder() {
        this.noSolder = true;
        return this;
    }

    @Override
    public CircuitAssemblerRecipeBuilder copy() {
        return new CircuitAssemblerRecipeBuilder(this);
    }

    @Override
    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs, duration, EUt, hidden));
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
}
