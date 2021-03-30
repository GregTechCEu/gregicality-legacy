package gregicadditions.jei.multi;

import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.unification.material.Materials.Americium;
import static gregtech.api.unification.material.Materials.Steel;

public class ElectricImplosionInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.ELECTRIC_IMPLOSION;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shape = new ArrayList<>();
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        shape.add(builder
                .aisle("IXX", "AAA", "AAA", "AAA", "XXX")
                .aisle("SXE", "A#A", "A#A", "A#A", "XXX")
                .aisle("OXX", "AAA", "AAA", "AAA", "XXX")
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[1], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[1], EnumFacing.WEST)
                .where('S', getController(), EnumFacing.WEST)
                .where('A', GAMetaBlocks.getMetalCasingBlockState(Americium))
                .where('X', GAMetaBlocks.getMetalCasingBlockState(Steel))
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.UHV], EnumFacing.EAST)
                .build());
        return shape;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.electric_implosion.description")};
    }
}
