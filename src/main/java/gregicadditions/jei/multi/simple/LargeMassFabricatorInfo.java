package gregicadditions.jei.multi.simple;

import com.google.common.collect.Lists;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.multi.simple.TileEntityLargeBrewery;
import gregicadditions.machines.multi.uumatter.TileEntityLargeMassFabricator;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
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
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.HV], EnumFacing.SOUTH)
                .where('S', GATileEntities.LARGE_MASS_FABRICATOR, EnumFacing.SOUTH)
                .where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('X', TileEntityLargeMassFabricator.casingState)
                .where('#', Blocks.AIR.getDefaultState())
                .where('c', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR))
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GAValues.LV], EnumFacing.WEST)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[GAValues.LV], EnumFacing.EAST)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[GAValues.LV], EnumFacing.SOUTH)
                .where('F', GAMetaBlocks.FIELD_GEN_CASING.getDefaultState())
                .where('U', GAMetaBlocks.PUMP_CASING.getDefaultState())
                .where('s', GAMetaBlocks.SENSOR_CASING.getDefaultState())
                .where('E', GAMetaBlocks.EMITTER_CASING.getDefaultState())
                .where('G', MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                .where('P', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.PTFE_PIPE))
                .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE));
        shapeInfo.add(builder.build());
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{};
    }

}
