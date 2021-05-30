package gregicadditions.jei.multi.miner;

import com.google.common.collect.Lists;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.miner.MetaTileEntityLargeMiner;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;


public class LargeMinerInfo extends MultiblockInfoPage {

    private final MetaTileEntityLargeMiner largeMiner;

    public LargeMinerInfo(MetaTileEntityLargeMiner largeMiner) {
        this.largeMiner = largeMiner;
    }

    @Override
    public MultiblockControllerBase getController() {
        return largeMiner;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("F###F", "F###F", "PPPPP", "#####", "#####", "#####", "#####", "#####", "#####", "#####")
                .aisle("#####", "#####", "PPPPP", "#OEC#", "#####", "#####", "#####", "#####", "#####", "#####")
                .aisle("#####", "#####", "PPPPP", "#IPC#", "#FFF#", "#FFF#", "#FFF#", "##F##", "##F##", "##F##")
                .aisle("#####", "#####", "PPPPP", "#MSC#", "#####", "#####", "#####", "#####", "#####", "#####")
                .aisle("F###F", "F###F", "PPPPP", "#####", "#####", "#####", "#####", "#####", "#####", "#####")
                .where('S', largeMiner, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('C', largeMiner.getCasingState())
                .where('P', largeMiner.getCasingState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[4], EnumFacing.NORTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.WEST)
                .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.WEST)
                .where('F', MetaBlocks.FRAMES.get(largeMiner.getMaterial()).getDefaultState())
                .where('#', Blocks.AIR.getDefaultState())
                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Temporary Placeholder"};
    }

    @Override
    public float getDefaultZoom() {
        return 0.5f;
    }
}
