package gregicadditions.integrations.FECompat.Energy;

import javax.annotation.Nullable;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.utils.GALog;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import gregicadditions.integrations.FECompat.Constants;

import static gregicadditions.integrations.FECompat.Constants.safeCastLongToInt;

public class GregicEnergyContainerWrapper implements IEnergyContainer {

    private final ICapabilityProvider upvalue;
	private final IEnergyStorage[] facesRF = new IEnergyStorage[7];

	private int rfBuffer = 0;

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

        if (container == null)
            return 0L;

        int receive = 0;

        // Try to use the internal buffer before consuming a new packet
        if (rfBuffer > 0) {

            GALog.logger.info("Buffering");

            receive = container.receiveEnergy(rfBuffer, true);

            if (receive == 0)
                return 0;

            // Internal Buffer could provide the max RF the consumer could consume
            if (rfBuffer > receive) {
                rfBuffer -= receive;
                container.receiveEnergy(receive, false);
                return 0;

            // Buffer could not provide max value, save the remainder and continue processing
            } else {
                receive = rfBuffer;
                rfBuffer = 0;
            }
        }

        long maxPacket = voltage * Constants.RATIO_LONG;
        long maximalValue = maxPacket * amperage;

        // Try to consume our remainder buffer plus a fresh packet
        if (receive != 0) {

            GALog.logger.info("Remainder Buffer + New Amp");

            int consumable = container.receiveEnergy(safeCastLongToInt(maximalValue + receive), true);

            // Machine unable to consume any power
            // Probably unnecessary in this case, but just to be safe
            if (consumable == 0)
                return 0;

            // Only able to consume our buffered amount
            if (consumable == receive) {
                container.receiveEnergy(consumable, false);
                return 0;
            }

            // Able to consume our full packet as well as our remainder buffer
            if (consumable == maximalValue + receive) {
                container.receiveEnergy(consumable, false);
                return amperage;
            }

            int newPower = consumable - receive;

            // Able to consume buffered amount plus an even amount of packets (no buffer needed)
            if (newPower % maxPacket == 0) {
                return container.receiveEnergy(consumable, false) / maxPacket;
            }

            // Able to consume buffered amount plus some amount of power with a packet remainder
            int ampsToConsume = safeCastLongToInt((newPower / maxPacket) + 1);
            rfBuffer = safeCastLongToInt((maxPacket * ampsToConsume) - consumable);
            container.receiveEnergy(consumable, false);
            return ampsToConsume;

        // Else try to draw 1 full packet
        } else {

            GALog.logger.info("New Amp Only");

            int consumable = container.receiveEnergy(safeCastLongToInt(maximalValue), true);

            // Machine unable to consume any power
            if (consumable == 0)
                return 0;

            // Able to accept the full amount of power
            if (consumable == maximalValue) {
                container.receiveEnergy(consumable, false);
                return amperage;
            }

            // Able to consume an even amount of packets
            if (consumable % maxPacket == 0) {
                return container.receiveEnergy(consumable, false) / maxPacket;
            }

            // Able to consume power with some amount of power remainder in the packet
            int ampsToConsume = safeCastLongToInt((consumable / maxPacket) + 1);
            rfBuffer = safeCastLongToInt((maxPacket * ampsToConsume) - consumable);
            container.receiveEnergy(consumable, false);
            return ampsToConsume;
        }
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
			long extractValue = delta * Constants.RATIO_LONG;

			if (extractValue > Integer.MAX_VALUE) {
				extractValue = Integer.MAX_VALUE;
			}

			int extract = container.extractEnergy(safeCastLongToInt(extractValue), true);
			extract -= extract % GAConfig.EUtoRF.RATIO;
			return container.extractEnergy(extract, false) / Constants.RATIO_LONG;
		}

		long receiveValue = delta * Constants.RATIO_LONG;

		if (receiveValue > Integer.MAX_VALUE) {
			receiveValue = Integer.MAX_VALUE;
		}

		int receive = container.receiveEnergy((int) receiveValue, true);
		receive -= receive % GAConfig.EUtoRF.RATIO;
		return container.receiveEnergy(receive, false) / Constants.RATIO_LONG;
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
		value -= value % GAConfig.EUtoRF.RATIO;
		return value / GAConfig.EUtoRF.RATIO;
	}

	@Override
	public long getEnergyStored() {
		IEnergyStorage cap = getStorageCap();

		if (cap == null) {
			return 0L;
		}

		int value = cap.getEnergyStored();
		value -= value % GAConfig.EUtoRF.RATIO;
		return value / GAConfig.EUtoRF.RATIO;
	}

	@Override
	public long getInputAmperage() {
		IEnergyStorage container = getAcceptionCap();

		if (container == null) {
			return 0L;
		}

		long voltage = getInputVoltage();

		if (voltage == GAValues.V[GAValues.V.length]) {
			return 1L;
		}

		for (int index = 0; index < GAValues.V.length; index++) {
			if (GAValues.V[index] == voltage) {
				long voltageNext = GAValues.V[index + 1] * Constants.RATIO_LONG;

				if (voltageNext > Integer.MAX_VALUE) {
					voltageNext = Integer.MAX_VALUE;
				}

				int allowedInput = container.receiveEnergy(safeCastLongToInt(voltageNext), true);

				if (allowedInput < voltage * Constants.RATIO_LONG) {
					return 1L;
				}

				allowedInput -= allowedInput % voltage * Constants.RATIO_LONG;
				return allowedInput / (voltage * Constants.RATIO_LONG);
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
		grabMaxInput -= grabMaxInput % Constants.RATIO_LONG;

		if (grabMaxInput == 0) {
			return 0L;
		}

		grabMaxInput /= Constants.RATIO_LONG;

		long value = GAValues.V[0];

		if (grabMaxInput < value) {
			return 0L;
		}

		for (long value2 : GAValues.V) {
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
