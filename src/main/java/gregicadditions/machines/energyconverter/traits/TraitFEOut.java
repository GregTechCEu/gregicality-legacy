package gregicadditions.machines.energyconverter.traits;


import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.utils.Energy;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TraitFEOut extends TraitEnergyConverter.TraitEnergyConverterCapabilityBasedEmitter<IEnergyStorage> implements IEnergyStorage {
	public TraitFEOut(final MetaTileEntityEnergyConverter energyConverter) {
		super(energyConverter);
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
		return "TraitFEOut";
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
		return this.energyConverter.extractEnergy(Energy.FE, maxExtract, true, simulate).intValue();
	}

	public int getEnergyStored() {
		return this.energyConverter.getStoredSum(Energy.FE, true).intValue();
	}

	public int getMaxEnergyStored() {
		return this.energyConverter.getCapacitySum(Energy.FE, true).intValue();
	}

	public boolean canExtract() {
		return true;
	}

	public boolean canReceive() {
		return false;
	}
}
