package gregicadditions.machines.multi.impl;

import gregicadditions.recipes.impl.nuclear.HotCoolantRecipe;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMap;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.util.GTUtility;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.function.Supplier;

public class HotCoolantRecipeLogic extends MTETrait implements IControllable {

    public final HotCoolantRecipeMap recipeMap;
    protected HotCoolantRecipe previousRecipe;

    protected final Supplier<IEnergyContainer> energyContainer;
    protected final Supplier<IMultipleTankHandler> fluidTank;
    public final long maxVoltage;

    private int recipeDurationLeft;
    private long recipeOutputVoltage;

    private boolean isActive;
    private boolean workingEnabled = true;
    private boolean wasActiveAndNeedsUpdate = false;

    public HotCoolantRecipeLogic(MetaTileEntity metaTileEntity, HotCoolantRecipeMap recipeMap, Supplier<IEnergyContainer> energyContainer, Supplier<IMultipleTankHandler> fluidTank, long maxVoltage) {
        super(metaTileEntity);
        this.recipeMap = recipeMap;
        this.energyContainer = energyContainer;
        this.fluidTank = fluidTank;
        this.maxVoltage = maxVoltage;
    }

    public long getRecipeOutputVoltage() {
        return recipeOutputVoltage;
    }

    @Override
    public String getName() {
        return "HotCoolantRecipeMapWorkable";
    }

    @Override
    public int getNetworkID() {
        return TraitNetworkIds.TRAIT_ID_WORKABLE;
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return null;
    }

    @Override
    public void update() {
        if (getMetaTileEntity().getWorld().isRemote) return;
        if (workingEnabled) {
            if (recipeDurationLeft > 0) {
                if (energyContainer.get().getEnergyCanBeInserted() >=
                        recipeOutputVoltage || shouldVoidExcessiveEnergy()) {
                    energyContainer.get().addEnergy(recipeOutputVoltage);
                    if (--this.recipeDurationLeft == 0) {
                        this.wasActiveAndNeedsUpdate = true;
                    }
                }
            }
            if (recipeDurationLeft == 0) {
                tryAcquireNewRecipe();
            }
        }
        if (wasActiveAndNeedsUpdate) {
            setActive(false);
            this.wasActiveAndNeedsUpdate = false;
        }
    }

    protected boolean shouldVoidExcessiveEnergy() {
        return false;
    }

    private void tryAcquireNewRecipe() {
        IMultipleTankHandler fluidTanks = this.fluidTank.get();
        for (IFluidTank fluidTank : fluidTanks) {
            FluidStack tankContents = fluidTank.getFluid();
            if (tankContents != null && tankContents.amount > 0) {
                int fuelAmountUsed = tryAcquireNewRecipe(tankContents);
                if (fuelAmountUsed > 0) {
                    fluidTank.drain(fuelAmountUsed, true);
                    break; //recipe is found and ready to use
                }
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    private int tryAcquireNewRecipe(FluidStack fluidStack) {
        HotCoolantRecipe currentRecipe;
        if (previousRecipe != null && previousRecipe.matches(getMaxVoltage(), fluidStack)) {
            //if previous recipe still matches inputs, try to use it
            currentRecipe = previousRecipe;
        } else {
            //else, try searching new recipe for given inputs
            currentRecipe = recipeMap.findRecipe(getMaxVoltage(), fluidStack);
            //if we found recipe that can be buffered, buffer it
            if (currentRecipe != null) {
                this.previousRecipe = currentRecipe;
            }
        }
        if (currentRecipe != null && checkRecipe(currentRecipe)) {
            int fuelAmountToUse = calculateFuelAmount(currentRecipe);
            if (fluidStack.amount >= fuelAmountToUse) {
                this.recipeDurationLeft = calculateRecipeDuration(currentRecipe);
                this.recipeOutputVoltage = startRecipe(currentRecipe, fuelAmountToUse, recipeDurationLeft);
                if (wasActiveAndNeedsUpdate) {
                    this.wasActiveAndNeedsUpdate = false;
                } else {
                    setActive(true);
                }
                return fuelAmountToUse;
            }
        }
        return 0;
    }

    protected boolean checkRecipe(HotCoolantRecipe recipe) {
        return true;
    }

    public long getMaxVoltage() {
        return maxVoltage;
    }

    protected int calculateFuelAmount(HotCoolantRecipe currentRecipe) {
        return currentRecipe.getRecipeFluid().amount * getVoltageMultiplier(getMaxVoltage(), currentRecipe.getMinVoltage());
    }

    protected int calculateRecipeDuration(HotCoolantRecipe currentRecipe) {
        return currentRecipe.getDuration();
    }

    /**
     * Performs preparations for starting given recipe and determines it's output voltage
     *
     * @return recipe's output voltage
     */
    protected long startRecipe(HotCoolantRecipe currentRecipe, int fuelAmountUsed, int recipeDuration) {
        return getMaxVoltage();
    }

    public static int getVoltageMultiplier(long maxVoltage, long minVoltage) {
        return (int) (maxVoltage / minVoltage);
    }

    public static long getTieredVoltage(long voltage) {
        return GTValues.V[GTUtility.getTierByVoltage(voltage)];
    }

    private void setActive(boolean active) {
        this.isActive = active;
        if (!metaTileEntity.getWorld().isRemote) {
            metaTileEntity.markDirty();
            writeCustomData(1, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled) {
        this.workingEnabled = workingEnabled;
        metaTileEntity.markDirty();
    }

    @Override
    public boolean isWorkingEnabled() {
        return workingEnabled;
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == 1) {
            this.isActive = buf.readBoolean();
            getMetaTileEntity().getHolder().scheduleChunkForRenderUpdate();
        }
    }

    @Override
    public void writeInitialData(PacketBuffer buf) {
        buf.writeBoolean(this.isActive);
    }

    @Override
    public void receiveInitialData(PacketBuffer buf) {
        this.isActive = buf.readBoolean();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setBoolean("WorkEnabled", this.workingEnabled);
        compound.setInteger("RecipeDurationLeft", this.recipeDurationLeft);
        if (recipeDurationLeft > 0) {
            compound.setLong("RecipeOutputVoltage", this.recipeOutputVoltage);
        }
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        if (!compound.hasKey("WorkEnabled", Constants.NBT.TAG_BYTE)) {
            //change working mode only if there is a tag compound with it's value
            this.workingEnabled = compound.getBoolean("WorkEnabled");
        }
        this.recipeDurationLeft = compound.getInteger("RecipeDurationLeft");
        if (recipeDurationLeft > 0) {
            this.recipeOutputVoltage = compound.getLong("RecipeOutputVoltage");
        }
        this.isActive = recipeDurationLeft > 0;
    }

}
