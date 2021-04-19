package gregicadditions.recipes.nuclear;

import gregicadditions.Gregicality;
import gregtech.api.gui.GuiTextures;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

@MethodsReturnNonnullByDefault
public class HotCoolantRecipeMapCategory implements IRecipeCategory<GTHotCoolantRecipeWrapper> {

    private final HotCoolantRecipeMap recipeMap;
    private final IDrawable background;

    public HotCoolantRecipeMapCategory(HotCoolantRecipeMap recipeMap, IGuiHelper helper) {
        this.recipeMap = recipeMap;
        this.background = helper.createBlankDrawable(176, 110);
    }

    @Override
    public String getUid() {
        return Gregicality.MODID + ":" + recipeMap.getUnlocalizedName();
    }

    @Override
    public String getTitle() {
        return recipeMap.getLocalizedName();
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
    public void setRecipe(IRecipeLayout recipeLayout, GTHotCoolantRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 52, 24, 16, 16,
                recipeWrapper.recipe.getRecipeFluid().amount, false, null);
        recipeLayout.getFluidStacks().init(1, false, 104, 24, 16, 16,
                recipeWrapper.recipe.getRecipeFluid().amount, false, null);
        recipeLayout.getFluidStacks().set(ingredients);

    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {
        GuiTextures.PROGRESS_BAR_ARROW.drawSubArea(77, 22, 20, 20, 0.0, 0.0, 1.0, 0.5);
        GuiTextures.FLUID_SLOT.draw(51, 23, 18, 18);
        GuiTextures.FLUID_SLOT.draw(103, 23, 18, 18);
    }
}
