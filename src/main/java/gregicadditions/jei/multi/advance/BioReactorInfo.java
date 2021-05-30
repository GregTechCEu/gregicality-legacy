package gregicadditions.jei.multi.advance;

import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.FieldGenCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;


public class BioReactorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.BIO_REACTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shape = new ArrayList<>();
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        shape.add(builder
                .aisle("XXeXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .aisle("XXXXX", "G###G", "G#s#G", "G###G", "XXXXX")
                .aisle("XXXXX", "G#P#G", "GEFEG", "G#P#G", "XXXXX")
                .aisle("XXXXX", "G###G", "G#s#G", "G###G", "XXXXX")
                .aisle("oISOi", "XGGGX", "XGGGX", "XGGGX", "XXMXX")
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.SOUTH)
                .where('S', GATileEntities.BIO_REACTOR, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.MUTLIBLOCK_CASING2.getState(GAMultiblockCasing2.CasingType.BIO_REACTOR))
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.UV], EnumFacing.NORTH)
                .where('E', GAMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_UV))
                .where('P', GAMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_UV))
                .where('F', GAMetaBlocks.FIELD_GEN_CASING.getState(FieldGenCasing.CasingType.FIELD_GENERATOR_UV))
                .where('s', GAMetaBlocks.SENSOR_CASING.getState(SensorCasing.CasingType.SENSOR_UV))
                .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                .where('#', Blocks.AIR.getDefaultState())
                .build());
        return shape;
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }


    @Override
    public float getDefaultZoom() {
        return 0.7f;
    }
}
