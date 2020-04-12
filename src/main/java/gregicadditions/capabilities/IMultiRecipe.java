package gregicadditions.capabilities;

import gregtech.api.recipes.RecipeMap;

public interface IMultiRecipe {

    RecipeMap<?>[] getRecipes();
    int getCurrentRecipe();
}
