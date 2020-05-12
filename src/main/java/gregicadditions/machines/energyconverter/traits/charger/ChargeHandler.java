package gregicadditions.machines.energyconverter.traits.charger;

import gregicadditions.GAConfig;
import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.energy.EnergyConverterCharger;
import gregicadditions.machines.energyconverter.utils.Numbers;
import gregicadditions.machines.energyconverter.utils.Ratio;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.metatileentity.MTETrait;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public abstract class ChargeHandler extends MTETrait implements EnergyConverterCharger {
    protected final MetaTileEntityEnergyConverter energyConverter;

    public ChargeHandler(final MetaTileEntityEnergyConverter energyConverter) {
        super(energyConverter);
        this.energyConverter = energyConverter;
    }

    public String getName() {
        return "ChargeHandler";
    }

    public int getNetworkID() {
        return -1;
    }

    @Nullable
    public <T> T getCapability(final Capability<T> capability) {
        return null;
    }

    public void update() {
        if (!this.energyConverter.getWorld().isRemote && this.energyConverter.isThisEnabled()) {
            final Ratio r = this.energyConverter.ratio();
            final Number charged = r.reverse().
                    convert(
                            this.insertEnergy(this.energyConverter.getType().getOutput(), r.convert(this.energyConverter.extractEnergy(this.energyConverter.getType().getInput(), Long.MAX_VALUE, false, true), this.energyConverter.getType().getOutput().getNumberType()), false, false),
                            this.energyConverter.getType().getInput().getNumberType());
            this.energyConverter.extractEnergy(this.energyConverter.getType().getInput(), charged, false, false);
            if (this.energyConverter.getEnergyStorage().getEnergyStored() > 0L) {
                if (this.energyConverter.isGTEU()) {
                    this.energyConverter.getEnergyStorage().removeEnergy(this.chargeEU(this.energyConverter.getEnergyStorage().getEnergyStored(), false, false, false));
                } else {
                    this.energyConverter.getEnergyStorage().removeEnergy(r.convertToLong(this.insertEnergy(this.energyConverter.getType().getInput(), r.reverse().convert(this.energyConverter.getEnergyStorage().getEnergyStored(), this.energyConverter.getType().getInput().getNumberType()), false, false)));
                }
            }
        }
    }

    public long chargeEU(final long amount, final boolean ignoreLimit, final boolean chargeWrapped, final boolean simulate) {
        if (amount <= 0L) {
            return 0L;
        }
        long total = 0L;
        final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
        for (int i = 0; i < inventory.getSlots(); ++i) {
            final ItemStack s = inventory.getStackInSlot(i);
            if (!s.isEmpty()) {
                final IElectricItem item = this.getBatteryContainer(s, chargeWrapped);
                if (item != null) {
                    total += item.charge(amount - total, item.getTier(), ignoreLimit, simulate);
                    if (total >= amount) {
                        return amount;
                    }
                }
            }
        }
        return total;
    }

    public long dischargeEU(final long amount, final boolean ignoreLimit, final boolean externally, final boolean dischargeWrapped, final boolean simulate) {
        if (amount <= 0L) {
            return 0L;
        }
        long total = 0L;
        final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
        for (int i = 0; i < inventory.getSlots(); ++i) {
            final ItemStack s = inventory.getStackInSlot(i);
            if (!s.isEmpty()) {
                final IElectricItem item = this.getBatteryContainer(s, dischargeWrapped);
                if (item != null) {
                    total += item.discharge(amount - total, item.getTier(), ignoreLimit, externally, simulate);
                    if (total >= amount) {
                        return amount;
                    }
                }
            }
        }
        return total;
    }

    public long capacityEU(final boolean includeWrapped) {
        long total = 0L;
        final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
        for (int i = 0; i < inventory.getSlots(); ++i) {
            final ItemStack s = inventory.getStackInSlot(i);
            if (!s.isEmpty()) {
                final IElectricItem item = this.getBatteryContainer(s, includeWrapped);
                if (item != null) {
                    total = Numbers.addWithOverflowCheck(item.getMaxCharge(), total);
                    if (total == Long.MAX_VALUE) {
                        return total;
                    }
                }
            }
        }
        return total;
    }

    public long storedEU(final boolean includeWrapped) {
        long total = 0L;
        final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
        for (int i = 0; i < inventory.getSlots(); ++i) {
            final ItemStack s = inventory.getStackInSlot(i);
            if (!s.isEmpty()) {
                final IElectricItem item = this.getBatteryContainer(s, includeWrapped);
                if (item != null) {
                    total = Numbers.addWithOverflowCheck(item.getCharge(), total);
                    if (total == Long.MAX_VALUE) {
                        return total;
                    }
                }
            }
        }
        return total;
    }

    @Nullable
    public IElectricItem getBatteryContainer(final ItemStack s, final boolean wrap) {
        final IElectricItem i = s.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (i != null && i.canProvideChargeExternally()) {
            return (GAConfig.energyConverter.PermitOnlyExactVoltage && i.getTier() != this.energyConverter.getTier()) ? null : i;
        }
        if (wrap) {
            return this.getWrappedBatteryContainer(s);
        }
        return null;
    }

    @Nullable
    protected abstract IElectricItem getWrappedBatteryContainer(final ItemStack p0);
}
