package gregicadditions.recipes.wrapper;

import gregicadditions.Gregicality;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GADrillingRigCategory implements IRecipeCategory<GADrillingRigRecipeWrapper> {

    private final IDrawable background;
    private final IDrawable listBackground;

    public GADrillingRigCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(176, 70);
        listBackground = helper.drawableBuilder(new ResourceLocation("gregtech", "textures/gui/base/bordered_background.png"), 0, 0, background.getWidth() / 2 - 4, 23)
            .setTextureSize(background.getWidth() / 2 - 4, 23)
            .build();
    }

    @Override
    public String getUid() {
        return Gregicality.MODID + ":drilling_rig";
    }

    @Override
    public String getTitle() {
        return I18n.format("recipemap.drilling_rig.name");
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
    public void drawExtras(Minecraft minecraft) {
        listBackground.draw(minecraft, 0, 18);
        listBackground.draw(minecraft, background.getWidth() / 2, 18);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, GADrillingRigRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, false, 0, 0, 16, 16,
                1000, false, null);
        recipeLayout.getFluidStacks().set(ingredients);
    }
}
