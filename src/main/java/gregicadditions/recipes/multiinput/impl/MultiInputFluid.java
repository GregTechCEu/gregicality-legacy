package gregicadditions.recipes.multiinput.impl;

import gregicadditions.recipes.multiinput.stats.DefaultStats;
import gregtech.api.unification.material.type.FluidMaterial;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;
import java.util.stream.Collectors;

public class MultiInputFluid implements IMultiInputIngredient<FluidStack> {

    /**
     * Base amount of a dopant to consume, before applying any of the
     * dopant-specific stats to it. Can be overridden when called in
     * a recipe with {@link this.getIngredient()}
     */
    private static final int BASE_AMOUNT = 1000;

    /**
     * Override of {@link this.BASE_AMOUNT},
     * set by {@link this.getIngredient()}
     */
    private int baseAmount;

    /**
     * List of Fluids allowed in this MultiInput object.
     */
    private final List<FluidStack> fluids;

    /**
     * Total count of FluidStacks in this MultiInput object.
     */
    private final int fluidCount;

    /**
     * The FunctionalInterface to use for calculating the amount
     * of fluid consumed on each recipe.
     */
    private IMultiInputStats stats;

    public MultiInputFluid(FluidMaterial... materials) {
        this(BASE_AMOUNT, materials);
    }

    public MultiInputFluid(int baseAmount, FluidMaterial... materials) {
        this.baseAmount = baseAmount;
        this.fluidCount = materials.length;
        this.stats = new DefaultStats();

        this.fluids = Arrays.stream(materials)
                            .map(mat -> mat.getFluid(1))
                            .collect(Collectors.toList());
    }

    /**
     * Basic constructor only ever called by {@link this.copy()}
     */
    private MultiInputFluid(List<FluidStack> fluids,
                            int fluidCount,
                            int baseAmount,
                            IMultiInputStats stats) {

        this.fluids = new ArrayList<>(fluids);
        this.fluidCount = fluidCount;
        this.baseAmount = baseAmount;
        this.stats = stats;
    }

    @Override
    public MultiInputFluid getIngredient(int baseAmount) {
        MultiInputFluid clone = this.copy();
        clone.baseAmount = baseAmount;
        return clone;
    }

    @Override
    public MultiInputFluid setStats(IMultiInputStats stats) {
        this.stats = stats;
        return this;
    }

    @Override
    public MultiInputFluid copy() {
        return new MultiInputFluid(this.fluids,
                                   this.fluidCount,
                                   this.baseAmount,
                                   this.stats);
    }

    @Override
    public FluidStack getRecipeInput(FluidStack inputStack, int recipeTier) {
        FluidStack returnStack = inputStack.copy();
        returnStack.amount = stats.apply(baseAmount,
                                         recipeTier,
                                         fluids.indexOf(inputStack));
        return returnStack;
    }

    @Override
    public int getBaseAmount() {
        return this.baseAmount;
    }

    @Override
    public boolean matches(FluidStack inputStack) {
        return fluids.contains(inputStack);
    }
}
