package gregicadditions.machines.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PistonCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.multi.MultiUtils;
import gregicadditions.machines.multi.simple.MultiRecipeMapMultiblockController;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.*;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.InventoryUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

import static gregicadditions.client.ClientHandler.BABBITT_ALLOY_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class TileEntityAdvancedDistillationTower extends MultiRecipeMapMultiblockController {

    public static final List<GAMultiblockCasing.CasingType> CASING1_ALLOWED = Arrays.asList(
            GAMultiblockCasing.CasingType.TIERED_HULL_ULV,
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

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};

    public TileEntityAdvancedDistillationTower(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, 100, 100, 100, GAConfig.multis.distillationTower.distillationMultiplier,
                new RecipeMap<?>[]{RecipeMaps.DISTILLATION_RECIPES, RecipeMaps.DISTILLERY_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityAdvancedDistillationTower(metaTileEntityId, RecipeMaps.DISTILLATION_RECIPES);
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        return Textures.DISTILLERY_OVERLAY;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        Predicate<BlockWorldState> fluidExportPredicate = this.countMatch("HatchesAmount", abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS));
        Predicate<PatternMatchContext> exactlyOneHatch = (context) -> {
            return context.getInt("HatchesAmount") == 1;
        };

        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("YSY", "YYY", "YYY")
                .aisle("XXX", "X#X", "XXX").setRepeatable(0, 11)
                .aisle("XXX", "XXX", "XXX")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('X', fluidExportPredicate.or(statePredicate(getCasingState())).or(abilityPartPredicate(GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY)))
                .where('#', tieredCasing1Predicate().or(tieredCasing2Predicate()))
                .validateLayer(1, exactlyOneHatch)
                .validateLayer(2, exactlyOneHatch)
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
                long maxVoltage;
                switch (tieredCasingType) {
                    case TIERED_HULL_LV:
                        maxVoltage = GAValues.V[GAValues.LV];
                        break;
                    case TIERED_HULL_MV:
                        maxVoltage = GAValues.V[GAValues.MV];
                        break;
                    case TIERED_HULL_HV:
                        maxVoltage = GAValues.V[GAValues.HV];
                        break;
                    case TIERED_HULL_EV:
                        maxVoltage = GAValues.V[GAValues.EV];
                        break;
                    case TIERED_HULL_IV:
                        maxVoltage = GAValues.V[GAValues.IV];
                        break;
                    case TIERED_HULL_LUV:
                        maxVoltage = GAValues.V[GAValues.LuV];
                        break;
                    case TIERED_HULL_ZPM:
                        maxVoltage = GAValues.V[GAValues.ZPM];
                        break;
                    case TIERED_HULL_UV:
                        maxVoltage = GAValues.V[GAValues.UV];
                        break;
                    case TIERED_HULL_MAX:
                        maxVoltage = GAValues.V[GAValues.MAX];
                        break;
                    default:
                        maxVoltage = 0;
                        break;
                }
                long currentMaxVoltage = blockWorldState.getMatchContext().getOrPut("maxVoltage", maxVoltage);
                return currentMaxVoltage == maxVoltage;
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
                long maxVoltage;
                switch (tieredCasingType) {
                    case TIERED_HULL_UHV:
                        maxVoltage = GAValues.V[GAValues.UHV];
                        break;
                    case TIERED_HULL_UEV:
                        maxVoltage = GAValues.V[GAValues.UEV];
                        break;
                    case TIERED_HULL_UIV:
                        maxVoltage = GAValues.V[GAValues.UIV];
                        break;
                    case TIERED_HULL_UMV:
                        maxVoltage = GAValues.V[GAValues.UMV];
                        break;
                    case TIERED_HULL_UXV:
                        maxVoltage = GAValues.V[GAValues.UXV];
                        break;
                    default:
                        maxVoltage = 0;
                        break;
                }
                long currentMaxVoltage = blockWorldState.getMatchContext().getOrPut("maxVoltage", maxVoltage);
                return currentMaxVoltage == maxVoltage;
            }
        };
    }

    @Override
    protected void formStructure(PatternMatchContext context) { //todo unknown why this is necessary, reports some voltages incorrectly under some conditions
        super.formStructure(context);
        maxVoltage = Math.min(context.getOrDefault("maxVoltage", 0L), this.getEnergyContainer().getInputVoltage()/this.getEnergyContainer().getInputAmperage());
    }

    private static final IBlockState defaultCasingState = METAL_CASING_1.getState(MetalCasing1.CasingType.BABBITT_ALLOY);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.distillationTower.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.distillationTower.casingMaterial, BABBITT_ALLOY_CASING);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description1"));
        tooltip.add(I18n.format("gregtech.multiblock.universal.framework.tooltip"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description2"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description3"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description4"));
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        T capabilityResult = super.getCapability(capability, side);
        if (capabilityResult == null && capability == GregicAdditionsCapabilities.MULTI_RECIPE_CAPABILITY) {
            return (T) this;
        }
        return capabilityResult;
    }
}
