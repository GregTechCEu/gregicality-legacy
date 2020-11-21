package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregicadditions.item.fusion.GACryostatCasing;
import gregicadditions.item.fusion.GADivertorCasing;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.fusion.GAVacuumCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class FusionReactor4Info extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {

        return GATileEntities.ADVANCED_FUSION_REACTOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()

                .aisle("#################","#################","######ccCcc######","######ccCcc######","#################","#################")
                .aisle("#################","######ccCcc######","####ccvvvvvcc####","####ccvvvvvcc####","########C########","#################")
                .aisle("########C########","####cdddddddc####","##Ccvv#####vvcC##","##Ccvv#####vvcC##","####cbEEbEEbc####","########C########")
                .aisle("########C########","###CvdddddddvC###","##cv#########vc##","##cv#########vc##","###CbbbbbbbbbC###","########C########")
                .aisle("####C#######C####","##cvddvcCcvddvc##","#cv####vvv####vc#","#cv####vvv####vc#","##cbbbbcCcbbbbc##","####C#######C####")
                .aisle("#####C#####C#####","#cvddvc###cvddvc#","#cv###vcCcv###vc#","#cv###vcCcv###vc#","#cbbbbc###cbbbbc#","#####C#####C#####")
                .aisle("#################","#cddvcC###Ccvddc#","cv###vC###Cv###vc","cv###vC###Cv###vc","#cbbbcC###Ccbbbc#","#################")
                .aisle("#######XXX#######","#cddc##CCC##cddc#","cv##vc#CCC#cv##vc","cv##vc#CCC#cv##vc","#cbbc##CCC##cbbc#","#######XXX#######")
                .aisle("##CC###XXX###CC##","#CddC##CCC##CddC#","Cv##vC#CCC#Cv##vC","Cv##vC#CCC#Cv##vC","#CbbC##CCC##CbbC#","##CC###XXX###CC##")
                .aisle("#######XXX#######","#cddc##CCC##cddc#","cv##vc#CCC#cv##vc","cv##vc#CCC#cv##vc","#cbbc##CCC##cbbc#","#######XXX#######")
                .aisle("#################","#cddvcC###Ccvddc#","cv###vC###Cv###vc","cv###vC###Cv###vc","#cbbbcC###Ccbbbc#","#################")
                .aisle("#####C#####C#####","#cvddvc###cvddvc#","#cv###vcCcv###vc#","#cv###vcCcv###vc#","#cbbbbc###cbbbbc#","#####C#####C#####")
                .aisle("####C#######C####","##cvddvcCcvddvc##","#cv####vvv####vc#","#cv####vvv####vc#","##cbbbbcCcbbbbc##","####C#######C####")
                .aisle("########C########","###CvdddddddvC###","##cv#########vc##","##cv#########vc##","###CbbbbbbbbbC###","########C########")
                .aisle("########C########","####cfffvFFXc####","##Ccvv#####vvcC##","##Ccvv#####vvcC##","####cEEEbEEEc####","########C########")
                .aisle("#################","########S########","####ccvvvvvcc####","####ccvvvvvcc####","########C########","#################")
                .aisle("#################","#################","########C########","########C########","#################","#################")
                .where('S', GATileEntities.ADVANCED_FUSION_REACTOR, EnumFacing.SOUTH)
                .where('#', Blocks.AIR.getDefaultState())
                .where('C', GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.ADV_FUSION_COIL_1))
                .where('X', GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.ADV_FUSION_CASING))
                .where('f', MetaTileEntities.FLUID_IMPORT_HATCH[8], EnumFacing.SOUTH)
                .where('F', MetaTileEntities.FLUID_EXPORT_HATCH[8], EnumFacing.SOUTH)
                .where('E', GATileEntities.ENERGY_INPUT[0], EnumFacing.NORTH)
                .where('c', GAMetaBlocks.CRYOSTAT_CASING.getState(GACryostatCasing.CasingType.CRYOSTAT_1))
                .where('v', GAMetaBlocks.VACUUM_CASING.getState(GAVacuumCasing.CasingType.VACUUM_1))
                .where('b', GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_BLANKET))
                .where('d', GAMetaBlocks.DIVERTOR_CASING.getState(GADivertorCasing.CasingType.DIVERTOR_1))

                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[] {};
    }
}
