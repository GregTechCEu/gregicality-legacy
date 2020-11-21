package gregicadditions.machines.multi.simple;

import gregicadditions.client.ClientHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntityLargeLaserEngraver extends LargeSimpleRecipeMapMultiblockController {
    /**
     * Create large multiblock machine for simple machine.
     * <p>
     * Percentage : 80 => 0.8 mean lower
     * Percentage : 120 => 1.2 mean higher
     *
     * @param metaTileEntityId
     * @param recipeMap
     * @param EUtPercentage      should be between 0 ~ Integer.MAX_VALUE, Default should be 100
     * @param durationPercentage should be between 0 ~ Integer.MAX_VALUE, Default should be 100
     * @param chancePercentage   should be between 0 ~ Integer.MAX_VALUE, Default should be 100
     * @param stack              should be between 0 ~ Integer.MAX_VALUE, Default should be 1
     */
    public TileEntityLargeLaserEngraver(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int EUtPercentage, int durationPercentage, int chancePercentage, int stack) {
        super(metaTileEntityId, recipeMap, EUtPercentage, durationPercentage, chancePercentage, stack);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return null;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.LASER_ENGRAVER;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return null;
    }
}
