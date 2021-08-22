package gregicadditions.capabilities.impl;

import gregicadditions.capabilities.GATraitNetworkIds;
import gregicadditions.capabilities.GregicalityCapabilities;
import gregicadditions.capabilities.IQubitContainer;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.util.function.Predicate;

public class QubitContainerHandler extends MTETrait implements IQubitContainer {

    public static final int TRAIT_ID_QBIT_CONTAINER = 10;

    private final long maxCapacity;
    private long qubitStored;

    private final long maxInputQubit;
    private final long maxInputParallel;

    private final long maxOutputQubit;
    private final long maxOutputParallel;

    private Predicate<EnumFacing> sideInputCondition;
    private Predicate<EnumFacing> sideOutputCondition;

    public QubitContainerHandler(MetaTileEntity tileEntity, long maxCapacity, long maxInputQubit, long maxInputParallel, long maxOutputQubit, long maxOutputParallel) {
        super(tileEntity);
        this.maxCapacity = maxCapacity;
        this.maxInputQubit = maxInputQubit;
        this.maxInputParallel = maxInputParallel;
        this.maxOutputQubit = maxOutputQubit;
        this.maxOutputParallel = maxOutputParallel;
    }

    public void setSideInputCondition(Predicate<EnumFacing> sideInputCondition) {
        this.sideInputCondition = sideInputCondition;
    }

    public void setSideOutputCondition(Predicate<EnumFacing> sideOutputCondition) {
        this.sideOutputCondition = sideOutputCondition;
    }

    public static QubitContainerHandler emitterContainer(MetaTileEntity tileEntity, long maxCapacity, long maxOutputVoltage, long maxOutputAmperage) {
        return new QubitContainerHandler(tileEntity, maxCapacity, 0L, 0L, maxOutputVoltage, maxOutputAmperage);
    }

    public static QubitContainerHandler receiverContainer(MetaTileEntity tileEntity, long maxCapacity, long maxInputVoltage, long maxInputAmperage) {
        return new QubitContainerHandler(tileEntity, maxCapacity, maxInputVoltage, maxInputAmperage, 0L, 0L);
    }

    @Override
    public String getName() {
        return "QubitContainer";
    }

    @Override
    public int getNetworkID() {
        return TRAIT_ID_QBIT_CONTAINER;
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        if (capability == GregicalityCapabilities.QBIT_CAPABILITY) {
            return (T) this;
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setLong("QubitStored", qubitStored);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        this.qubitStored = compound.getLong("QubitStored");
        notifyEnergyListener(true);
    }

    @Override
    public long getQubitStored() {
        return this.qubitStored;
    }

    public void setQubitStored(long qubitStored) {
        this.qubitStored = qubitStored;
        if (!metaTileEntity.getWorld().isRemote) {
            metaTileEntity.markDirty();
            notifyEnergyListener(false);
        }
    }

    protected void notifyEnergyListener(boolean isInitialChange) {
        if (metaTileEntity instanceof IQubitChangeListener) {
            ((IQubitChangeListener) metaTileEntity).onEnergyChanged(this, isInitialChange);
        }
    }

    @Override
    public void update() {
        if (getMetaTileEntity().getWorld().isRemote)
            return;
        if (getQubitStored() >= getOutputQubit() && getOutputQubit() > 0 && getOutputParallel() > 0) {
            long outputQubit = getOutputQubit();
            long outputParallel = Math.min(getQubitStored() / outputQubit, getOutputParallel());
            if (outputParallel == 0) return;
            long currentParallel = 0;
            for (EnumFacing side : EnumFacing.VALUES) {
                if (!outputsQubit(side)) continue;
                TileEntity tileEntity = metaTileEntity.getWorld().getTileEntity(metaTileEntity.getPos().offset(side));
                EnumFacing oppositeSide = side.getOpposite();
                if (tileEntity != null && tileEntity.hasCapability(GregicalityCapabilities.QBIT_CAPABILITY, oppositeSide)) {
                    IQubitContainer qubitContainer = tileEntity.getCapability(GregicalityCapabilities.QBIT_CAPABILITY, oppositeSide);
                    if (qubitContainer == null || !qubitContainer.inputsQubit(oppositeSide)) continue;
                    currentParallel += qubitContainer.acceptQubitFromNetwork(oppositeSide, outputQubit, outputParallel - currentParallel);
                    if (currentParallel == outputParallel) break;
                }
            }
            if (currentParallel > 0) {
                setQubitStored(getQubitStored() - currentParallel * outputQubit);
            }
        }
    }

    @Override
    public long acceptQubitFromNetwork(EnumFacing side, long qubit, long parallel) {
        long canAccept = getQubitCapacity() - getQubitStored();
        if (qubit > 0L && parallel > 0L && (side == null || inputsQubit(side))) {
            if (qubit > getInputQubit()) {
                return Math.min(parallel, getInputParallel());
            }
            if (canAccept >= qubit) {
                long parallelAccepted = Math.min(canAccept / qubit, Math.min(parallel, getInputParallel()));
                if (parallelAccepted > 0) {
                    setQubitStored(getQubitStored() + qubit * parallelAccepted);
                    return parallelAccepted;
                }
            }
        }
        return 0;
    }

    @Override
    public long getQubitCapacity() {
        return this.maxCapacity;
    }

    @Override
    public boolean inputsQubit(EnumFacing side) {
        return !outputsQubit(side) && getInputQubit() > 0 && (sideInputCondition == null || sideInputCondition.test(side));
    }

    @Override
    public boolean outputsQubit(EnumFacing side) {
        return getOutputQubit() > 0 && (sideOutputCondition == null || sideOutputCondition.test(side));
    }

    @Override
    public long changeQubit(long energyToAdd) {
        long oldQubitStored = getQubitStored();
        long newQubitStored = (maxCapacity - oldQubitStored < energyToAdd) ? maxCapacity : (oldQubitStored + energyToAdd);
        if (newQubitStored < 0)
            newQubitStored = 0;
        setQubitStored(newQubitStored);
        return newQubitStored - oldQubitStored;
    }

    @Override
    public long getOutputQubit() {
        return this.maxOutputQubit;
    }

    @Override
    public long getOutputParallel() {
        return this.maxOutputParallel;
    }

    @Override
    public long getInputParallel() {
        return this.maxInputParallel;
    }

    @Override
    public long getInputQubit() {
        return this.maxInputQubit;
    }

    public interface IQubitChangeListener {
        void onEnergyChanged(IQubitContainer container, boolean isInitialChange);
    }
}
