package gregicadditions.machines.ceu.traits;


import gregicadditions.machines.ceu.MTECeu;
import gregicadditions.machines.ceu.utils.Energy;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TraitFeOut extends TraitCeu.TraitCeuCapabilityBasedEmitter<IEnergyStorage> implements IEnergyStorage {
	public TraitFeOut(final MTECeu ceu) {
		super(ceu);
	}

	@Override
	protected void operate(final IEnergyStorage storage) {
		if (storage.canReceive()) {
			int extracted = this.extractEnergy(Integer.MAX_VALUE, true);
			if (extracted > 0) {
				extracted = storage.receiveEnergy(extracted, false);
				if (extracted > 0) {
					this.extractEnergy(extracted, false);
				}
			}
		}
	}

	public String getName() {
		return "TraitFeOut";
	}

	public int getNetworkID() {
		return 0;
	}

	@Override
	protected Capability<IEnergyStorage> getImplementingCapability() {
		return CapabilityEnergy.ENERGY;
	}

	public int receiveEnergy(final int maxReceive, final boolean simulate) {
		return 0;
	}

	public int extractEnergy(final int maxExtract, final boolean simulate) {
		return this.ceu.extractEnergy(Energy.FE, maxExtract, true, simulate).intValue();
	}

	public int getEnergyStored() {
		return this.ceu.getStoredSum(Energy.FE, true).intValue();
	}

	public int getMaxEnergyStored() {
		return this.ceu.getCapacitySum(Energy.FE, true).intValue();
	}

	public boolean canExtract() {
		return true;
	}

	public boolean canReceive() {
		return false;
	}
}
