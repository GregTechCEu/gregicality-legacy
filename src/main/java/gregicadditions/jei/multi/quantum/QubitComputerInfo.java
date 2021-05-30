package gregicadditions.jei.multi.quantum;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAQuantumCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class QubitComputerInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.QUBIT_COMPUTER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Lists.newArrayList(MultiblockShapeInfo.builder()
                .aisle("CCCC", "CCCC", "CCCC")
                .aisle("IOEC", "CSMC", "CCCC")
                .where('S', GATileEntities.QUBIT_COMPUTER, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('C', GAMetaBlocks.QUANTUM_CASING.getState(GAQuantumCasing.CasingType.COMPUTER))
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                .where('O', GATileEntities.QBIT_OUTPUT_HATCH[0], EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[4], EnumFacing.SOUTH)
                .build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }


    @Override
    public float getDefaultZoom() {
        return 1.1f;
    }
}
