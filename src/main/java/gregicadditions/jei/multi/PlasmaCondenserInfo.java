package gregicadditions.jei.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

public class PlasmaCondenserInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.PLASMA_CONDENSER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder.aisle("XEX", "XXX", "XXX", "XXX", "XXX")
                .aisle("FXf", "XMX", "X#X", "XPX", "XXX")
                .aisle("ISi", "XXX", "XXX", "XXX", "XXX")
                .where('S', GATileEntities.PLASMA_CONDENSER, EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.ENRICHED_NAQUADAH_ALLOY))
                .where('#', Blocks.AIR.getDefaultState())
                .where('P', GAMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_UHV))
                .where('M', GAMetaBlocks.MOTOR_CASING.getState(MotorCasing.CasingType.MOTOR_UHV))
                .where('E', GATileEntities.ENERGY_INPUT[0], EnumFacing.NORTH)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.WEST)
                .where('i', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.WEST);

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }
}
