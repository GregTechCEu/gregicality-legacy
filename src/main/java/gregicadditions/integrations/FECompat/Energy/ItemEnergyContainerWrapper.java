package gregicadditions.integrations.FECompat.Energy;

import gregicadditions.GAConfig;
import gregicadditions.integrations.FECompat.variables;
import gregtech.api.GTValues;
import gregtech.api.capability.IElectricItem;
import net.minecraftforge.energy.IEnergyStorage;

public class ItemEnergyContainerWrapper implements IEnergyStorage {
	private final IElectricItem container;

	public ItemEnergyContainerWrapper(IElectricItem container) {
		this.container = container;
	}

	boolean isValid() {
		return container != null;
	}

	private long itemVoltage() {
		return GTValues.V[container.getTier()];
	}

	private int getMaxSpeed() {
		long result = itemVoltage() * variables.RATIO_LONG;

		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		return (int) result;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		/* if (!canReceive()) {
			return 0;
		}

		if (container.getMaxCharge() <= 0) {
			return 0;
		} */

		if (maxReceive == 1 && simulate) {
			// assuming we hit mekanism or enderio charger
			maxReceive = Integer.MAX_VALUE;
		}

		int speed = getMaxSpeed();

		if (maxReceive > speed) {
			maxReceive = speed;
		}

		maxReceive -= maxReceive % GAConfig.EUtoRF.RATIO;

		if (maxReceive <= 0) {
			return 0;
		}

		long simulated = container.charge(maxReceive / variables.RATIO_LONG, Integer.MAX_VALUE, false, true);

		if (simulated < 0L) {
		// if (simulated < 0L || simulated < itemVoltage()) {
			return 0;
		}

		if (!simulate) {
			container.charge(simulated, Integer.MAX_VALUE, false, false);
		}

		return (int) (simulated * variables.RATIO_LONG);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		/* if (!canExtract()) {
			return 0;
		}

		if (container.getMaxCharge() <= 0) {
			return 0;
		} */

		if (maxExtract == 1 && simulate) {
			// assuming we hit mekanism
			maxExtract = Integer.MAX_VALUE;
		}

		int speed = getMaxSpeed();

		if (maxExtract > speed) {
			maxExtract = speed;
		}

		maxExtract -= maxExtract % GAConfig.EUtoRF.RATIO;

		if (maxExtract <= 0) {
			return 0;
		}

		long simulated = container.discharge(maxExtract / variables.RATIO_LONG, Integer.MAX_VALUE, false, true, true);

		if (simulated < 0L) {
		// if (simulated < 0L || simulated < itemVoltage()) {
			return 0;
		}

		if (!simulate) {
			container.discharge(simulated, Integer.MAX_VALUE, false, true, false);
		}

		return (int) (simulated * variables.RATIO_LONG);
	}

	// IElectricItem has no interface for getting current charge
	// using workaround
	@Override
	public int getEnergyStored() {
		long value = container.getCharge();

		if (value >= variables.MAX_VALUE_AS_LONG || value > variables.OVERFLOW_CHECK) {
			return Integer.MAX_VALUE;
		}

		return (int) (value * variables.RATIO_LONG);
	}

	@Override
	public int getMaxEnergyStored() {
		long value = container.getMaxCharge();

		if (value >= variables.MAX_VALUE_AS_LONG || value > variables.OVERFLOW_CHECK) {
			return Integer.MAX_VALUE;
		}

		return (int) (value * variables.RATIO_LONG);
	}

	@Override
	public boolean canExtract() {
		return container.canProvideChargeExternally();
	}

	@Override
	public boolean canReceive() {
		return container.charge(container.getTransferLimit(), Integer.MAX_VALUE, true, true) != 0L;
	}
}
