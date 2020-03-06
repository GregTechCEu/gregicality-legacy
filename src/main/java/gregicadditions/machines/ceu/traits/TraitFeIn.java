package gregicadditions.machines.ceu.traits;

import gregicadditions.machines.ceu.MTECeu;
import gregicadditions.machines.ceu.utils.Energy;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TraitFeIn extends TraitCeu implements IEnergyStorage {
	public TraitFeIn(final MTECeu ceu) {
		super(ceu);
	}

	@Override
	protected Capability<?> getImplementingCapability() {
		return CapabilityEnergy.ENERGY;
	}

	public String getName() {
		return "TraitFeIn";
	}

	public int getNetworkID() {
		return 0;
	}

	public int receiveEnergy(final int maxReceive, final boolean simulate) {
		return this.ceu.ratio().reverse().convertToInt(this.ceu.getEnergyStorage().asElectricItem().charge(this.ceu.ratio().convertToLong(maxReceive), this.ceu.getTier(), false, simulate));
	}

	public int extractEnergy(final int maxExtract, final boolean simulate) {
		return 0;
	}

	public int getEnergyStored() {
		return this.ceu.getStoredSum(Energy.FE, true).intValue();
	}

	public int getMaxEnergyStored() {
		return this.ceu.getCapacitySum(Energy.FE, true).intValue();
	}

	public boolean canExtract() {
		return false;
	}

	public boolean canReceive() {
		return true;
	}
}
