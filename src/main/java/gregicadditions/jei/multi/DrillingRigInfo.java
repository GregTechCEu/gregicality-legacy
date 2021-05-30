package gregicadditions.jei.multi;

import gregicadditions.GAValues;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

import static gregtech.api.unification.material.Materials.StainlessSteel;

public class DrillingRigInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.DRILLING_RIG;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {

        MultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
        builder
                .aisle("F#####F", "F#####F", "CCCCCCC", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                .aisle("#######", "#######", "CCCCCCC", "#OMSEI#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##", "#######", "#######", "#######", "#######", "#######")
                .aisle("#######", "#######", "CCCCCCC", "#OBBBB#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#BB#BB#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##")
                .aisle("###P###", "###P###", "CCCPCCC", "#BBPBB#", "###P###", "###P###", "###P###", "###P###", "###P###", "#B#P#B#", "###P###", "###P###", "###P###", "###P###", "##BPB##")
                .aisle("#######", "#######", "CCCCCCC", "#BBBBB#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#BB#BB#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##")
                .aisle("#######", "#######", "CCCCCCC", "#BBBBB#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##", "#######", "#######", "#######", "#######", "#######")
                .aisle("F#####F", "F#####F", "CCCCCCC", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                .where('B', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                .where('F', MetaBlocks.FRAMES.get(StainlessSteel).getDefaultState())
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                .where('C', MetaBlocks.CONCRETE.withVariant(BlockConcrete.ConcreteVariant.LIGHT_CONCRETE, StoneBlock.ChiselingVariant.NORMAL))
                .where('S', GATileEntities.DRILLING_RIG, EnumFacing.NORTH)
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[4], EnumFacing.NORTH)
                .where('I', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.NORTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GAValues.EV], EnumFacing.NORTH);

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
