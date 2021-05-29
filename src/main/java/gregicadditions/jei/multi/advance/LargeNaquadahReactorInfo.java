package gregicadditions.jei.multi.advance;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;


public class LargeNaquadahReactorInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_NAQUADAH_REACTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder.aisle("CCCCC", "CCCCC", "CCECC", "CCCCC", "CCCCC");
        for (int num = 0; num < 10; num++) {
            builder.aisle("CCCCC", "CYYYC", "CYTYF", "CYYYC", "CCCCC");
        }
        builder.aisle("CCCCC", "CCMCC", "CCSCC", "CCCCC", "CCCCC")
                .where('S', GATileEntities.LARGE_NAQUADAH_REACTOR, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('C', GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.NAQUADRIA))
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.EAST)
                .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[8], EnumFacing.NORTH)
                .where('Y', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL))
                .where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_UV));
        return Collections.singletonList(builder.build());

    }

    @Override
    public String[] getDescription() {
        return new String[]{"Temporary Placeholder"};
    }
}
