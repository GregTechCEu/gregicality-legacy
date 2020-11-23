package gregicadditions.jei.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

public class DrillingRigInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.DRILLING_RIG;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {

        MultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder.aisle("XEO", "XSX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XXX", "XXX")
                .where('S', GATileEntities.DRILLING_RIG, EnumFacing.NORTH)
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.NORTH)
                .where('X', GAMetaBlocks.getMetalCasingBlockState(Materials.Steel))
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV], EnumFacing.NORTH);

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[0];
    }
}
