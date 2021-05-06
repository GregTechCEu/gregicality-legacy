package gregicadditions.jei.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.NuclearCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

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
                .where('A', GAMetaBlocks.NUCLEAR_CASING.getState(NuclearCasing.CasingType.AMERICIUM))
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                .where('E', GATileEntities.ENERGY_INPUT[0], EnumFacing.EAST)
                .build());
        return shape;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.electric_implosion.description")};
    }
}
