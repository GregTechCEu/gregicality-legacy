package gregicadditions.jei.multi.advance;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.List;

import static gregicadditions.GAMaterials.BabbittAlloy;

public class AdvancedDistillationTowerInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.ADVANCED_DISTILLATION_TOWER;
    }


    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("CXX", "XXX", "XXX", "XXX", "XXX", "XXX")
                .aisle("SFX", "X#X", "X#X", "X#X", "X#X", "XXX")
                .aisle("IEX", "HXX", "HXX", "HXX", "HXX", "HXX")
                .where('#', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV))
                .where('X', GAMetaBlocks.getMetalCasingBlockState(BabbittAlloy))
                .where('S', GATileEntities.ADVANCED_DISTILLATION_TOWER, EnumFacing.WEST)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.EV], EnumFacing.SOUTH)
                .where('C', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.EV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.EV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.EV], EnumFacing.DOWN)
                .where('H', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.EV], EnumFacing.WEST)
                .build();
        return Lists.newArrayList(shapeInfo);
    }



    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.advanced_distillation_tower.description1")};
    }
}
