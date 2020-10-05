package gregicadditions.machines.multi.advance;

import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAFusionCasing;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class FusionReactorAdv extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    int tier;

    public FusionReactorAdv(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int tier) {
        super(metaTileEntityId, recipeMap);
        this.tier = tier;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
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
                .aisle("########C########","#####IIIXIII#####","##C#XX#####XX#C##","##C#XX#####XX#C##","#####XXXXXXX#####","########C########")
                .aisle("#################","########S########","######XXXXX######","######XXXXX######","########C########","#################")
                .aisle("#################","#################","########C########","########C########","#################","#################")
                .where('S', selfPredicate())
                .where('#', (tile) -> true)
                .where('C', statePredicate(getCoilState()))
                .where('X', statePredicate(getCasingState()))
                .where('I', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.FUSION_TEXTURE;
    }

    private IBlockState getCasingState() {
        switch (tier) {
            case 9:
                return GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_3);
            default:
                return GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_4);
        }
    }

    private IBlockState getCoilState() {
        switch (tier) {
            case 9:
                return GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_COIL_2);
            default:
                return GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.FUSION_COIL_3);
        }
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new FusionReactorAdv(metaTileEntityId, recipeMap, this.tier);
    }
}
