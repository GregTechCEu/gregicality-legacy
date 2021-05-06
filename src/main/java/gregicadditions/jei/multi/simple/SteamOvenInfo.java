package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockFireboxCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;


public class SteamOvenInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.STEAM_OVEN;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();

        shapeInfo.add(MultiblockShapeInfo.builder()
                .aisle("FFF", "IXX", "###")
                .aisle("HFF", "S#X", "XXX")
                .aisle("FFF", "OXX", "###")
                .where('S', GATileEntities.STEAM_OVEN, EnumFacing.WEST)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS))
                .where('F', MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.BRONZE_FIREBOX))
                .where('I', GATileEntities.STEAM_INPUT_BUS, EnumFacing.WEST)
                .where('O', GATileEntities.STEAM_OUTPUT_BUS, EnumFacing.WEST)
                .where('H', GATileEntities.STEAM_HATCH, EnumFacing.WEST)
                .where('#', Blocks.AIR.getDefaultState())
                .build());

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.steam_oven.description")};
    }
}
