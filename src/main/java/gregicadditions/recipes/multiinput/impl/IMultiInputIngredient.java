package gregicadditions.recipes.multiinput.impl;

public interface IMultiInputIngredient<T> {

    /**
     * Used in Recipe Registration to set the proper base amount of this MultiInput
     * object in a specific recipe. This type of Ingredient is only accepted in machines
     * using {@link gregicadditions.recipes.multiinput.MultiInputRecipeBuilder}.<br><br>
     *
     * Code example:
     * <pre>
     *     CVD_RECIPES.recipeBuilder()
     *         .input(dust, Carbon, 4)
     *         .fluidInputs(N_DOPENT.getIngredient(1000))
     *         .fluidOutputs(CarbonDioxide.getFluid(1000))
     *         .EUt(30)
     *         .duration(30)
     *         .buildAndRegister();
     * </pre>
     * In this example, the Ingredient "N_DOPENT" is being set as a fluidInput, and having
     * its baseAmount set as 1000, or 1 bucket since it is a fluid.
     *
     * @param baseAmount The base amount for a recipe.
     * @return this
     */
    IMultiInputIngredient<T> getIngredient(int baseAmount);

    /**
     * Used to override the default behavior of fluid consumption. By default, it will
     * consume 1 bucket when "on tier," and divide by 2 for each tier if above tier,
     * multiply by 2 for each tier if below tier.<br><br>
     *
     * An example of how to override these stats:
     * <pre>
     *     public static final MultiInputFluid N_DOPANT = new MultiInputFluid(
     *         Phosphorus,
     *         Arsenic,
     *         Antimony
     *     ).setStats((baseAmount, recipeTier, ingredientTier) -> {
     *         return baseAmount * ingredientTier + 1;
     *     });
     * </pre>
     * In this case, any recipe that consumes an "N_DOPANT" will consume the base amount
     * times the ingredient tier plus one, being
     * - Phosphorus: 0
     * - Arsenic: 1
     * - Antimony: 2
     *
     * @param stats The expression to use for calculating fluid consumption.
     * @return this, so that this can be called on Object declaration.
     */
    IMultiInputIngredient<T> setStats(IMultiInputStats stats); // TODO

    /**
     * Used to acquire the exact stack (Item or Fluid) that will be consumed if this recipe succeeds.
     *
     * @param inputStack The stack (Item or Fluid) before having its amount adjusted.
     * @param recipeTier The tier of the current recipe.
     * @return The stack after having its amount adjusted.
     */
    T getRecipeInput(T inputStack, int recipeTier);

    /**
     * Used to acquire the base amount of the material to consume on one recipe process.
     * This value is used in calculations in {@link IMultiInputStats}.
     *
     * @return The base amount of material consumed.
     */
    int getBaseAmount();

    /**
     * Used to determine if the provided inputStack is contained within
     * this MultiInput's registry.
     *
     * @param inputStack The stack (either ItemStack or FluidStack) to test for inclusion.
     * @return True if present, false otherwise.
     */
    boolean matches(T inputStack);

    /**
     * Returns a copy of the current IMultiInputIngredient.
     * Only intended for internal use.
     *
     * @return A copy of this.
     */
    IMultiInputIngredient<T> copy();
}
