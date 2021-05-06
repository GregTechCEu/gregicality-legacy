package gregicadditions.jei.multi.miner;

import com.google.common.collect.Lists;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class VoidMinerInfo3 extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.VOID_MINER[2];
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("CCCCCCCCC", "CCCCCCCCC", "C#######C", "C#######C", "C#######C", "CCCCCCCCC", "CFFFFFFFC", "CFFFFFFFC", "C#######C", "C#######C")
                .aisle("C#######C", "C#######C", "#########", "#########", "#########", "C###D###C", "F##DDD##F", "F##DDD##F", "###DDD###", "#########")
                .aisle("C#######C", "C#######C", "#########", "####D####", "###DDD###", "C##DDD##C", "F#DD#DD#F", "F#D###D#F", "##D###D##", "#########")
                .aisle("E###D###C", "C###D###C", "###DDD###", "###D#D###", "##DD#DD##", "C#D###D#C", "FDD###DDF", "FD#####DF", "#D#####D#", "#########")
                .aisle("O##D#D##C", "S##D#D##C", "###D#D###", "##D###D##", "##D###D##", "CDD###DDC", "FD#####DF", "FD#####DF", "#D#####D#", "#########")
                .aisle("I###D###C", "C###D###C", "###DDD###", "###D#D###", "##DD#DD##", "C#D###D#C", "FDD###DDF", "FD#####DF", "#D#####D#", "#########")
                .aisle("C#######C", "C#######C", "#########", "####D####", "###DDD###", "C##DDD##C", "F#DD#DD#F", "F#D###D#F", "##D###D##", "#########")
                .aisle("C#######C", "C#######C", "#########", "#########", "#########", "C###D###C", "F##DDD##F", "F##DDD##F", "###DDD###", "#########")
                .aisle("CCCCCCCCC", "CCCCCCCCC", "C#######C", "C#######C", "C#######C", "CCCCCCCCC", "CFFFFFFFC", "CFFFFFFFC", "C#######C", "C#######C")
                .where('S', GATileEntities.VOID_MINER[2], EnumFacing.WEST)
                .where('C', METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_K243))
                .where('D', METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_X78))
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[8], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.WEST)
                .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.WEST)
                .where('F', MetaBlocks.FRAMES.get(Bohrium).getDefaultState())
                .where('#', Blocks.AIR.getDefaultState())
                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Temporary Placeholder"};
    }
}
