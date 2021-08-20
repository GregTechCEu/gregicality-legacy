package gregicadditions.capabilities;

import gregtech.api.recipes.RecipeMap;

public interface IMultiRecipe {

    RecipeMap<?>[] getRecipeMaps();
    int getRecipeMapIndex();
}
