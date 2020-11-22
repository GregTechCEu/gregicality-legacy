package gregicadditions.recipes.wrapper;

import gregicadditions.worldgen.PumpjackHandler;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

public class GAFluidDrillingRecipeWrapper implements IRecipeWrapper {

    private final PumpjackHandler.ReservoirType reservoirType;
    private final int biome;


    public GAFluidDrillingRecipeWrapper(PumpjackHandler.ReservoirType reservoirType, int biome) {
        this.reservoirType = reservoirType;
        this.biome = biome;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setOutput(FluidStack.class, new FluidStack(reservoirType.getFluid(), 1000));
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.fluid_drilling.fluid", I18n.format(reservoirType.getFluid().getUnlocalizedName())), 0, 20, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.fluid_drilling.dimension"), 0, 40, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.fluid_drilling.biome"), 0, 60, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.fluid_drilling.min_size", reservoirType.minSize), 0, 80, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.fluid_drilling.max_size", reservoirType.maxSize), 0, 100, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.fluid_drilling.replenish_rate", reservoirType.replenishRate), 0, 120, 0x111111);

    }
}
