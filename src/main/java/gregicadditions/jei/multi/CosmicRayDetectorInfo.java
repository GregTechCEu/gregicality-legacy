package gregicadditions.jei.multi;

import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.FieldGenCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

public class CosmicRayDetectorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.COSMIC_RAY_DETECTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder
                .aisle("##XXX##", "##XXX##", "#######", "#######", "##xxx##")
                .aisle("#XXXXX#", "#XXXXX#", "###X###", "##XXX##", "#xx#xx#")
                .aisle("XXXXXXX", "XXXFXXX", "##XXX##", "#XXXXX#", "xx###xx")
                .aisle("XXXXXXX", "XXFPFXX", "#XXFXX#", "#XXEXX#", "x##s##x")
                .aisle("XXXXXXX", "XXXFXXX", "##XXX##", "#XXXXX#", "xx###xx")
                .aisle("#XXXXX#", "#XXXXX#", "###X###", "##XXX##", "#xx#xx#")
                .aisle("##fSe##", "##XXX##", "#######", "#######", "##xxx##")
                .where('S', GATileEntities.COSMIC_RAY_DETECTOR, EnumFacing.SOUTH)
                .where('e', GATileEntities.ENERGY_INPUT[0], EnumFacing.SOUTH)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.METAL_CASING.get(GAMaterials.Quantum).getDefaultState())
                .where('x', GAMetaBlocks.METAL_CASING.get(Materials.Tritanium).getDefaultState())
                .where('F', GAMetaBlocks.FIELD_GEN_CASING.getState(FieldGenCasing.CasingType.FIELD_GENERATOR_UHV))
                .where('E', GAMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_UHV))
                .where('s', GAMetaBlocks.SENSOR_CASING.getState(SensorCasing.CasingType.SENSOR_UHV))
                .where('P', GAMetaBlocks.PUMP_CASING.getState(PumpCasing.CasingType.PUMP_UHV))
                .where('#', Blocks.AIR.getDefaultState())
                .build();

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[0];
    }
}
