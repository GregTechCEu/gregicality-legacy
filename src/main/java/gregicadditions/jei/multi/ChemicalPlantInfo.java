package gregicadditions.jei.multi;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChemicalPlantInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.CHEMICAL_PLANT;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            if (!Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName())) {

                shapeInfo.add(GAMultiblockShapeInfo.builder()
                        .aisle("XXXXX", "RRRRR", "RRRRR", "RRRRR", "XXXXX")
                        .aisle("FXXXX", "RCCCR", "RCCCR", "RCCCR", "XXXXX")
                        .aisle("FXXXM", "RCTCR", "RCTCR", "RCTCR", "XXXXX")
                        .aisle("FXXXE", "RCCCR", "RCCCR", "RCCCR", "XXXXX")
                        .aisle("FISOO", "RRRRR", "RRRRR", "RRRRR", "XXXXX")
                        .where('S', GATileEntities.CHEMICAL_PLANT, EnumFacing.SOUTH)
                        .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.EAST)
                        .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                        .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                        .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.SOUTH)
                        .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                        .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                        .where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
                        .where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_HV))
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.EV], EnumFacing.EAST)
                        .build());
            }
        }
        for (GAHeatingCoil.CoilType coilType : GAHeatingCoil.CoilType.values()) {
            if (!Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName())) {

                shapeInfo.add(GAMultiblockShapeInfo.builder()
                        .aisle("XXXXX", "RRRRR", "RRRRR", "RRRRR", "XXXXX")
                        .aisle("FXXXX", "RCCCR", "RCCCR", "RCCCR", "XXXXX")
                        .aisle("FXXXX", "RCTCR", "RCTCR", "RCTCR", "XXXXX")
                        .aisle("FXXXE", "RCCCR", "RCCCR", "RCCCR", "XXXXX")
                        .aisle("FISOO", "RRRRR", "RRRRR", "RRRRR", "XXXXX")
                        .where('S', GATileEntities.CHEMICAL_PLANT, EnumFacing.SOUTH)
                        .where('C', GAMetaBlocks.HEATING_COIL.getState(coilType))
                        .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                        .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.SOUTH)
                        .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID))
                        .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                        .where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
                        .where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_HV))
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.EV], EnumFacing.EAST).build());
            }
        }
        return shapeInfo;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.chemical_plant.description")};
    }

    @Override
    public float getDefaultZoom() {
        return 0.7f;
    }
}
