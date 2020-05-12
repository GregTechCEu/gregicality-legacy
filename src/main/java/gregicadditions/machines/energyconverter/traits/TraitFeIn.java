package gregicadditions.machines.energyconverter.traits;

import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.utils.Energy;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TraitFeIn extends TraitEnergyConverter implements IEnergyStorage {
    public TraitFeIn(final MetaTileEntityEnergyConverter energyConverter) {
        super(energyConverter);
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
        return this.energyConverter.ratio().reverse().convertToInt(this.energyConverter.getEnergyStorage().asElectricItem().charge(this.energyConverter.ratio().convertToLong(maxReceive), this.energyConverter.getTier(), false, simulate));
    }

	public int extractEnergy(final int maxExtract, final boolean simulate) {
		return 0;
	}

	public int getEnergyStored() {
        return this.energyConverter.getStoredSum(Energy.FE, true).intValue();
    }

	public int getMaxEnergyStored() {
        return this.energyConverter.getCapacitySum(Energy.FE, true).intValue();
    }

	public boolean canExtract() {
		return false;
	}

	public boolean canReceive() {
		return true;
	}
}
