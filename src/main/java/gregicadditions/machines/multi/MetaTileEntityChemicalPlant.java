package gregicadditions.machines.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
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
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.BlockWireCoil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static gregtech.api.unification.material.Materials.Steel;


public class MetaTileEntityChemicalPlant extends RecipeMapMultiblockController {

    public static final List<GAMultiblockCasing.CasingType> CASING_ALLOYED = Arrays.asList(
            GAMultiblockCasing.CasingType.TIERED_HULL_LV,
            GAMultiblockCasing.CasingType.TIERED_HULL_MV,
            GAMultiblockCasing.CasingType.TIERED_HULL_HV,
            GAMultiblockCasing.CasingType.TIERED_HULL_EV,
            GAMultiblockCasing.CasingType.TIERED_HULL_IV,
            GAMultiblockCasing.CasingType.TIERED_HULL_LUV,
            GAMultiblockCasing.CasingType.TIERED_HULL_ZPM,
            GAMultiblockCasing.CasingType.TIERED_HULL_UV);
    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    private long maxVolatage = 0;
    protected int heatingCoilLevel;
    protected int heatingCoilDiscount;

    public MetaTileEntityChemicalPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.CHEMICAL_PLANT_RECIPES);
        this.recipeMapWorkable = new ChemicalPlantRecipeLogic(this);
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        //basically check minimal requirements for inputs count
        int itemInputsCount = abilities.getOrDefault(MultiblockAbility.IMPORT_ITEMS, Collections.emptyList())
                .stream().map(it -> (IItemHandler) it).mapToInt(IItemHandler::getSlots).sum();
        int fluidOutput = abilities.getOrDefault(MultiblockAbility.EXPORT_FLUIDS, Collections.emptyList()).size();
        int fluidInputsCount = abilities.getOrDefault(MultiblockAbility.IMPORT_FLUIDS, Collections.emptyList()).size();
        return fluidOutput >= 2 &&
                fluidInputsCount >= 4 && itemInputsCount >= 4 &&
                abilities.containsKey(MultiblockAbility.INPUT_ENERGY);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "RRRRR", "RRRRR", "RRRRR", "YYYYY")
                .aisle("XXXXX", "RCCCR", "RCCCR", "RCCCR", "YYYYY")
                .aisle("XXXXX", "RCTCR", "RCTCR", "RCTCR", "YYYYY")
                .aisle("XXXXX", "RCCCR", "RCCCR", "RCCCR", "YYYYY")
                .aisle("XXSXX", "RRRRR", "RRRRR", "RRRRR", "YYYYY")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('R', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS)))
                .where('T', tieredCasingPredicate())
                .where('C', heatingCoilPredicate())
                .build();

    }

    public static Predicate<BlockWorldState> tieredCasingPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAMultiblockCasing)) {
                return false;
            } else {
                GAMultiblockCasing blockWireCoil = (GAMultiblockCasing) blockState.getBlock();
                GAMultiblockCasing.CasingType tieredCasingType = blockWireCoil.getState(blockState);
                if (!CASING_ALLOYED.contains(tieredCasingType)) {
                    return false;
                }
                GAMultiblockCasing.CasingType currentCoilType = blockWorldState.getMatchContext().getOrPut("TieredCasing", tieredCasingType);
                return currentCoilType.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof BlockWireCoil))
                return false;
            BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
            BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
            BlockWireCoil.CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);
            return currentCoilType.getName().equals(coilType.getName());
        };
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        GAMultiblockCasing.CasingType currentTier = context.getOrDefault("TieredCasing", GAMultiblockCasing.CasingType.TIERED_HULL_ULV);
        switch (currentTier) {
            case TIERED_HULL_LV:
                maxVolatage = GTValues.V[GTValues.LV];
                break;
            case TIERED_HULL_MV:
                maxVolatage = GTValues.V[GTValues.MV];
                break;
            case TIERED_HULL_HV:
                maxVolatage = GTValues.V[GTValues.HV];
                break;
            case TIERED_HULL_EV:
                maxVolatage = GTValues.V[GTValues.EV];
                break;
            case TIERED_HULL_IV:
                maxVolatage = GTValues.V[GTValues.IV];
                break;
            case TIERED_HULL_LUV:
                maxVolatage = GTValues.V[GTValues.LuV];
                break;
            case TIERED_HULL_ZPM:
                maxVolatage = GTValues.V[GTValues.ZPM];
                break;
            case TIERED_HULL_UV:
                maxVolatage = GTValues.V[GTValues.UV];
                break;
            default:
                maxVolatage = 0;
                break;
        }
        BlockWireCoil.CoilType coilType = context.getOrDefault("CoilType", BlockWireCoil.CoilType.CUPRONICKEL);
        heatingCoilLevel = coilType.getLevel();
        heatingCoilDiscount = coilType.getEnergyDiscount();
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("Framework casing defined which tier you are alloyed to run it");
        tooltip.add(I18n.format("gtadditions.multiblock.chemical_plant.tooltip"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentString(String.format("Current Max Tiered Voltage: %d EU/t", this.maxVolatage)));
    }


    protected IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Steel);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Steel);
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return recipe.getEUt() < maxVolatage;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityChemicalPlant(metaTileEntityId);
    }

    public class ChemicalPlantRecipeLogic extends MultiblockRecipeLogic {


        public ChemicalPlantRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected void setupRecipe(Recipe recipe) {
            super.setupRecipe(recipe);
            int duration = getMaxProgress();
            int multiplier;
            switch (heatingCoilDiscount * (heatingCoilLevel + 2)) {
                case 3:
                    multiplier = 50;
                    break;
                case 6:
                    multiplier = 150;
                    break;
                case 12:
                    multiplier = 200;
                    break;
                case 24:
                    multiplier = 250;
                    break;
                case 32:
                    multiplier = 300;
                    break;
                case 48:
                    multiplier = 350;
                    break;
                case 64:
                    multiplier = 400;
                    break;
                case 96:
                    multiplier = 450;
                    break;
                case 160:
                    multiplier = 500;
                    break;
                default:
                    multiplier = 100;
                    break;
            }
            setMaxProgress(duration * multiplier);
        }
    }
}
