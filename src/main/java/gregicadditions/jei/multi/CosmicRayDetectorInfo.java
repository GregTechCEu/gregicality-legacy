package gregicadditions.jei.multi;

import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.FieldGenCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
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
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("######XXX######", "######XXX######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("###XXXXXXXXX###", "###X##EcE##X###", "###X###c###X###", "####X##c##X####", "####X#FcF#X####", "###XXxxExxXX###", "##xxx##C##xxx##", "#x#####C#####x#", "x######C######x", "#######s#######")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("######XMX######", "######fSe######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .where('S', GATileEntities.COSMIC_RAY_DETECTOR, EnumFacing.SOUTH)
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('e', GATileEntities.ENERGY_INPUT[0], EnumFacing.SOUTH)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
                .where('X', GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.QUANTUM))
                .where('x', GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM))
                .where('C', MetaBlocks.FRAMES.get(GAMaterials.BlackTitanium).getDefaultState())
                .where('c', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR))
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
        return new String[] {"Temporary Placeholder"};
    }


    @Override
    public float getDefaultZoom() {
        return 0.3f;
    }
}
