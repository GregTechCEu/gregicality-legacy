package gregicadditions.recipes;

import gregicadditions.recipes.crafttweaker.CTLargeRecipeBuilder;
import gregicadditions.recipes.map.LargeRecipeBuilder;
import gregtech.api.recipes.RecipeMap;

import java.util.ArrayList;
import java.util.List;

public class LargeRecipeMap extends RecipeMap<LargeRecipeBuilder> {

    private static final List<LargeRecipeMap> LARGE_RECIPE_MAPS = new ArrayList<>();

    public LargeRecipeMap(String unlocalizedName, int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, LargeRecipeBuilder defaultRecipe) {
        super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, defaultRecipe);
        LARGE_RECIPE_MAPS.add(this);
    }

    public CTLargeRecipeBuilder ctLargeRecipeBuilder() {
        return new CTLargeRecipeBuilder(recipeBuilder());
    }

    public static LargeRecipeMap getLargeMapByName(String unlocalizedName) {
        return LARGE_RECIPE_MAPS.stream().filter((map) -> map.unlocalizedName.equals(unlocalizedName)).findFirst().orElse(null);
    }
}
