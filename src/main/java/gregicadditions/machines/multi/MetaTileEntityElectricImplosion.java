package gregicadditions.machines.multi;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.metal.NuclearCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static gregicadditions.item.GAMetaBlocks.NUCLEAR_CASING;

public class MetaTileEntityElectricImplosion extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
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
                .aisle("XXX", "AAA", "AAA", "AAA", "XXX")
                .aisle("XXX", "A#A", "A#A", "A#A", "XXX")
                .aisle("XSX", "AAA", "AAA", "AAA", "XXX")
                .where('A', statePredicate(getCasingState2()))
                .where('S', selfPredicate())
                .where('#', isAirPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .build();
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    protected IBlockState getCasingState2() {
        return NUCLEAR_CASING.getState(NuclearCasing.CasingType.AMERICIUM);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }
}
