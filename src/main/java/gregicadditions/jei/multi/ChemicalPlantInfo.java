package gregicadditions.jei.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChemicalPlantInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.CHEMICAL_PLANT;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Arrays.stream(BlockWireCoil.CoilType.values()).map(coilType -> {
            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
            builder.aisle("XXXXX", "RRRRR", "RRRRR", "RRRRR", "YYYYY")
                    .aisle("FXXXX", "RCCCR", "RCCCR", "RCCCR", "YYYYY")
                    .aisle("FXXXX", "RCTCR", "RCTCR", "RCTCR", "YYYYY")
                    .aisle("FXXXE", "RCCCR", "RCCCR", "RCCCR", "YYYYY")
                    .aisle("FISOO", "RRRRR", "RRRRR", "RRRRR", "YYYYY")
                    .where('S', GATileEntities.CHEMICAL_PLANT, EnumFacing.SOUTH)
                    .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                    .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.SOUTH)
                    .where('Y', GAMetaBlocks.METAL_CASING.get(Materials.Steel).getDefaultState())
                    .where('X', GAMetaBlocks.METAL_CASING.get(Materials.Steel).getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                    .where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
                    .where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_HV))
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV], EnumFacing.EAST);
            return builder.build();
        }).collect(Collectors.toList());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.chemical_plant.description")};
    }
}
