package gregicadditions.machines.energyconverter.traits;

import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TraitGTEUOut extends TraitEnergyConverter.TraitEnergyConverterCapabilityBasedEmitter<IEnergyContainer> implements IEnergyContainer {
	public TraitGTEUOut(final MetaTileEntityEnergyConverter energyConverter) {
		super(energyConverter);
	}

	@Override
	protected void operate(final IEnergyContainer energy) {
		if (energy.inputsEnergy(this.energyConverter.getFrontFacing().getOpposite())) {
			final long volt = this.energyConverter.getEnergyStorage().getOutputVoltage();
			final long amp = this.energyConverter.getEnergyStorage().getOutputAmperage();
			final long energyStored = this.energyConverter.extractEU(Long.MAX_VALUE, true, true);
			if (energyStored > volt) {
				long ampere = Math.min(amp, energyStored / volt);
				if (ampere > 0L) {
					ampere = energy.acceptEnergyFromNetwork(this.energyConverter.getFrontFacing().getOpposite(), volt, ampere);
					if (ampere > 0L) {
						this.energyConverter.extractEU(volt * ampere, true, false);
					}
				}
			}
		}
	}

	public String getName() {
		return "TraitGteuOut";
	}

	public int getNetworkID() {
		return 1;
	}

	@Override
	protected Capability<IEnergyContainer> getImplementingCapability() {
		return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
	}

	public long acceptEnergyFromNetwork(final EnumFacing side, final long voltage, final long amperage) {
		return 0L;
	}

	public boolean inputsEnergy(final EnumFacing side) {
		return false;
	}

	public boolean outputsEnergy(final EnumFacing side) {
		return true;
	}

	public long changeEnergy(final long differenceAmount) {
		return 0L;
	}

	public long getEnergyStored() {
		return this.energyConverter.getEUStoredSum(true);
	}

	public long getEnergyCapacity() {
		return this.energyConverter.getEUCapacitySum(true);
	}

	public long getInputAmperage() {
		return this.energyConverter.getEnergyStorage().getInputAmperage();
	}

	public long getInputVoltage() {
		return this.energyConverter.getEnergyStorage().getInputVoltage();
	}
}
