package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeWiremill;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class SteamGrinderInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.STEAM_GRINDER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();

        shapeInfo.add(MultiblockShapeInfo.builder()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XHX", "ISO", "XXX")
                .where('S', GATileEntities.STEAM_GRINDER, EnumFacing.SOUTH)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS))
                .where('I', GATileEntities.STEAM_INPUT_BUS, EnumFacing.SOUTH)
                .where('O', GATileEntities.STEAM_OUTPUT_BUS, EnumFacing.SOUTH)
                .where('H', GATileEntities.STEAM_HATCH, EnumFacing.SOUTH)
                .where('#', Blocks.AIR.getDefaultState())
                .build());

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{};
    }
}
