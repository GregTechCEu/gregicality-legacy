package gregicadditions.machines.multi;

import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.material.Materials.*;

public class MetaTileEntityElectricImplosion extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityElectricImplosion(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.ELECTRIC_IMPLOSION_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityElectricImplosion(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "AAA", "AAA", "AAA", "CCC", "CCC", "XXX")
                .aisle("XXX", "CFC", "CFC", "AFA", "AFA", "AFA", "CFC", "CFC", "XSX")
                .aisle("XXX", "CCC", "CCC", "AAA", "AAA", "AAA", "CCC", "CCC", "XXX")
                .where('A', statePredicate(casingPredicate2()))
                .where('S', selfPredicate())
                .where('F', statePredicate(ferritePredicate()))
                .where('X', statePredicate(casingPredicate()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', statePredicate(coilPredicate()))
                .build();
    }

    // TODO Change this
    private static IBlockState coilPredicate() {
        return GAMetaBlocks.getMetalCasingBlockState(Invar);
    }

    private static IBlockState casingPredicate() {
        return GAMetaBlocks.getMetalCasingBlockState(Steel);
    }

    private static IBlockState casingPredicate2() {
        return GAMetaBlocks.getMetalCasingBlockState(Americium);
    }

    private static IBlockState ferritePredicate() {
        return MetaBlocks.COMPRESSED.get(NickelZincFerrite).getStateFromMeta(0); // TODO is "0" correct?
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Steel);
    }
}
