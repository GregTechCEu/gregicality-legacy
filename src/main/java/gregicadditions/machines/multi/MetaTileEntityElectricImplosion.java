package gregicadditions.machines.multi;

import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.item.metal.NuclearCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregicadditions.item.GAMetaBlocks.NUCLEAR_CASING;
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
                .aisle("XXX", "AAA", "AAA", "AAA", "XXX")
                .aisle("XXX", "A#A", "A#A", "A#A", "XXX")
                .aisle("XSX", "AAA", "AAA", "AAA", "XXX")
                .where('A', statePredicate(casingPredicate2()))
                .where('S', selfPredicate())
                .where('#', isAirPredicate())
                .where('X', statePredicate(casingPredicate()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .build();
    }

    private static IBlockState casingPredicate() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    private static IBlockState casingPredicate2() {
        return NUCLEAR_CASING.getState(NuclearCasing.CasingType.AMERICIUM);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Steel);
    }
}
