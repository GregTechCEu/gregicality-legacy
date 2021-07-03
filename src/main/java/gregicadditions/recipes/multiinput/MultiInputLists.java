package gregicadditions.recipes.multiinput;

import gregicadditions.recipes.multiinput.impl.IMultiInputStats;
import gregicadditions.recipes.multiinput.impl.MultiInputFluid;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.Materials.*;

public class MultiInputLists {

    /**
     * Example of P-Dopants using the default "stats" for determining
     * the amount of fluid to consume in a recipe.
     */
    public static final MultiInputFluid P_DOPANT = new MultiInputFluid(
            Boron,
            Aluminium,
            Gallium,
            Indium,
            Thallium,
            Nihonium
    );

    /**
     * Example of N-Dopants using a custom "stats" to determine
     * how much fluid is consumed per recipe.
     * This is an example where it is overriding the default behavior, but
     * it is still using the default behavior, just as an example.<br><br>
     *
     * This can also be done by creating a class which overrides
     * {@link IMultiInputStats} and passing an instance of it here,
     * if this method seems too confusing or unclear.
     */
    public static final MultiInputFluid N_DOPANT = new MultiInputFluid(
            Phosphorus,
            Arsenic,
            Antimony,
            Bismuth,
            Moscovium
    ).setStats((baseAmount, recipeTier, ingredientTier) -> {
        int tierDiff = recipeTier - ingredientTier;
        if (tierDiff == 0) return baseAmount;
        return tierDiff < 0 ? baseAmount / (2 *Math.abs(tierDiff)) : baseAmount * (2 * tierDiff);
    });
}
