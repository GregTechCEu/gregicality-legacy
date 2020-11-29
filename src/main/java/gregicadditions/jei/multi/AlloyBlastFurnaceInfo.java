package gregicadditions.jei.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.GAMaterials.Staballoy;
import static gregicadditions.GAMaterials.ZirconiumCarbide;

public class AlloyBlastFurnaceInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.ALLOY_BLAST_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shape = new ArrayList<>();
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        shape.add(builder.aisle("OXX", "YYY", "YYY", "XXX")
                .aisle("SXX", "Y#Y", "Y#Y", "XXE")
                .aisle("IXX", "YYY", "YYY", "XXX")
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.WEST)
                .where('S', GATileEntities.ALLOY_BLAST_FURNACE, EnumFacing.WEST)
                .where('Y', GAMetaBlocks.getMetalCasingBlockState(Staballoy))
                .where('X', GAMetaBlocks.getMetalCasingBlockState(ZirconiumCarbide))
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV], EnumFacing.EAST)
                .build());
            return shape;
    }

    @Override
    public String[] getDescription() {
        return new String[]{};
    }
}
