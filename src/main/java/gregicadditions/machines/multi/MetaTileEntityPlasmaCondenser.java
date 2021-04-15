package gregicadditions.machines.multi;

import gregicadditions.GAMaterials;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.recipes.GARecipeMaps;
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
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

public class MetaTileEntityPlasmaCondenser extends GARecipeMapMultiblockController {

    public MetaTileEntityPlasmaCondenser(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.PLASMA_CONDENSER_RECIPES);
        this.recipeMapWorkable = new GAMultiblockRecipeLogic(this) {
            @Override
            protected long getMaxVoltage() {
                return maxVoltage;
            }
        };
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    public long maxVoltage;

    @Override
    protected BlockPattern createStructurePattern() {
        return  FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX", "XXX", "XXX")
                .aisle("XXX", "XMX", "X#X", "XPX", "XXX")
                .aisle("XSX", "XXX", "XXX", "XXX", "XXX")
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .where('P', pumpPredicate())
                .where('M', motorPredicate())
                .build();
    }

    public static Predicate<BlockWorldState> motorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof MotorCasing)) {
                return false;
            } else {
                MotorCasing motorCasing = (MotorCasing) blockState.getBlock();
                MotorCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                MotorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Motor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> pumpPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof PumpCasing)) {
                return false;
            } else {
                PumpCasing motorCasing = (PumpCasing) blockState.getBlock();
                PumpCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                PumpCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Pump", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
        PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
        int min = Math.min(motor.getTier(), pump.getTier());
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return recipe.getEUt() < maxVoltage;
    }

    private IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(GAMaterials.EnrichedNaquadahAlloy);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(GAMaterials.EnrichedNaquadahAlloy);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityPlasmaCondenser(metaTileEntityId);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.PLASMA_ARC_FURNACE_OVERLAY;
    }
}
