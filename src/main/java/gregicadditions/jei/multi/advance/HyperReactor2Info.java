package gregicadditions.jei.multi.advance;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAReactorCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

import static gregtech.api.unification.material.Materials.Naquadria;

public class HyperReactor2Info extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.HYPER_REACTOR_II;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
         builder.aisle("#######C#######", "#####CCCCC#####", "#######C#######")
                .aisle("####CCCCCCC####", "###CC#####CC###", "####CCCCCCC####")
                .aisle("###CCCCCCCCC###", "##C##CCCCC##C##", "###CCCCCCCCC###")
                .aisle("##CCC#####CCC##", "#C##C#####C##C#", "##CCC#####CCC##")
                .aisle("#CCC#######CCC#", "#C#C#######C#C#", "#CCC#######CCC#")
                .aisle("#CC#########CC#", "C#C#########C#C", "#CC#########CC#")
                .aisle("#CC####F####CC#", "C#C####H####C#C", "#CC#########CC#")
                .aisle("MCC###FHF###CCC", "S#C###HHH###C#E", "CCC####H####CCC")
                .aisle("#CC####F####CC#", "f#C####H####C#C", "#CC#########CC#")
                .aisle("#CC#########CC#", "C#C#########C#C", "#CC#########CC#")
                .aisle("#CCC#######CCC#", "#C#C#######C#C#", "#CCC#######CCC#")
                .aisle("##CCC#####CCC##", "#C##C#####C##C#", "##CCC#####CCC##")
                .aisle("###CCCCCCCCC###", "##C##CCCCC##C##", "###CCCCCCCCC###")
                .aisle("####CCCCCCC####", "###CC#####CC###", "####CCCCCCC####")
                .aisle("#######C#######", "#####CCCCC#####", "#######C#######")
                .where('S', GATileEntities.HYPER_REACTOR_II, EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CASING)), EnumFacing.WEST)
                .where('C', GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CASING))
                .where('f', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.WEST)
                .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[8], EnumFacing.EAST)
                .where('H', GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CORE_2))
                .where('#', Blocks.AIR.getDefaultState())
                .where('F', MetaBlocks.FRAMES.get(Naquadria).getDefaultState());
        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format("gtadditions.multiblock.hyper_reactor.description")};
    }


    @Override
    public float getDefaultZoom() {
        return 0.4f;
    }
}
