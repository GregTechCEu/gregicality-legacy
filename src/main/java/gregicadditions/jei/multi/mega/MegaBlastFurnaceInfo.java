package gregicadditions.jei.multi.mega;

import com.google.common.collect.Lists;
import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MegaBlastFurnaceInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.MEGA_BLAST_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() { //TODO change pattern to have the controller face the viewer in jei
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()) {
            if (Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName()))
                continue;

                GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder(RIGHT, FRONT, UP);
            builder.aisle("XXOIXXMSXXFEXXH", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX");
            for (int i = 0; i < 18; i++) {
                builder.aisle("GGGGGGGGGGGGGGG", "GCCCCCCCCCCCCCG", "GCP###ppp###PCG", "GC###########CG", "GC###########CG", "GC###########CG", "GCp#########pCG", "GCp#########pCG", "GCp#########pCG", "GC###########CG", "GC###########CG", "GC###########CG", "GCP###ppp###PCG", "GCCCCCCCCCCCCCG", "GGGGGGGGGGGGGGG");
            }
            builder.aisle("XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXggggXXXggggXX", "XXggggXmXggggXX", "XXggggXXXggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX")
                    .where('H', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.IV], EnumFacing.NORTH)
                    .where('S', GATileEntities.MEGA_BLAST_FURNACE, EnumFacing.NORTH)
                    .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                    .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.NORTH)
                    .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.HV], EnumFacing.NORTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.NORTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.NORTH)
                    .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                    .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('P', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV))
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                    .where('m', GATileEntities.MUFFLER_HATCH[0], EnumFacing.UP)
                    .where('g', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING));
            shapeInfo.add(builder.build());
        }

        for (GAHeatingCoil.CoilType coilType : GAHeatingCoil.CoilType.values()) {
            if (Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName()))
                continue;

            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder(RIGHT, FRONT, UP);
            builder.aisle("XXOIXXMSXXFEXXH", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX");
            for (int i = 0; i < 18; i++) {
                builder.aisle("GGGGGGGGGGGGGGG", "GCCCCCCCCCCCCCG", "GCP###ppp###PCG", "GC###########CG", "GC###########CG", "GC###########CG", "GCp#########pCG", "GCp#########pCG", "GCp#########pCG", "GC###########CG", "GC###########CG", "GC###########CG", "GCP###ppp###PCG", "GCCCCCCCCCCCCCG", "GGGGGGGGGGGGGGG");
            }
            builder.aisle("XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXggggXXXggggXX", "XXggggXmXggggXX", "XXggggXXXggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXgggggggggggXX", "XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX")
                    .where('H', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.IV], EnumFacing.NORTH)
                    .where('S', GATileEntities.MEGA_BLAST_FURNACE, EnumFacing.NORTH)
                    .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                    .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.HV], EnumFacing.NORTH)
                    .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.HV], EnumFacing.NORTH)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GAValues.HV], EnumFacing.NORTH)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.HV], EnumFacing.NORTH)
                    .where('C', GAMetaBlocks.HEATING_COIL.getState(coilType))
                    .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('P', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV))
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                    .where('m', GATileEntities.MUFFLER_HATCH[0], EnumFacing.UP)
                    .where('g', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING));
            shapeInfo.add(builder.build());
        }

        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{};
    }

    @Override
    public float getDefaultZoom() {
        return 0.2f;
    }
}
