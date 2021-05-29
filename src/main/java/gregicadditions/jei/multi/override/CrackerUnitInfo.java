package gregicadditions.jei.multi.override;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CrackerUnitInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.CRACKER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            if (!Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName())) {

                shapeInfo.add(MultiblockShapeInfo.builder()
                        .aisle("XCMCX", "XCSCF", "XCXCX")
                        .aisle("XCXCX", "H###X", "XCXCX")
                        .aisle("XCXCX", "XCECF", "XCXCX")
                        .where('S', GATileEntities.CRACKER, EnumFacing.NORTH)
                        .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                        .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                        .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                        .where('#', Blocks.AIR.getDefaultState())
                        .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.EAST)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                        .where('H', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.HV], EnumFacing.WEST)
                        .build());
            }
        }
        for (GAHeatingCoil.CoilType coilType : GAHeatingCoil.CoilType.values()) {
            if (!Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName())) {

                shapeInfo.add(MultiblockShapeInfo.builder()
                        .aisle("XCXCX", "XCSCF", "XCXCX")
                        .aisle("XCXCX", "H###X", "XCXCX")
                        .aisle("XCXCX", "XCECF", "XCXCX")
                        .where('S', GATileEntities.CRACKER, EnumFacing.NORTH)
                        .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                        .where('C', GAMetaBlocks.HEATING_COIL.getState(coilType))
                        .where('#', Blocks.AIR.getDefaultState())
                        .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.EAST)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                        .where('H', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.HV], EnumFacing.WEST)
                        .build());
            }
        }
        return shapeInfo;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.cracker.description")};
    }

}
