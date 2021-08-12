package gregicadditions.recipes.impl;

import gregicadditions.recipes.compat.crafttweaker.CTLargeRecipeBuilder;
import gregtech.api.recipes.RecipeMap;

import java.util.ArrayList;
import java.util.List;

public class LargeRecipeMap extends RecipeMap<LargeRecipeBuilder> {

    private static final List<LargeRecipeMap> LARGE_RECIPE_MAPS = new ArrayList<>();

    public LargeRecipeMap(String unlocalizedName, int inputs, int outputs, int fluidInputs, int fluidOutputs, LargeRecipeBuilder defaultRecipe, boolean isHidden) {
        super(unlocalizedName, inputs, outputs, fluidInputs, fluidOutputs, defaultRecipe, isHidden);
        LARGE_RECIPE_MAPS.add(this);
    }

    public CTLargeRecipeBuilder ctLargeRecipeBuilder() {
        return new CTLargeRecipeBuilder(recipeBuilder());
    }

    public static LargeRecipeMap getLargeMapByName(String unlocalizedName) {
        return LARGE_RECIPE_MAPS.stream().filter((map) -> map.unlocalizedName.equals(unlocalizedName)).findFirst().orElse(null);
    }
}
