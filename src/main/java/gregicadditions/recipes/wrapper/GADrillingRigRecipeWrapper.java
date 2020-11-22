package gregicadditions.recipes.wrapper;

import gregicadditions.worldgen.PumpjackHandler;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

public class GADrillingRigRecipeWrapper implements IRecipeWrapper {

    private final PumpjackHandler.ReservoirType reservoirType;
    private final int weight;


    public GADrillingRigRecipeWrapper(PumpjackHandler.ReservoirType reservoirType, int weight) {
        this.reservoirType = reservoirType;
        this.weight = weight;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setOutput(FluidStack.class, new FluidStack(reservoirType.getFluid(), 1000));
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.dimension_whitelist", reservoirType.dimensionWhitelist.toString()), 0, 18, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.dimension_blacklist", reservoirType.dimensionBlacklist.toString()), 0, 27, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.biome_whitelist", reservoirType.biomeWhitelist.toString()), 0, 36, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.biome_blacklist", reservoirType.biomeBlacklist.toString()), 0, 45, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.min_size", reservoirType.minSize), 0, 54, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.max_size", reservoirType.maxSize), 0, 63, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.replenish_rate", reservoirType.replenishRate), 0, 72, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.weight", weight), 0, 81, 0x111111);

    }
}
