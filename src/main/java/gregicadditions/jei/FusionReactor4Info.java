package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class FusionReactor4Info extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GATileEntities.FUSION_REACTOR_UHV;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("#################","#################","########C########","########C########","#################","#################")
                .aisle("#################","#######CCC#######","######XXXXX######","######XXXXX######","########C########","#################")
                .aisle("########C########","#####XXXXXXX#####","##C#XX#####XX#C##","##C#XX#####XX#C##","#####XXXXXXX#####","########C########")
                .aisle("########C########","###CXXXXXXXXXC###","###X#########X###","###X#########X###","###CXXXXXXXXXC###","########C########")
                .aisle("####C#######C####","###XXXX#C#XXXX###","##X####XXX####X##","##X####XXX####X##","###XXXX#C#XXXX###","####C#######C####")
                .aisle("#####C#####C#####","##XXXX#####XXXX##","##X###X#C#X###X##","##X###X#C#X###X##","##XXXX#####XXXX##","#####C#####C#####")
                .aisle("#################","##XXX#C###C#XXX##","#X###XC###CX###X#","#X###XC###CX###X#","##XXX#C###C#XXX##","#################")
                .aisle("#######XXX#######","##XX###CCC###XX##","#X##X##CCC##X##X#","#X##X##CCC##X##X#","##XX###CCC###XX##","#######XXX#######")
                .aisle("##CC###XXX###CC##","#CXXC##CCC##CXXC#","CX##XC#CCC#CX##XC","CX##XC#CCC#CX##XC","#CXXC##CCC##CXXC#","##CC###XXX###CC##")
                .aisle("#######XXX#######","##XX###CCC###XX##","#X##X##CCC##X##X#","#X##X##CCC##X##X#","##XX###CCC###XX##","#######XXX#######")
                .aisle("#################","##XXX#C###C#XXX##","#X###XC###CX###X#","#X###XC###CX###X#","##XXX#C###C#XXX##","#################")
                .aisle("#####C#####C#####","##XXXX#####XXXX##","##X###X#C#X###X##","##X###X#C#X###X##","##XXXX#####XXXX##","#####C#####C#####")
                .aisle("####C#######C####","###XXXX#C#XXXX###","##X####XXX####X##","##X####XXX####X##","###XXXX#C#XXXX###","####C#######C####")
                .aisle("########C########","###CXXXXXXXXXC###","###X#########X###","###X#########X###","###CXXXXXXXXXC###","########C########")
                .aisle("########C########","#####XXXXXXX#####","##C#XX#####XX#C##","##C#XX#####XX#C##","#####XXXXXXX#####","########C########")
                .aisle("#################","########S########","######XXXXX######","######XXXXX######","########C########","#################")
                .aisle("#################","#################","########C########","########C########","#################","#################")
                .where('S', GATileEntities.FUSION_REACTOR_UHV, EnumFacing.SOUTH)
                .where('#', Blocks.AIR.getDefaultState())
                .where('C', GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_COIL_2))
                .where('X', GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_3))
                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[] { I18n.format("gregtech.multiblock.fusion_reactor_mk3.description") };
    }
}
