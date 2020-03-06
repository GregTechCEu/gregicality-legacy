package gregicadditions.machines.ceu.traits;

import gregicadditions.machines.ceu.MTECeu;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TraitGteuOut extends TraitCeu.TraitCeuCapabilityBasedEmitter<IEnergyContainer> implements IEnergyContainer {
	public TraitGteuOut(final MTECeu ceu) {
		super(ceu);
	}

	@Override
	protected void operate(final IEnergyContainer energy) {
		if (energy.inputsEnergy(this.ceu.getFrontFacing().getOpposite())) {
			final long volt = this.ceu.getEnergyStorage().getOutputVoltage();
			final long amp = this.ceu.getEnergyStorage().getOutputAmperage();
			final long energyStored = this.ceu.extractEU(Long.MAX_VALUE, true, true);
			if (energyStored > volt) {
				long ampere = Math.min(amp, energyStored / volt);
				if (ampere > 0L) {
					ampere = energy.acceptEnergyFromNetwork(this.ceu.getFrontFacing().getOpposite(), volt, ampere);
					if (ampere > 0L) {
						this.ceu.extractEU(volt * ampere, true, false);
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
		return this.ceu.getEUStoredSum(true);
	}

	public long getEnergyCapacity() {
		return this.ceu.getEUCapacitySum(true);
	}

	public long getInputAmperage() {
		return this.ceu.getEnergyStorage().getInputAmperage();
	}

	public long getInputVoltage() {
		return this.ceu.getEnergyStorage().getInputVoltage();
	}
}
