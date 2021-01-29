package gregicadditions.machines.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregicadditions.GAMaterials;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.FieldGenCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.components.SensorCasing;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MetaTileEntityCosmicRayDetector extends MultiblockWithDisplayBase {

    public MetaTileEntityCosmicRayDetector(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        reinitializeStructurePattern();
    }

    @Override
    protected void updateFormedValid() {

        if (getWorld().isRemote) {
            return;
        }

        if (getTimer() % 100 == 13) {
            canSeeSky = this.getWorld().canSeeSky(this.getPos().up(5));
        }

        if (getTimer() % 20 == 8) {
            hasEnoughEnergy = drainEnergy();
        }

        if (canSeeSky && hasEnoughEnergy) {
            drainEnergy();
        }
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    private long maxVoltage = 0;
    private boolean canSeeSky = false;
    private boolean hasEnoughEnergy = false;
    private IEnergyContainer energyContainer;
    protected IMultipleTankHandler exportFluidHandler;
    private int amount = 0;

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("##XXX##", "##XXX##", "#######", "#######", "##xxx##")
                .aisle("#XXXXX#", "#XXXXX#", "###X###", "##XXX##", "#xx#xx#")
                .aisle("XXXXXXX", "XXXFXXX", "##XXX##", "#XXXXX#", "xx###xx")
                .aisle("XXXXXXX", "XXFPFXX", "#XXFXX#", "#XXEXX#", "x##s##x")
                .aisle("XXXXXXX", "XXXFXXX", "##XXX##", "#XXXXX#", "xx###xx")
                .aisle("#XXXXX#", "#XXXXX#", "###X###", "##XXX##", "#xx#xx#")
                .aisle("##XSX##", "##XXX##", "#######", "#######", "##xxx##")
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('x', statePredicate(getSecondaryCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', (tile) -> true)
                .where('F', fieldGenPredicate())
                .where('E', emitterPredicate())
                .where('s', sensorPredicate())
                .where('P', pumpPredicate())
                .build();
    }

    public static Predicate<BlockWorldState> fieldGenPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof FieldGenCasing)) {
                return false;
            } else {
                FieldGenCasing motorCasing = (FieldGenCasing) blockState.getBlock();
                FieldGenCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                FieldGenCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("FieldGen", tieredCasingType);
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

    public static Predicate<BlockWorldState> emitterPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof EmitterCasing)) {
                return false;
            } else {
                EmitterCasing motorCasing = (EmitterCasing) blockState.getBlock();
                EmitterCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                EmitterCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Emitter", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> sensorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof SensorCasing)) {
                return false;
            } else {
                SensorCasing motorCasing = (SensorCasing) blockState.getBlock();
                SensorCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                SensorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Sensor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));
            if (!canSeeSky)
                textList.add(new TextComponentTranslation("gtadditions.multiblock.cosmic_ray_detector.tooltip.1")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
            if (!hasEnoughEnergy)
                textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
            if (hasEnoughEnergy && canSeeSky) {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.cosmic_ray_detector.tooltip.5", this.amount));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.cosmic_ray_detector.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.cosmic_ray_detector.tooltip.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.cosmic_ray_detector.tooltip.4"));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        EmitterCasing.CasingType emitter = context.getOrDefault("Emitter", EmitterCasing.CasingType.EMITTER_LV);
        SensorCasing.CasingType sensor = context.getOrDefault("Sensor", SensorCasing.CasingType.SENSOR_LV);
        PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
        FieldGenCasing.CasingType fieldGen = context.getOrDefault("FieldGen", FieldGenCasing.CasingType.FIELD_GENERATOR_LV);
        int min = Collections.min(Arrays.asList(emitter.getTier(), sensor.getTier(), pump.getTier(), fieldGen.getTier()));
        maxVoltage = (long) (Math.pow(4, min) * 8);
        this.initializeAbilities();
        amount = getAmount();
    }

    private int getAmount() {
        double amount = Math.min(((double) this.getPos().getY()) / (256-5), 1);
        amount = Math.max(amount, 0);
        amount *= 35;
        amount *= getOverclock();
        amount += 5;
        return (int) amount;
    }

    private void initializeAbilities() {
        this.exportFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.exportFluidHandler = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    private boolean drainEnergy() {
        if (maxVoltage >= getVoltage() && energyContainer.getEnergyStored() >= getVoltage() &&
                exportFluidHandler.fill(GAMaterials.HeavyLeptonMix.getFluid(1), false) > 0) {
            energyContainer.removeEnergy(getVoltage() * getOverclock());
            exportFluidHandler.fill(GAMaterials.HeavyLeptonMix.getFluid(this.amount), true);
            return true;
        }
        return false;
    }

    public long getOverclock() {
        int tierDifference = GAUtility.getTierByVoltage(energyContainer.getInputVoltage()) - GAValues.UHV;
        return (long) Math.floor(Math.pow(2, tierDifference));
    }

    private long getVoltage() {
        return GAValues.V[GAValues.UHV];
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        int fluidOutputsCount = abilities.getOrDefault(MultiblockAbility.EXPORT_FLUIDS, Collections.emptyList()).size();
        return fluidOutputsCount >= 1 &&
                abilities.containsKey(MultiblockAbility.INPUT_ENERGY);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
        this.resetTileAbilities();
    }

    private IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(GAMaterials.Quantum);
    }

    private IBlockState getSecondaryCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Materials.Tritanium);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(GAMaterials.Quantum);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isStructureFormed() & hasEnoughEnergy);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityCosmicRayDetector(metaTileEntityId);
    }
}
