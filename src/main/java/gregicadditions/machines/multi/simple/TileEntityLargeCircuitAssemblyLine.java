package gregicadditions.machines.multi.simple;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.QubitConsumeRecipeLogic;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.multi.qubit.QubitRecipeMapMultiblockController;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;
import static gregtech.api.unification.material.Materials.Steel;

public class TileEntityLargeCircuitAssemblyLine extends QubitRecipeMapMultiblockController {

    public static final List<GAMultiblockCasing.CasingType> CASING1_ALLOWED = Arrays.asList(
            GAMultiblockCasing.CasingType.TIERED_HULL_IV,
            GAMultiblockCasing.CasingType.TIERED_HULL_LUV,
            GAMultiblockCasing.CasingType.TIERED_HULL_ZPM,
            GAMultiblockCasing.CasingType.TIERED_HULL_UV,
            GAMultiblockCasing.CasingType.TIERED_HULL_MAX);
    public static final List<GAMultiblockCasing2.CasingType> CASING2_ALLOWED = Arrays.asList(
            GAMultiblockCasing2.CasingType.TIERED_HULL_UHV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UEV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UIV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UMV,
            GAMultiblockCasing2.CasingType.TIERED_HULL_UXV);

    private long maxVoltage = 0;

    public TileEntityLargeCircuitAssemblyLine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES);
        this.recipeMapWorkable = new QubitConsumeRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeCircuitAssemblyLine(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(LEFT, DOWN, BACK)
                .aisle("GAG", "RTR", "COC")
                .aisle("GAG", "RTR", "FIF").setRepeatable(5)
                .aisle("GSG", "RTR", "FIF")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()))
                .where('F', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
                .where('O', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.EXPORT_ITEMS)))
                .where('I', tilePredicate((state, tile) -> tile.metaTileEntityId.equals(MetaTileEntities.ITEM_IMPORT_BUS[0].metaTileEntityId)))
                .where('G', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('A', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLER_CASING)).or(abilityPartPredicate(MultiblockAbility.INPUT_ENERGY)).or(abilityPartPredicate(GregicAdditionsCapabilities.INPUT_QBIT)))
                .where('R', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS)))
                .where('T', tieredCasing1Predicate().or(tieredCasing2Predicate()))
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

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        GAMultiblockCasing.CasingType currentTier = context.getOrDefault("TieredCasing", GAMultiblockCasing.CasingType.TIERED_HULL_ULV);
        switch (currentTier) {
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
            case TIERED_HULL_MAX:
                maxVoltage = GTValues.V[GTValues.MAX];
                break;
            default:
                maxVoltage = 0;
                break;
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.universal.framework.tooltip"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return recipe.getEUt() < maxVoltage;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(Steel);
    }

    protected IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Steel);
    }

}
