package gregicadditions.machines.ceu.energy;

import gregicadditions.machines.ceu.MTECeu;
import gregicadditions.machines.ceu.utils.Numbers;
import gregtech.api.GTValues;
import gregtech.api.capability.IElectricItem;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class UniversalEnergyStorage implements IEnergyContainer, INBTSerializable<NBTTagCompound> {
    private final MTECeu ceu;
    private long current;
    private long max;
    private final ElectricItemWrapped electricItem;

    public UniversalEnergyStorage(final MTECeu ceu, final long max) {
        this(ceu, 0L, max);
    }

    public UniversalEnergyStorage(final MTECeu ceu, final long current, final long max) {
        this.electricItem = new ElectricItemWrapped();
        this.ceu = ceu;
        this.current = current;
        this.max = max;
    }

    public IElectricItem asElectricItem() {
        return this.electricItem;
    }

    public long acceptEnergyFromNetwork(@Nullable final EnumFacing side, final long voltage, final long amperage) {
        if (voltage > 0L && amperage > 0L && (side == null || this.inputsEnergy(side))) {
            if (voltage > this.getInputVoltage()) {
                final BlockPos pos = this.ceu.getPos();
                this.ceu.getWorld().removeTileEntity(pos);
                if (ConfigHolder.doExplosions) {
                    this.ceu.getWorld().createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, GTUtility.getTierByVoltage(voltage), true);
                }
                return Math.min(amperage, this.getInputAmperage());
            }
            final long canAccept = this.getEnergyCapacity() - this.getEnergyStored();
            if (canAccept >= voltage) {
                final long amperesAccepted = Math.min(canAccept / voltage, Math.min(amperage, this.getInputAmperage()));
                if (amperesAccepted > 0L) {
                    this.setEnergyStored(this.getEnergyStored() + voltage * amperesAccepted);
                    return amperesAccepted;
                }
            }
        }
        return 0L;
    }

    public boolean inputsEnergy(final EnumFacing side) {
        return this.ceu.isCeu() && this.ceu.getFrontFacing() != side;
    }

    public long changeEnergy(final long differenceAmount) {
        final long old = this.current;
        this.current = Numbers.clamp(this.current + differenceAmount, 0L, this.max);
        return old - this.current;
    }

    public void setEnergyStored(final long value) {
        this.current = Numbers.clamp(value, 0L, this.max);
    }

    public long getEnergyStored() {
        return this.current;
    }

    public long getEnergyCapacity() {
        return this.max;
    }

    public long getInputAmperage() {
        return this.ceu.getImportItems().getSlots();
    }

    public long getOutputAmperage() {
        return this.getInputAmperage();
    }

    public long getInputVoltage() {
        return GTValues.V[this.ceu.getTier()];
    }

    public long getOutputVoltage() {
        return this.getInputVoltage();
    }

    public NBTTagCompound serializeNBT() {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setLong("current", this.current);
        nbt.setLong("max", this.max);
        return nbt;
    }

    public void deserializeNBT(final NBTTagCompound nbt) {
        this.current = nbt.getLong("current");
        this.max = nbt.getLong("max");
    }

    private class ElectricItemWrapped implements IElectricItem {
        public boolean canProvideChargeExternally() {
            return false;
        }

        public void addChargeListener(final BiConsumer<ItemStack, Long> chargeListener) {
        }

        public long charge(final long amount, final int chargerTier, final boolean ignoreTransferLimit, final boolean simulate) {
            if (amount > 0L) {
                long added = Math.min(amount, UniversalEnergyStorage.this.max - UniversalEnergyStorage.this.current);
                if (ignoreTransferLimit) {
                    added = Math.min(added, UniversalEnergyStorage.this.getInputAmperage() * UniversalEnergyStorage.this.getInputVoltage());
                }
                if (added > 0L) {
                    if (!simulate) {
                        UniversalEnergyStorage.this.addEnergy(added);
                    }
                    return added;
                }
            }
            return 0L;
        }

        public long discharge(final long amount, final int dischargerTier, final boolean ignoreTransferLimit, final boolean externally, final boolean simulate) {
            if (amount > 0L) {
                long consumed = Math.min(amount, UniversalEnergyStorage.this.current);
                if (ignoreTransferLimit) {
                    consumed = Math.min(consumed, UniversalEnergyStorage.this.getInputAmperage() * UniversalEnergyStorage.this.getInputVoltage());
                }
                if (consumed > 0L) {
                    if (!simulate) {
                        UniversalEnergyStorage.this.removeEnergy(consumed);
                    }
                    return consumed;
                }
            }
            return 0L;
        }

        public long getMaxCharge() {
            return UniversalEnergyStorage.this.max;
        }

        public long getCharge() {
            return UniversalEnergyStorage.this.current;
        }

        public int getTier() {
            return UniversalEnergyStorage.this.ceu.getTier();
        }

        public long getTransferLimit() {
            return GTValues.V[this.getTier()];
        }
    }
}
