package gregicadditions.integrations.FECompat.Energy;

import javax.annotation.Nullable;

import gregtech.api.GTValues;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import gregicadditions.integrations.FECompat.variables;

public class GregicEnergyContainerWrapper implements IEnergyContainer {
	private final ICapabilityProvider upvalue;
	private final IEnergyStorage[] facesRF = new IEnergyStorage[7];

	public GregicEnergyContainerWrapper(ICapabilityProvider upvalue) {
		this.upvalue = upvalue;
	}

	boolean isValid(EnumFacing face) {
		if (upvalue.hasCapability(CapabilityEnergy.ENERGY, face)) {
			return true;
		}

		if (face == null) {
			for (EnumFacing face2 : EnumFacing.VALUES) {
				if (upvalue.hasCapability(CapabilityEnergy.ENERGY, face2)) {
					return true;
				}
			}
		}

		return false;
	}

	private IEnergyStorage getStorageCap() {
		IEnergyStorage container = def();

		if (container != null && container.getMaxEnergyStored() > 0) {
			return container;
		}

		for (EnumFacing face : EnumFacing.VALUES) {
			container = facesRF[face.getIndex()];

			if (container == null) {
				container = upvalue.getCapability(CapabilityEnergy.ENERGY, face);
				facesRF[face.getIndex()] = container;
			}

			if (container != null && container.getMaxEnergyStored() > 0) {
				return container;
			}
		}

		return container;
	}

	private IEnergyStorage getAcceptionCap() {
		IEnergyStorage container = def();

		if (container != null && container.receiveEnergy(Integer.MAX_VALUE, true) > 0) {
			return container;
		}

		for (EnumFacing face : EnumFacing.VALUES) {
			container = facesRF[face.getIndex()];

			if (container == null) {
				container = upvalue.getCapability(CapabilityEnergy.ENERGY, face);
				facesRF[face.getIndex()] = container;
			}

			if (container != null && container.receiveEnergy(Integer.MAX_VALUE, true) > 0) {
				return container;
			}
		}

		return container;
	}

	@Override
	public long acceptEnergyFromNetwork(EnumFacing facing, long voltage, long amperage) {
		int faceID = facing == null ? 6 : facing.getIndex();
		IEnergyStorage container = facesRF[faceID];

		if (container == null) {
			container = upvalue.getCapability(CapabilityEnergy.ENERGY, facing);
			facesRF[faceID] = container;
		}

		if (container == null) {
			return 0L;
		}

		long maximalValue = voltage * amperage * variables.RATIO_LONG;

		if (maximalValue > Integer.MAX_VALUE) {
			maximalValue = Integer.MAX_VALUE;
		}

		int receive = container.receiveEnergy((int) maximalValue, true);
		receive -= receive % (voltage * variables.RATIO_LONG);

		if (receive == 0) {
			return 0L;
		}

		return container.receiveEnergy(receive, false) / (voltage * variables.RATIO_LONG);
	}

	@Override
	public long changeEnergy(long delta) {
		IEnergyStorage container = getStorageCap();

		if (container == null) {
			return 0L;
		}

		if (delta == 0L) {
			return 0L;
		}

		if (delta < 0L) {
			long extractValue = delta * variables.RATIO_LONG;

			if (extractValue > Integer.MAX_VALUE) {
				extractValue = Integer.MAX_VALUE;
			}

			int extract = container.extractEnergy((int) extractValue, true);
			extract -= extract % variables.RATIO_INT;
			return container.extractEnergy(extract, false) / variables.RATIO_LONG;
		}

		long receiveValue = delta * variables.RATIO_LONG;

		if (receiveValue > Integer.MAX_VALUE) {
			receiveValue = Integer.MAX_VALUE;
		}

		int receive = container.receiveEnergy((int) receiveValue, true);
		receive -= receive % variables.RATIO_INT;
		return container.receiveEnergy(receive, false) / variables.RATIO_LONG;
	}

	@Nullable
	private IEnergyStorage def() {
		if (facesRF[6] == null) {
			facesRF[6] = upvalue.getCapability(CapabilityEnergy.ENERGY, null);
		}

		return facesRF[6];
	}

	@Override
	public long getEnergyCapacity() {
		IEnergyStorage cap = getStorageCap();

		if (cap == null) {
			return 0L;
		}

		int value = cap.getMaxEnergyStored();
		value -= value % variables.RATIO_INT;
		return value / variables.RATIO_INT;
	}

	@Override
	public long getEnergyStored() {
		IEnergyStorage cap = getStorageCap();

		if (cap == null) {
			return 0L;
		}

		int value = cap.getEnergyStored();
		value -= value % variables.RATIO_INT;
		return value / variables.RATIO_INT;
	}

	@Override
	public long getInputAmperage() {
		IEnergyStorage container = getAcceptionCap();

		if (container == null) {
			return 0L;
		}

		long voltage = getInputVoltage();

		if (voltage == GTValues.V[GTValues.V.length]) {
			return 1L;
		}

		for (int index = 0; index < GTValues.V.length; index++) {
			if (GTValues.V[index] == voltage) {
				long voltageNext = GTValues.V[index + 1] * variables.RATIO_LONG;

				if (voltageNext > Integer.MAX_VALUE) {
					voltageNext = Integer.MAX_VALUE;
				}

				int allowedInput = container.receiveEnergy((int) voltageNext, true);

				if (allowedInput < voltage * variables.RATIO_LONG) {
					return 1L;
				}

				allowedInput -= allowedInput % voltage * variables.RATIO_LONG;
				return allowedInput / (voltage * variables.RATIO_LONG);
			}
		}

		return 1L;
	}

	@Override
	public long getInputVoltage() {
		IEnergyStorage container = getStorageCap();

		if (container == null) {
			return 0L;
		}

		long grabMaxInput = container.receiveEnergy(Integer.MAX_VALUE, true);
		grabMaxInput -= grabMaxInput % variables.RATIO_LONG;

		if (grabMaxInput == 0) {
			return 0L;
		}

		grabMaxInput /= variables.RATIO_LONG;

		long value = GTValues.V[0];

		if (grabMaxInput < value) {
			return 0L;
		}

		for (long value2 : GTValues.V) {
			if (value2 < grabMaxInput) {
				break;
			} else {
				value = value2;
			}
		}

		return value;
	}

	@Override
	public boolean inputsEnergy(EnumFacing facing) {
		int faceID = facing == null ? 6 : facing.getIndex();
		IEnergyStorage container = facesRF[faceID];

		if (container == null) {
			container = upvalue.getCapability(CapabilityEnergy.ENERGY, facing);
			facesRF[faceID] = container;
		}

		if (container == null) {
			return false;
		}

		return container.canReceive();
	}

	@Override
	public boolean outputsEnergy(EnumFacing arg0) {
		// return container.canExtract();
		// we just want to receive energy from ENet without hacks
		// FE based blocks will push energy on it's own to us using EnergyContainerWrapper.
		return false;
	}
}
