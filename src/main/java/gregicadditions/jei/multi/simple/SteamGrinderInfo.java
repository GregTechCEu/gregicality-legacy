package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAConfig;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
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
                .aisle("XXX", "IXX", "XXX")
                .aisle("HXX", "S#X", "XXX")
                .aisle("XXX", "OXX", "XXX")
                .where('S', GATileEntities.STEAM_GRINDER, EnumFacing.WEST)
                .where('X', GAConfig.multis.steamMultis.useSteelMultis ? MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID) : MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS))
                .where('I', GATileEntities.STEAM_INPUT_BUS, EnumFacing.WEST)
                .where('O', GATileEntities.STEAM_OUTPUT_BUS, EnumFacing.WEST)
                .where('H', GATileEntities.STEAM_HATCH, EnumFacing.WEST)
                .where('#', Blocks.AIR.getDefaultState())
                .build());

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.steam_grinder.description")};
    }
}
