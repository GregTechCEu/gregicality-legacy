package gregicadditions.recipes.nuclear;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GTHotCoolantRecipeWrapper implements IRecipeWrapper {

    public final HotCoolantRecipe recipe;

    public GTHotCoolantRecipeWrapper(HotCoolantRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, recipe.getRecipeFluid());
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getOutputFluid());
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        int duration = recipe.getDuration();
        long voltage = recipe.getMinVoltage();
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.hot_coolant", recipe.getRecipeFluid().getFluid().getTemperature() - 273), 0, 60, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.total", voltage * duration), 0, 70, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.eu_inverted", voltage), 0, 80, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.duration", duration / 20.0), 0, 90, 0x111111);
    }
}
