package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeThermalCentrifuge;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeThermalCentrifugeInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_THERMAL_CENTRIFUGE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            if (coilType.equals(BlockWireCoil.CoilType.SUPERCONDUCTOR) || coilType.equals(BlockWireCoil.CoilType.FUSION_COIL))
                continue;

            shapeInfo.add(MultiblockShapeInfo.builder()
                    .aisle("#XXX#", "#XGX#", "#XXX#", "#####")
                    .aisle("XXXXX", "XCCCX", "X###X", "#XXX#")
                    .aisle("XXMXX", "GCPCG", "X#P#X", "#XmX#")
                    .aisle("XXXXX", "XCCCX", "X###X", "#XXX#")
                    .aisle("#XHX#", "#XSX#", "#IEO#", "#####")
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.NORTH)
                    .where('S', GATileEntities.LARGE_THERMAL_CENTRIFUGE, EnumFacing.SOUTH)
                    .where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                    .where('X', TileEntityLargeThermalCentrifuge.casingState)
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.SOUTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.SOUTH)
                    .where('M', GAMetaBlocks.MOTOR_CASING.getDefaultState())
                    .where('m', GATileEntities.MUFFLER_HATCH[0], EnumFacing.UP)
                    .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE))
                    .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                    .where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                    .build());
        }

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Temporary Placeholder"};
    }
}
