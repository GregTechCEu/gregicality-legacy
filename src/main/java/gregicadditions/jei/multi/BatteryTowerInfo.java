package gregicadditions.jei.multi;

import gregicadditions.item.CellCasing;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BatteryTowerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.BATTERY_TOWER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Arrays.stream(CellCasing.CellType.values()).map(cellType -> MultiblockShapeInfo.builder()
                .aisle("CCCCC", "GGGGG", "GGGGG", "GGGGG", "GGGGG", "CCCCC")
                .aisle("CCCCC", "GRRRG", "GRRRG", "GRRRG", "GRRRG", "CCCCC")
                .aisle("SCCCC", "GRRRG", "GRRRG", "GRRRG", "GRRRG", "CCCCC")
                .aisle("CCCCC", "GRRRG", "GRRRG", "GRRRG", "GRRRG", "CCCCC")
                .aisle("CCCCC", "GGGGG", "GGGGG", "GGGGG", "GGGGG", "CCCCC")
                .where('S', GATileEntities.BATTERY_TOWER, EnumFacing.WEST)
                .where('C', GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.TALONITE))
                .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS))
                .where('R', GAMetaBlocks.CELL_CASING.getState(cellType))
                .build()).collect(Collectors.toList());


    }

    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.battery_tower.description")};
    }
}
