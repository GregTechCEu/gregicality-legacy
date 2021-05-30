package gregicadditions.jei.multi.advance;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

public class StellarForgeInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.STELLAR_FORGE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
         builder.aisle("###############", "######CEC######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "#F####XXX####F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F####XXX####F#", "######C#C######")
                .aisle("#CCCCCCMCCCCCC#", "CF###XXXXX###FC", "C##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF###XXXXX###FC", "#CCCCCCMCCCCCC#")
                .aisle("######MMM######", "CF###XXXXX###FC", "###FX#####XF###", "####X#####X####", "####X#####X####", "####X#####X####", "###FX#####XF###", "CF###XXXXX###FC", "######MMM######")
                .aisle("#CCCCCCMCCCCCC#", "CF###XXXXX###FC", "C##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF###XXXXX###FC", "#CCCCCCMCCCCCC#")
                .aisle("######C#C######", "#F####XXX####F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F####XXX####F#", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                .aisle("###############", "######ISi######", "######f#H######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                .where('M', GAMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_UV))
                .where('C', GAMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.ENRICHED_NAQUADAH_ALLOY))
                .where('X', GAMetaBlocks.MUTLIBLOCK_CASING2.getState(GAMultiblockCasing2.CasingType.STELLAR_CONTAINMENT))
                .where('F', GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_COIL_2))
                .where('S', GATileEntities.STELLAR_FORGE, EnumFacing.SOUTH)
                .where('H', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[8], EnumFacing.NORTH)
                .where('f', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_EXPORT_BUS[4], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.ITEM_IMPORT_BUS[4], EnumFacing.SOUTH);

        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }


    @Override
    public float getDefaultZoom() {
        return 0.4f;
    }
}
