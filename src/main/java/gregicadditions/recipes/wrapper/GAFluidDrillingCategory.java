package gregicadditions.recipes.wrapper;

import gregicadditions.Gregicality;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;

public class GAFluidDrillingCategory implements IRecipeCategory<GAFluidDrillingRecipeWrapper> {

    private final IDrawable background;

    public GAFluidDrillingCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(176, 110);
    }

    @Override
    public String getUid() {
        return Gregicality.MODID + ":fluid_drilling";
    }

    @Override
    public String getTitle() {
        return "fluid_drilling";
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
    public void setRecipe(IRecipeLayout recipeLayout, GAFluidDrillingRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 52, 24, 16, 16,
                1000, false, null);
    }
}
