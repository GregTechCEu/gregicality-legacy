package gregicadditions.machines.energyconverter.traits.charger;


import gregicadditions.GAConfig;
import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.energy.ElectricItemFE;
import gregicadditions.machines.energyconverter.energy.EnergyStorageGTEU;
import gregicadditions.machines.energyconverter.utils.Energy;
import gregicadditions.machines.energyconverter.utils.Numbers;
import gregtech.api.capability.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public class ChargeHandlerFE extends ChargeHandler {
	public ChargeHandlerFE(final MetaTileEntityEnergyConverter energyConverter) {
		super(energyConverter);
	}

	public Number getStoredSum(final Energy e, final boolean includeWrapped) {
		if (e == Energy.GTEU) {
			return this.storedEU(includeWrapped);
		}
		if (e != Energy.FE) {
			throw new IllegalArgumentException();
		}
		return this.storedFE(2147483647L, includeWrapped);
	}

	public Number getCapacitySum(final Energy e, final boolean includeWrapped) {
		if (e == Energy.GTEU) {
			return this.capacityEU(includeWrapped);
		}
		if (e != Energy.FE) {
			throw new IllegalArgumentException();
		}
		return this.capacityFE(2147483647L, includeWrapped);
	}

	public Number extractEnergy(final Energy e, final Number max, final boolean extractWrapped, final boolean simulate) {
		if (e == Energy.GTEU) {
			return this.dischargeEU(max.longValue(), false, true, extractWrapped, simulate);
		}
		if (e != Energy.FE) {
			throw new IllegalArgumentException();
		}
		return this.dischargeFE(max.longValue(), extractWrapped, simulate);
	}

	public Number insertEnergy(final Energy e, final Number max, final boolean insertWrapped, final boolean simulate) {
		if (e == Energy.GTEU) {
			return this.chargeEU(max.longValue(), false, insertWrapped, simulate);
		}
		if (e != Energy.FE) {
			throw new IllegalArgumentException();
		}
		return this.chargeFE(max.longValue(), insertWrapped, simulate);
	}

	public long chargeFE(final long maxReceive, final boolean chargeWrapped, final boolean simulate) {
		if (maxReceive <= 0L) {
			return 0L;
		}
		long total = 0L;
		final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			final ItemStack s = inventory.getStackInSlot(i);
			if (!s.isEmpty()) {
				final IEnergyStorage storage = this.getItemEnergyStorage(s, chargeWrapped);
				if (storage != null) {
					total += storage.receiveEnergy((int) Math.min(maxReceive - total, 2147483647L), simulate);
					if (total >= maxReceive) {
						return maxReceive;
					}
				}
			}
		}
		return total;
	}

	public long dischargeFE(final long maxExtract, final boolean dischargeWrapped, final boolean simulate) {
		if (maxExtract <= 0L) {
			return 0L;
		}
		long total = 0L;
		final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			final ItemStack s = inventory.getStackInSlot(i);
			if (!s.isEmpty()) {
				final IEnergyStorage storage = this.getItemEnergyStorage(s, dischargeWrapped);
				if (storage != null) {
					total += storage.extractEnergy((int) Math.min(maxExtract - total, 2147483647L), simulate);
					if (total >= maxExtract) {
						return maxExtract;
					}
				}
			}
		}
		return total;
	}

	public long capacityFE(final long limit, final boolean includeWrapped) {
		if (limit <= 0L) {
			return 0L;
		}
		long total = 0L;
		final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			final ItemStack s = inventory.getStackInSlot(i);
			if (!s.isEmpty()) {
				final IEnergyStorage storage = this.getItemEnergyStorage(s, includeWrapped);
				if (storage != null) {
					total = Numbers.addWithOverflowCheck(storage.getMaxEnergyStored(), total);
					if (total >= limit) {
						return limit;
					}
				}
			}
		}
		return total;
	}

	public long storedFE(final long limit, final boolean includeWrapped) {
		if (limit <= 0L) {
			return 0L;
		}
		long total = 0L;
		final IItemHandlerModifiable inventory = this.energyConverter.getImportItems();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			final ItemStack s = inventory.getStackInSlot(i);
			if (!s.isEmpty()) {
				final IEnergyStorage storage = this.getItemEnergyStorage(s, includeWrapped);
				if (storage != null) {
					total = Numbers.addWithOverflowCheck(storage.getEnergyStored(), total);
					if (total >= limit) {
						return limit;
					}
				}
			}
		}
		return total;
	}

	@Nullable
	@Override
	protected IElectricItem getWrappedBatteryContainer(final ItemStack s) {
		final IEnergyStorage storage = this.getItemEnergyStorage(s, false);
		return (storage != null) ? new ElectricItemFE(this.energyConverter, storage) : null;
	}

	@Nullable
	protected IEnergyStorage getItemEnergyStorage(final ItemStack s, final boolean wrapEUBattery) {
		final IEnergyStorage storage = s.getCapability(CapabilityEnergy.ENERGY, null);
		Label_0049:
		{
			if (storage != null) {
				if (this.energyConverter.isGTEU()) {
					if (!storage.canReceive()) {
						break Label_0049;
					}
				} else if (!storage.canExtract()) {
					break Label_0049;
				}
				return storage;
			}
		}
		if (wrapEUBattery) {
			final IElectricItem i = this.getBatteryContainer(s, false);
			return (i != null && (!GAConfig.EnergyConversion.PermitOnlyExactVoltage || i.getTier() == this.energyConverter.getTier())) ? new EnergyStorageGTEU(this.energyConverter, i) : null;
		}
		return null;
	}
}
