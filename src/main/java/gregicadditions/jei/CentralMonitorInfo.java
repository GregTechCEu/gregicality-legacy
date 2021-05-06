package gregicadditions.jei;

import gregicadditions.GAValues;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.centralmonitor.MetaTileEntityCentralMonitor;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class CentralMonitorInfo extends MultiblockInfoPage {

    public CentralMonitorInfo(){
    }

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.CENTRAL_MONITOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapes = new ArrayList<>();
        for (int i = 3; i <= MetaTileEntityCentralMonitor.MAX_WIDTH; i++) {
            int height = 3;
            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
            String[] start = new String[height];
            String[] slice = new String[height];
            String[] end = new String[height];
            for (int j = 0; j < height; j++) {
                start[j] = "A";
                slice[j] = "B";
                end[j] = "A";
            }
            start[0] = "E";
            start[1] = "S";
            builder.aisle(start);
            for (int num = 0; num < i; num++) {
                builder.aisle(slice);
            }
            builder.aisle(end)
            .where('S', GATileEntities.CENTRAL_MONITOR, EnumFacing.WEST)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.WEST)
            .where('A', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
            .where('B', GATileEntities.MONITOR_SCREEN, EnumFacing.WEST);
            shapes.add(builder.build());
        }
        return shapes;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.central_monitor.tooltip.2", MetaTileEntityCentralMonitor.MAX_WIDTH, MetaTileEntityCentralMonitor.MAX_HEIGHT), I18n.format("gtadditions.multiblock.central_monitor.tooltip.3")};
    }
}
