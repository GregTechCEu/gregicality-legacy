package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.ConveyorCasing;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeEngraverInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_LASER_ENGRAVER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        shapeInfo.add(MultiblockShapeInfo.builder()
                .aisle("XXX", "XeX", "XXX", "#T#")
                .aisle("XCX", "G#G", "XEX", "#T#")
                .aisle("XCX", "G#G", "XEX", "#T#")
                .aisle("XCX", "G#G", "XEX", "#T#")
                .aisle("XMX", "ISO", "XXX", "#T#")
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.ZPM], EnumFacing.NORTH)
                .where('S', GATileEntities.LARGE_LASER_ENGRAVER, EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.MUTLIBLOCK_CASING2.getState(GAMultiblockCasing2.CasingType.LASER_ENGRAVER))
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('#', Blocks.AIR.getDefaultState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.SOUTH)
                .where('C', GAMetaBlocks.CONVEYOR_CASING.getState(ConveyorCasing.CasingType.CONVEYOR_ZPM))
                .where('E', GAMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_ZPM))
                .where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING))
                .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.IRIDIUM_GLASS))
                .build());


        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Temporary Placeholder"};
    }
}
