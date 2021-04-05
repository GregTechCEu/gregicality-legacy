package gregicadditions.recipes.multiinput.stats;

import gregicadditions.recipes.multiinput.impl.IMultiInputStats;

/**
 * Default class override of {@link IMultiInputStats}
 * used when custom behavior is not specified.
 */
public class DefaultStats implements IMultiInputStats {

    /**
     * Default implementation of {@link IMultiInputStats#apply}.
     *
     * @param baseAmount     The amount of material set to consume in the Recipe.
     * @param recipeTier     The tier of the recipe. Must be within [0, {@link gregicadditions.GAValues}.V.length]
     * @param ingredientTier The tier of the ingredient. In other words, the ingredient's position in the list.
     *
     * @return baseAmount if recipeTier == ingredientTier.<br>
     *         baseAmount / (2 * tierDifference) if ingredientTier is above recipeTier.<br>
     *         baseAmount * (2 * tierDifference) if ingredientTier is below recipeTier.
     */
    @Override
    public int apply(int baseAmount, int recipeTier, int ingredientTier) {
        int tierDiff = recipeTier - ingredientTier;
        if (tierDiff == 0) return baseAmount;
        return tierDiff < 0 ? baseAmount / (2 * Math.abs(tierDiff)) : baseAmount * (2 * tierDiff);
    }
}
