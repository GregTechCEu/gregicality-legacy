package gregicadditions.machines.multi.advance;

import gregicadditions.client.ClientHandler;
import gregicadditions.item.fusion.GACryostatCasing;
import gregicadditions.item.fusion.GADivertorCasing;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.fusion.GAVacuumCasing;
import gregicadditions.machines.multi.simple.Tuple;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class TileEntityAdvFusionReactor extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    int tier;
    private int coilTier;
    private int cryostatTier;
    private int vacuumTier;
    private int divertorTier;
    private boolean canWork;

    public TileEntityAdvFusionReactor(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, int tier) {
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
            default:
                return GAMetaBlocks.FUSION_CASING.getState(GAFusionCasing.CasingType.ADV_FUSION_CASING);
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

    public static Predicate<BlockWorldState> cryostatPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GACryostatCasing)) {
                return false;
            } else {
                GACryostatCasing cryostatCasing = (GACryostatCasing) blockState.getBlock();
                GACryostatCasing.CasingType tieredCasingType = cryostatCasing.getState(blockState);
                GACryostatCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Cryostat", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> divertorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GADivertorCasing)) {
                return false;
            } else {
                GADivertorCasing divertorCasing = (GADivertorCasing) blockState.getBlock();
                GADivertorCasing.CasingType tieredCasingType = divertorCasing.getState(blockState);
                GADivertorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Divertor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> vacuumPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAVacuumCasing)) {
                return false;
            } else {
                GAVacuumCasing vacuumCasing = (GAVacuumCasing) blockState.getBlock();
                GAVacuumCasing.CasingType tieredCasingType = vacuumCasing.getState(blockState);
                GAVacuumCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Vacuum", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }


    public static Predicate<BlockWorldState> coilPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAFusionCasing)) {
                return false;
            } else {
                GAFusionCasing coil = (GAFusionCasing) blockState.getBlock();
                GAFusionCasing.CasingType tieredCasingType = coil.getState(blockState);
                if (tieredCasingType.getName().startsWith("adv_fusion_coil")) {
                    GAFusionCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Coil", tieredCasingType);
                    return currentCasing.getName().equals(tieredCasingType.getName());
                } else {
                    return false;
                }
            }
        };
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        GAFusionCasing.CasingType coil = context.getOrDefault("Coil", GAFusionCasing.CasingType.ADV_FUSION_COIL_1);
        coilTier = Integer.parseInt(coil.getName().substring(coil.getName().length() - 1));
        vacuumTier = context.getOrDefault("Vacuum", GAVacuumCasing.CasingType.VACUUM_1).getTier();
        divertorTier = context.getOrDefault("Divertor", GADivertorCasing.CasingType.DIVERTOR_1).getTier();
        cryostatTier = context.getOrDefault("Cryostat", GACryostatCasing.CasingType.CRYOSTAT_1).getTier();
        canWork = Math.min(Math.min(vacuumTier, divertorTier), cryostatTier) >= coilTier;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.coilTier = 0;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityAdvFusionReactor(metaTileEntityId, recipeMap, this.tier);
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        int requiredCoilTier = recipe.getIntegerProperty("coil_tier");
        return canWork && this.coilTier >= requiredCoilTier;
    }


    public static class AdvFusionRecipeLogic extends MultiblockRecipeLogic {

        public AdvFusionRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            List<IItemHandlerModifiable> itemInputs = ((RecipeMapMultiblockController) this.getMetaTileEntity()).getAbilities(MultiblockAbility.IMPORT_ITEMS);

            Tuple recipePerInput = itemInputs.stream()
                    .map(iItemHandlerModifiable -> new Tuple(recipeMap.findRecipe(maxVoltage, iItemHandlerModifiable, fluidInputs, 0), iItemHandlerModifiable))
                    .filter(tuple -> tuple.getRecipe() != null)
                    .findFirst().orElse(new Tuple(recipeMap.findRecipe(maxVoltage, inputs, fluidInputs, 0), inputs));

            Recipe matchingRecipe = recipeMap.findRecipe(maxVoltage, (IItemHandlerModifiable) Collections.emptyList(), fluidInputs, Integer.MAX_VALUE);
            if (recipePerInput.getRecipe() == null) {
                return null;
            }

            return null;


        }

    }
}
