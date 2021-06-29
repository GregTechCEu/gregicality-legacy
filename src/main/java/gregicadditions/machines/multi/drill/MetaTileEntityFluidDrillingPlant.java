package gregicadditions.machines.multi.drill;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.worldgen.PumpjackHandler;
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
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static gregtech.api.unification.material.Materials.*;

public class MetaTileEntityFluidDrillingPlant extends MultiblockWithDisplayBase { //todo add maintenance and soft hammering, cache values

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[] {
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY
    };

    private static final int TICKS_PER_SECOND = 20;

    private IEnergyContainer energyContainer;
    protected IMultipleTankHandler exportFluidHandler;

    private boolean isActive;
    private boolean canWork;

    private int voltageTier;
    private int overclockAmount;

    private final int rigTier;
    private final int rigBaseOperationSpeed;

    private final int usableFluidAmount;

    private Fluid veinFluid;

    private int[] currentLocation;

    public MetaTileEntityFluidDrillingPlant(ResourceLocation metaTileEntityId, int rigTier) {
        super(metaTileEntityId);
        this.isActive = false;
        this.canWork = true;

        this.rigTier = rigTier;
        this.rigBaseOperationSpeed = this.rigTier > 2 ? 32 : 8; // t1 = 8 ticks, 2+ = 32ticks

        this.usableFluidAmount = 50 * getTieredFluidMultiplier();

        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityFluidDrillingPlant(metaTileEntityId, this.rigTier);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        if (isActive)
            setActive(false);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.currentLocation = new int[] {
                getWorld().getChunk(getPos()).x,
                getWorld().getChunk(getPos()).z
        };
        veinFluid = getAvailableFluid();
        initializeAbilities();
    }

    private void initializeAbilities() {
        this.exportFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
        this.voltageTier = getVoltageTier();
        this.overclockAmount = Math.max(0, this.voltageTier - this.rigTier);
    }

    private void resetTileAbilities() {
        this.exportFluidHandler = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    public boolean drainEnergy() {
        // do not allow working if energy hatch is below rig tier
        if (energyContainer.getInputVoltage() < getMaxVoltage())
            return false;

        // drain energy when running
        if (energyContainer.getEnergyStored() >= getMaxVoltage()) {
            energyContainer.removeEnergy(getMaxVoltage());
            return true;
        }

        return false;
    }

    public long getMaxVoltage() {
        return GAValues.V[voltageTier];
    }

    public int getVoltageTier() {
        return Math.max(rigTier, GAUtility.getTierByVoltage(energyContainer.getInputVoltage()));
    }



    @Override
    protected void updateFormedValid() {
        if (getWorld().isRemote) {
            return;
        }

        if (!this.canWork) {
            if (isActive)
                setActive(false);
            return;
        }

        if (canFillFluidExport() && drainEnergy()) {
            if (!isActive)
                setActive(true);
        } else {
            if (isActive)
                setActive(false);
            return;
        }

        if (getOffsetTimer() % 20 == 0) {
            // Get the residual fluid and total fluid in the vein
            int residual = getResidualFluidAmount();
            int fluidVeinAmount = getAvailableFluidAmount();


            int fluidAmountToProduce;

            // Check if there is enough fluid in the vein to produce the regular amount
            if (fluidVeinAmount > 0 || residual > 0) {

                fluidAmountToProduce = fluidVeinAmount;

                // If there is no fluid left, check if there is any residual fluid in the vein to produce
                if (fluidVeinAmount <= 0) {
                    fluidAmountToProduce = residual * this.rigTier;
                }

                // Get the FluidStack to produce
                FluidStack out = getFluidStackToProduce(fluidAmountToProduce);

                // Apply efficiency boosts given by better rigs for vein depletion
                int depleted = getFluidAmountToDrain(fluidAmountToProduce);

                // Produce the fluid
                exportFluidHandler.fill(out, true);

                // Deplete the vein's fluid
                depleteFluid(depleted);
            }
        }
    }

    // Check if there is room in the output hatch
    public boolean canFillFluidExport() {
        if(veinFluid == null)
            return false;

        return exportFluidHandler.fill(new FluidStack(veinFluid, 1), false) > 0;
    }

    /**
     * Get the fluid to fill the output hatch with
     * @param fluidAmount amount of fluid to produce
     * @return FluidStack to produce with proper quantity
     */
    public FluidStack getFluidStackToProduce(int fluidAmount) {
        // Use everything left in the vein if there is too little to produce the normal amount
        return new FluidStack(veinFluid, getFluidAmountForUse(fluidAmount));
    }

    /**
     * Apply a potential efficiency bonus to the fluid production value used for vein depletion
     * @param fluidAmount amount of fluid to drain
     * @return amount of fluid to deplete from the vein
     */
    public int getFluidAmountToDrain(int fluidAmount) {
        return getFluidAmountForUse(fluidAmount) / getTieredFluidMultiplier();
    }

    /**
     * Get the correct amount of fluid to use for calculations
     * @param fluidAmount the amount to compare the overclocked fluid quantity against
     * @return the smaller of the overclocked fluid quantity and the parameter
     */
    public int getFluidAmountForUse(int fluidAmount) {
        return Math.min(overclockFluidProduction(), fluidAmount);
    }

    /**
     * Instead of outputting faster than every second, adjust the output rate to be equivalent for per second output
     *
     * @return overclocked amount to fluid produced every 20 ticks
     */
    private int overclockFluidProduction() {

        // Total times dividing speed by 2 from overclocking
        int speedIncrease = (int) Math.pow(2, overclockAmount);

        // Overclock the speed
        double overclockedSpeed = (double) rigBaseOperationSpeed / speedIncrease;

        // Fluid Produced Per Standard Operation / Overclocked Speed * TPS = Fluid / Second
        return (int) (getChunkFluidDrainAmount() / overclockedSpeed * TICKS_PER_SECOND);
    }

    /**
     * @return amount of fluid produced per the rig's base time pre overclocking
     */
    private int getChunkFluidDrainAmount() {
        // Total usable fluid for current rig tier * (0.5 + 0.25 * overclockAmount) - from GT5u
        return (int) (usableFluidAmount * (0.5 + 0.25 * overclockAmount));
    }

    // T1 Rig = 1x, T2 = 16x, T3 = 64x - from gt5u
    public int getTieredFluidMultiplier() {
        if (rigTier > 2)
            return (int) Math.pow(4, rigTier - 1);
        return 1;
    }

    /**
     *
     * @return the amount of available fluid in the vein
     */
    public int getAvailableFluidAmount() {
        return PumpjackHandler.getFluidAmount(getWorld(), currentLocation[0],  currentLocation[1]);
    }

    /**
     *
     * @return the fluid in the vein
     */
    public Fluid getAvailableFluid() {
        return PumpjackHandler.getFluid(getWorld(), currentLocation[0], currentLocation[1]);
    }

    /**
     *
     * @return the residual fluid in the vein
     */
    public int getResidualFluidAmount() {
        return PumpjackHandler.getResidualFluid(getWorld(),  currentLocation[0], currentLocation[1]);
    }

    /**
     * Deplete fluid from the vein
     * @param amount the amount of fluid to remove from the vein
     */
    public void depleteFluid(int amount) {
        PumpjackHandler.depleteFluid(getWorld(), currentLocation[0], currentLocation[1], amount);
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        // Check for 1 fluid output, 1+ energy input, 1 maintenance hatch
        int fluidOutputsCount = abilities.getOrDefault(MultiblockAbility.EXPORT_FLUIDS, Collections.emptyList()).size();
//        int maintenanceCount = abilities.getOrDefault(GregicAdditionsCapabilities.MAINTENANCE_HATCH, Collections.emptyList()).size();

        return fluidOutputsCount == 1 && abilities.containsKey(MultiblockAbility.INPUT_ENERGY) /*&& maintenanceCount == 1) */;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);

        if (isStructureFormed()) {
            PumpjackHandler.OilWorldInfo oilWorldInfo = PumpjackHandler.getOilWorldInfo(getWorld(), currentLocation[0], currentLocation[1]);
            if (this.isStructureFormed()) {
                if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                    long maxVoltage = energyContainer.getInputVoltage();
                    String voltageName = GAValues.VN[GAUtility.getTierByVoltage(maxVoltage)];
                    textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
                }
            }
            if (oilWorldInfo == null || oilWorldInfo.getType() == null) {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.no_fluid").setStyle(new Style().setColor(TextFormatting.RED)));
            } else {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.rig_production", getAvailableFluidAmount() <= 0 ? getResidualFluidAmount() * this.rigTier : overclockFluidProduction()));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.fluid_drain", getFluidAmountToDrain(getFluidAmountForUse(getAvailableFluidAmount()))));

                ITextComponent fluidName = new TextComponentTranslation(oilWorldInfo.getType().getFluid().getUnlocalizedName());
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.fluid", fluidName));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.chunk_capacity", oilWorldInfo.capacity / 1000));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.chunk_remaining", oilWorldInfo.current / 1000));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.replenish", oilWorldInfo.type.replenishRate * voltageTier));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.drilling_rig.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.drilling_rig.tooltip.2", GAValues.VN[this.rigTier], GAValues.VN[this.rigTier == 2 ? 5 : this.rigTier == 3 ? 8 : 9]));
        tooltip.add(I18n.format("gtadditions.multiblock.drilling_rig.tooltip.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.drilling_rig.tooltip.4", overclockFluidProduction(), GAValues.VN[this.rigTier]));
        tooltip.add(I18n.format("gtadditions.multiblock.drilling_rig.tooltip.5", getTieredFluidMultiplier()));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "#F#", "#F#", "#F#", "###", "###", "###")
                .aisle("XXX", "FXF", "FXF", "FXF", "#F#", "#F#", "#F#")
                .aisle("XSX", "#F#", "#F#", "#F#", "###", "###", "###")
                .setAmountAtLeast('L', 4)
                .where('L', statePredicate(getCasingState()))
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('F', statePredicate(getFrameState()))
                .where('#', (tile) -> true)
                .build();
    }

    public IBlockState getCasingState() {
        switch (this.rigTier) {
            case 3:
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
            case 4:
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
            default:
                return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
        }
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        switch (this.rigTier) {
            case 3:
                return Textures.CLEAN_STAINLESS_STEEL_CASING;
            case 4:
                return Textures.STABLE_TITANIUM_CASING;
            default:
                return Textures.SOLID_STEEL_CASING;
        }
    }

    public IBlockState getFrameState() {
        switch (this.rigTier) {
            case 3:
                return MetaBlocks.FRAMES.get(StainlessSteel).getDefaultState();
            case 4:
                return MetaBlocks.FRAMES.get(Titanium).getDefaultState();
            default:
                return MetaBlocks.FRAMES.get(Steel).getDefaultState();
        }

    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
    }

    protected void setActive(boolean active) {
        this.isActive = active;
        markDirty();
        if (!getWorld().isRemote) {
            writeCustomData(1, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 1) {
            this.isActive = buf.readBoolean();
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive);
    }
}
