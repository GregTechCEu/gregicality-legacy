package gregicadditions.jei.multi.nuclear;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;


public class GasCentrifugeInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.GAS_CENTRIFUGE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder shapeInfo = MultiblockShapeInfo.builder()
                .aisle("#FEM#", "#YSY#", "#####", "#####", "#####", "#####", "#####")
                .aisle("OYYYY", "YYYYY", "#ZCZ#", "#Z#Z#", "#Z#Z#", "#Z#Z#", "#Z#Z#")
                .aisle("OYYYY", "YYYYY", "#CCC#", "#####", "#####", "#####", "#####")
                .aisle("OYYYY", "YYYYY", "#ZCZ#", "#Z#Z#", "#Z#Z#", "#Z#Z#", "#Z#Z#")
                .aisle("#IYY#", "#YYY#", "#####", "#####", "#####", "#####", "#####")
                .where('S', GATileEntities.GAS_CENTRIFUGE, EnumFacing.NORTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                .where('Y', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                .where('Z', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                .where('C', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.WEST)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.LV], EnumFacing.NORTH)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.ULV], EnumFacing.WEST)
                .where('#', Blocks.AIR.getDefaultState());
        return Lists.newArrayList(shapeInfo.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }
}
