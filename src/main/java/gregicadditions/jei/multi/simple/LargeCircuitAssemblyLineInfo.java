package gregicadditions.jei.multi.simple;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeCircuitAssemblyLine;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.unification.material.Materials.Steel;

public class LargeCircuitAssemblyLineInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_CIRCUIT_ASSEMBLY_LINE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return TileEntityLargeCircuitAssemblyLine.CASING1_ALLOWED.stream().map(casingType -> {
            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
            builder.aisle("COC", "RTR", "GYG");
            for (int num = 0; num < 5; num++) {
                if (num == 1) builder.aisle("CIC", "RTR", "GAG");
                else builder.aisle("CIC", "RTR", "GAG");
            }
            builder.aisle("FIC", "RTR", "GSG")
                    .where('S', GATileEntities.LARGE_CIRCUIT_ASSEMBLY_LINE, EnumFacing.SOUTH)
                    .where('C', GAMetaBlocks.getMetalCasingBlockState(Steel))
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.DOWN)
                    .where('Y', MetaTileEntities.ENERGY_INPUT_HATCH[4], EnumFacing.NORTH)
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.DOWN)
                    .where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                    .where('A', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING))
                    .where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
                    .where('T', GAMetaBlocks.MUTLIBLOCK_CASING.getState(casingType));
            return builder.build();
        }).collect(Collectors.toList());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gregtech.multiblock.large_circuit_assembly.description")};
    }

}