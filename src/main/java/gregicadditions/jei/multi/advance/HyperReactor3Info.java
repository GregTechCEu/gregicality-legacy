package gregicadditions.jei.multi.advance;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAReactorCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.Collections;
import java.util.List;

import static gregtech.api.unification.material.Materials.Naquadria;

public class HyperReactor3Info extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.HYPER_REACTOR_UEV;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
           builder.aisle("###########","###########","###########","###########","###########", "###########", "###########", "###########", "####CCC####", "###CCCCC###", "###CCECC###", "###CCCCC###", "####CCC####", "###########", "###########", "###########")
                  .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###CCCCC###", "##CC###CC##", "##C#####C##", "##C#####C##", "##C#####C##", "##CC###CC##", "###CCCCC###", "###########", "###########")
                  .aisle("##F#####F##","##F#####F##","##F#####F##","##F#####F##","##F#####F##", "##F#####F##", "##FCCCCCF##", "##C#####C##", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "##C#####C##", "###CCCCC###", "###########")
                  .aisle("###F###F###","###F###F###","###F###F###","###F###F###","###F###F###", "###FCCCF###", "##CC###CC##", "#C#######C#", "#C#######C#", "C#########C", "C####H####C", "C#########C", "#C#######C#", "#C#######C#", "##CC###CC##", "####CCC####")
                  .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C#########C", "G####H####G", "G###HHH###G", "G####H####G", "C#########C", "#C#######C#", "##C#####C##", "###CCCCC###")
                  .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C####H####C", "G###HHH###G", "G##HHHHH##G", "G###HHH###G", "C####H####C", "#C#######C#", "##C#####C##", "###CCCCC###")
                  .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C#########C", "G####H####G", "G###HHH###G", "G####H####G", "C#########C", "#C#######C#", "##C#####C##", "###CCCCC###")
                  .aisle("###F###F###","###F###F###","###F###F###","###F###F###","###F###F###", "###FCCCF###", "##CC###CC##", "#C#######C#", "#C#######C#", "C#########C", "C####H####C", "C#########C", "#C#######C#", "#C#######C#", "##CC###CC##", "####CCC####")
                  .aisle("##F#####F##","##F#####F##","##F#####F##","##F#####F##","##F#####F##", "##F#####F##", "##FCCCCCF##", "##C#####C##", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "##C#####C##", "###CCCCC###", "###########")
                  .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###CCCCC###", "##CC###CC##", "##C#####C##", "##C#####C##", "##C#####C##", "##CC###CC##", "###CCCCC###", "###########", "###########")
                  .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###########", "####CCC####", "###CCMCC###", "###CfSCC###", "###CCCCC###", "####CCC####", "###########", "###########", "###########")
                  .where('S', GATileEntities.HYPER_REACTOR_UEV, EnumFacing.SOUTH)
                  .where('M', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.SOUTH)
                  .where('C', GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CASING_2))
                  .where('f', MetaTileEntities.FLUID_IMPORT_HATCH[4], EnumFacing.SOUTH)
                  .where('E', MetaTileEntities.ENERGY_OUTPUT_HATCH[8], EnumFacing.NORTH)
                  .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS))
                  .where('H', GAMetaBlocks.REACTOR_CASING.getState(GAReactorCasing.CasingType.HYPER_CORE_3))
                  .where('#', Blocks.AIR.getDefaultState())
                  .where('F', MetaBlocks.FRAMES.get(Naquadria).getDefaultState());
        return Collections.singletonList(builder.build());
    }

    @Override
    public String[] getDescription() {
        return new String[] {"Temporary Placeholder"};
    }
}
