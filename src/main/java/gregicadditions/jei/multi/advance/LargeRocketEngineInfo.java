package gregicadditions.jei.multi.advance;

import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class LargeRocketEngineInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_ROCKET_ENGINE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder.aisle("CCC", "CEC", "CCC");
        for (int num = 0; num < 8; num++) {
            builder.aisle("CCC", "C#F", "CAC");
        }
        builder.aisle("CMC", "CSC", "CCC")
                .where('S', GATileEntities.LARGE_ROCKET_ENGINE, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('C', METAL_CASING_1.getState(MetalCasing1.CasingType.NITINOL_60))
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.EAST)
                .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[4], EnumFacing.NORTH)
                .where('A', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING))
                .where('#', Blocks.AIR.getDefaultState());
        return Collections.singletonList(builder.build());

    }

    @Override
    public String[] getDescription() {
        return new String[]{"Temporary Placeholder"};
    }
}
