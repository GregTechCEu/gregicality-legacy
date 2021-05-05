package gregicadditions.integrations.FECompat.Energy;

import gregtech.api.capability.IEnergyContainer;
import gregtech.common.pipelike.cable.tile.CableEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;
import gregicadditions.integrations.FECompat.variables;

public class EnergyContainerWrapper implements IEnergyStorage {
	private final IEnergyContainer container;
	private EnumFacing facing = null;

	public EnergyContainerWrapper(IEnergyContainer container, EnumFacing facing) {
		this.container = container;
		this.facing = facing;
	}

	boolean isValid() {
		return container != null && !(container instanceof GregicEnergyContainerWrapper);
	}

	private int maxSpeedIn() {
		long result = container.getInputAmperage() * container.getInputVoltage() * variables.RATIO_LONG;

		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		return (int) result;
	}

	private int voltageIn() {
		long result = container.getInputVoltage() * variables.RATIO_LONG;

		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		return (int) result;
	}

	private int voltageOut() {
		return 0;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return maxReceive;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return maxExtract;
	}

	@Override
	public int getEnergyStored() {
		long value = container.getEnergyStored();

		if (value >= variables.MAX_VALUE_AS_LONG || value > variables.OVERFLOW_CHECK) {
			return Integer.MAX_VALUE;
		}

		return (int) (value * variables.RATIO_LONG);
	}

	@Override
	public int getMaxEnergyStored() {
		long value = container.getEnergyCapacity();

		if (value >= variables.MAX_VALUE_AS_LONG || value > variables.OVERFLOW_CHECK) {
			return Integer.MAX_VALUE;
		}

		return (int) (value * variables.RATIO_LONG);
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return container.inputsEnergy(this.facing);
	}
}
