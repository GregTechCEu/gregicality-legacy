package gregicadditions.machines.multi;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
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
import net.minecraft.util.text.TextComponentTranslation;
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

    public static final List<GAMultiblockCasing.CasingType> CASING1_ALLOWED = Arrays.asList(
            GAMultiblockCasing.CasingType.TIERED_HULL_LV,
            GAMultiblockCasing.CasingType.TIERED_HULL_MV,
            GAMultiblockCasing.CasingType.TIERED_HULL_HV,
            GAMultiblockCasing.CasingType.TIERED_HULL_EV,
            GAMultiblockCasing.CasingType.TIERED_HULL_IV,
            GAMultiblockCasing.CasingType.TIERED_HULL_LUV,
            GAMultiblockCasing.CasingType.TIERED_HULL_ZPM,
            GAMultiblockCasing.CasingType.TIERED_HULL_UV);
    public static final List<GAMultiblockCasing2.CasingType> CASING2_ALLOWED = Arrays.asList(
            GAMultiblockCasing2.CasingType.TIERED_HULL_UHV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UEV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UIV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UMV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UXV);
    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    private long maxVoltage = 0;
    protected int heatingCoilLevel = 1;
    protected int heatingCoilDiscount = 1;

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
                .where('T', tieredCasing1Predicate().or(tieredCasing2Predicate()))
                .where('C', heatingCoilPredicate())
                .build();

    }

    public static Predicate<BlockWorldState> tieredCasing1Predicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAMultiblockCasing)) {
                return false;
            } else {
                GAMultiblockCasing blockWireCoil = (GAMultiblockCasing) blockState.getBlock();
                GAMultiblockCasing.CasingType tieredCasingType = blockWireCoil.getState(blockState);
                if (!CASING1_ALLOWED.contains(tieredCasingType)) {
                    return false;
                }
                GAMultiblockCasing.CasingType currentCoilType = blockWorldState.getMatchContext().getOrPut("TieredCasing", tieredCasingType);
                return currentCoilType.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> tieredCasing2Predicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAMultiblockCasing2)) {
                return false;
            } else {
                GAMultiblockCasing2 blockWireCoil = (GAMultiblockCasing2) blockState.getBlock();
                GAMultiblockCasing2.CasingType tieredCasingType = blockWireCoil.getState(blockState);
                if (!CASING2_ALLOWED.contains(tieredCasingType)) {
                    return false;
                }
                GAMultiblockCasing2.CasingType currentCoilType = blockWorldState.getMatchContext().getOrPut("TieredCasing", tieredCasingType);
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
                maxVoltage = GTValues.V[GTValues.LV];
                break;
            case TIERED_HULL_MV:
                maxVoltage = GTValues.V[GTValues.MV];
                break;
            case TIERED_HULL_HV:
                maxVoltage = GTValues.V[GTValues.HV];
                break;
            case TIERED_HULL_EV:
                maxVoltage = GTValues.V[GTValues.EV];
                break;
            case TIERED_HULL_IV:
                maxVoltage = GTValues.V[GTValues.IV];
                break;
            case TIERED_HULL_LUV:
                maxVoltage = GTValues.V[GTValues.LuV];
                break;
            case TIERED_HULL_ZPM:
                maxVoltage = GTValues.V[GTValues.ZPM];
                break;
            case TIERED_HULL_UV:
                maxVoltage = GTValues.V[GTValues.UV];
                break;
            default:
                maxVoltage = 0;
                break;
        }
        BlockWireCoil.CoilType coilType = context.getOrDefault("CoilType", BlockWireCoil.CoilType.CUPRONICKEL);
        heatingCoilLevel = coilType.getLevel();
        heatingCoilDiscount = coilType.getEnergyDiscount();
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.universal.framework.tooltip"));
        tooltip.add(I18n.format("gtadditions.multiblock.chemical_plant.tooltip"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.multi_furnace.heating_coil_level", heatingCoilLevel));
            textList.add(new TextComponentTranslation("gregtech.multiblock.multi_furnace.heating_coil_discount", heatingCoilDiscount));
        }
        textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));
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
        return recipe.getEUt() < maxVoltage;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityChemicalPlant(metaTileEntityId);
    }

    public class ChemicalPlantRecipeLogic extends LargeSimpleRecipeMapMultiblockController.LargeSimpleMultiblockRecipeLogic {

        public ChemicalPlantRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity, 100 / heatingCoilDiscount, 100, 100, heatingCoilLevel);
        }

    }
}
