package gregicadditions.recipes.multiinput.impl;

@FunctionalInterface
public interface IMultiInputStats {

    /**
     * A FunctionalInterface used to determine how much of a material to consume
     * on each recipe run, determined by a base amount, recipe tier, and ingredient tier.
     *
     * @param baseAmount     The amount of material set to consume in the Recipe.
     * @param recipeTier     The tier of the recipe. Must be within [0, {@link gregicadditions.GAValues}.V.length]
     * @param ingredientTier The tier of the ingredient. In other words, the ingredient's position in the list.
     * @return An int, being the amount of material to consume.<br>
     *         For ItemStacks, it is in items.<br>
     *         For FluidStacks, it is in millibuckets.
     */
    int apply(int baseAmount, int recipeTier, int ingredientTier);
}
