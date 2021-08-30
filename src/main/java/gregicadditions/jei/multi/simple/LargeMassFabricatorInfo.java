package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.uumatter.TileEntityLargeMassFabricator;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

public class LargeMassFabricatorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.LARGE_MASS_FABRICATOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("XXXXX", "#P#P#", "#P#P#", "#P#P#", "XXXXX")
                .aisle("IXXXX", "PXGXP", "PXGXP", "PXGXP", "iXXXX")
                .aisle("IXXXX", "#pUp#", "#psp#", "#pUp#", "iGGGX")
                .aisle("IXXXX", "#pcp#", "#FEF#", "#pcp#", "iGGGX")
                .aisle("IXXXX", "#pcp#", "#FEF#", "#pcp#", "iGGGX")
                .aisle("IXXXX", "#pUp#", "#psp#", "#pUp#", "iGGGX")
                .aisle("IXXXX", "PXGXP", "PXGXP", "PXGXP", "iXXXX")
                .aisle("XHSeX", "#P#P#", "#P#P#", "#P#P#", "XoXoX")
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('S', GATileEntities.LARGE_MASS_FABRICATOR, EnumFacing.SOUTH)
                .where('H', maintenanceIfEnabled(GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM)), EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM))
                .where('#', Blocks.AIR.getDefaultState())
                .where('c', MetaBlocks.FUSION_COIL.getState(BlockFusionCoil.CoilType.SUPERCONDUCTOR))
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.LV], EnumFacing.EAST)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.LV], EnumFacing.SOUTH)
                .where('F', GAMetaBlocks.FIELD_GEN_CASING.getDefaultState())
                .where('U', GAMetaBlocks.PUMP_CASING.getDefaultState())
                .where('s', GAMetaBlocks.SENSOR_CASING.getDefaultState())
                .where('E', GAMetaBlocks.EMITTER_CASING.getDefaultState())
                .where('G', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE))
                .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE));
        shapeInfo.add(builder.build());
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.large_mass_fabricator.description")};
    }

}
