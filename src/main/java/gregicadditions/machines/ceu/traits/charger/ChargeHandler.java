package gregicadditions.machines.ceu.traits.charger;

import gregicadditions.GAConfig;
import gregicadditions.machines.ceu.MTECeu;
import gregicadditions.machines.ceu.energy.CeuCharger;
import gregicadditions.machines.ceu.utils.Numbers;
import gregicadditions.machines.ceu.utils.Ratio;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.metatileentity.MTETrait;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public abstract class ChargeHandler extends MTETrait implements CeuCharger {
	protected final MTECeu ceu;

	public ChargeHandler(final MTECeu ceu) {
		super(ceu);
		this.ceu = ceu;
	}

	public String getName() {
		return "CeuChargeHandler";
	}

	public int getNetworkID() {
		return -1;
	}

	@Nullable
	public <T> T getCapability(final Capability<T> capability) {
		return null;
	}

	public void update() {
		if (!this.ceu.getWorld().isRemote && this.ceu.isThisEnabled()) {
			final Ratio r = this.ceu.ratio();
			final Number charged = r.reverse().
					convert(
							this.insertEnergy(this.ceu.getType().getOutput(), r.convert(this.ceu.extractEnergy(this.ceu.getType().getInput(), Long.MAX_VALUE, false, true), this.ceu.getType().getOutput().getNumberType()), false, false),
							this.ceu.getType().getInput().getNumberType());
			this.ceu.extractEnergy(this.ceu.getType().getInput(), charged, false, false);
			if (this.ceu.getEnergyStorage().getEnergyStored() > 0L) {
				if (this.ceu.isCeu()) {
					this.ceu.getEnergyStorage().removeEnergy(this.chargeEU(this.ceu.getEnergyStorage().getEnergyStored(), false, false, false));
				} else {
					this.ceu.getEnergyStorage().removeEnergy(r.convertToLong(this.insertEnergy(this.ceu.getType().getInput(), r.reverse().convert(this.ceu.getEnergyStorage().getEnergyStored(), this.ceu.getType().getInput().getNumberType()), false, false)));
				}
			}
		}
	}

	public long chargeEU(final long amount, final boolean ignoreLimit, final boolean chargeWrapped, final boolean simulate) {
		if (amount <= 0L) {
			return 0L;
		}
		long total = 0L;
		final IItemHandlerModifiable inventory = this.ceu.getImportItems();
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
		final IItemHandlerModifiable inventory = this.ceu.getImportItems();
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
		final IItemHandlerModifiable inventory = this.ceu.getImportItems();
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
		final IItemHandlerModifiable inventory = this.ceu.getImportItems();
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
			return (GAConfig.ceu.PermitOnlyExactVoltage && i.getTier() != this.ceu.getTier()) ? null : i;
		}
		if (wrap) {
			return this.getWrappedBatteryContainer(s);
		}
		return null;
	}

	@Nullable
	protected abstract IElectricItem getWrappedBatteryContainer(final ItemStack p0);
}
