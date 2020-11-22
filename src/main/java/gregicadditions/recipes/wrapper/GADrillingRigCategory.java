package gregicadditions.recipes.wrapper;

import gregicadditions.Gregicality;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;

public class GADrillingRigCategory implements IRecipeCategory<GADrillingRigRecipeWrapper> {

    private final IDrawable background;

    public GADrillingRigCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(176, 110);
    }

    @Override
    public String getUid() {
        return Gregicality.MODID + ":drilling_rig";
    }

    @Override
    public String getTitle() {
        return "drilling_rig";
    }

    @Override
    public String getModName() {
        return Gregicality.MODID;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, GADrillingRigRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, false, 0, 0, 16, 16,
                1000, false, null);
        recipeLayout.getFluidStacks().set(ingredients);
    }
}
