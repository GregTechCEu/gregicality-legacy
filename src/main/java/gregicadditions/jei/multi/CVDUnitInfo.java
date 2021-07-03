package gregicadditions.jei.multi;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CVDUnitInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.CVD_UNIT;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            if (!Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName())) {
                shapeInfo.add(MultiblockShapeInfo.builder()
                        .aisle("CCC", "CGC", "CGC", "CCC")
                        .aisle("ICF", "CHC", "CUC", "C#C")
                        .aisle("CCF", "SPC", "CUC", "###")
                        .aisle("OCF", "CHC", "CUC", "C#C")
                        .aisle("CEC", "CGC", "CGC", "CCC")
                        .where('S', GATileEntities.CVD_UNIT, EnumFacing.WEST)
                        .where('C', GAMetaBlocks.getMetalCasingBlockState(Materials.StainlessSteel))
                        .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
                        .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
                        .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.EAST)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.WEST)
                        .where('P', GAMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_HV))
                        .where('H', MetaBlocks.WIRE_COIL.getState(coilType))
                        .where('U', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                        .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS))
                        .where('#', Blocks.AIR.getDefaultState())
                        .build());
            }
        }
        for (GAHeatingCoil.CoilType coilType : GAHeatingCoil.CoilType.values()) {
            if (!Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName())) {
                shapeInfo.add(MultiblockShapeInfo.builder()
                        .aisle("CCC", "CGC", "CGC", "CCC")
                        .aisle("ICF", "CHC", "CUC", "C#C")
                        .aisle("CCF", "SPC", "CUC", "###")
                        .aisle("OCF", "CHC", "CUC", "C#C")
                        .aisle("CEC", "CGC", "CGC", "CCC")
                        .where('S', GATileEntities.CVD_UNIT, EnumFacing.WEST)
                        .where('C', GAMetaBlocks.getMetalCasingBlockState(Materials.StainlessSteel))
                        .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
                        .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.LV], EnumFacing.WEST)
                        .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.EAST)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.WEST)
                        .where('P', GAMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_HV))
                        .where('H', GAMetaBlocks.HEATING_COIL.getState(coilType))
                        .where('U', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                        .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS))
                        .where('#', Blocks.AIR.getDefaultState())
                        .build());
            }
        }
        return shapeInfo;
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }
}
