package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class TileEntityLargeLaserEngraver extends LargeSimpleRecipeMapMultiblockController {

    public TileEntityLargeLaserEngraver(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.LARGE_ENGRAVER_RECIPES, GAConfig.multis.largeEngraver.euPercentage, GAConfig.multis.largeEngraver.durationPercentage, GAConfig.multis.largeEngraver.chancedBoostPercentage, GAConfig.multis.largeEngraver.stack);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeLaserEngraver(metaTileEntityId);
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS,
            MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY};


    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX","XXX","#T#")
                .aisle("XCX", "G#G","XEX","#T#")
                .aisle("XCX", "G#G","XEX","#T#")
                .aisle("XCX", "G#G","XEX","#T#")
                .aisle("XXX", "XSX","XXX","#T#")
                .setAmountAtLeast('L', 30)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .where('M', motorPredicate())
                .where('P', pistonPredicate())
                .where('C', conveyorPredicate())
                .where('E', emitterPredicate())
                .where('T', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING)))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.IRIDIUM_GLASS)))
                .build();
    }

    private IBlockState getCasingState() {
        return GAMetaBlocks.MUTLIBLOCK_CASING2.getState(GAMultiblockCasing2.CasingType.LASER_ENGRAVER);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.LASER_ENGRAVER;
    }

}
