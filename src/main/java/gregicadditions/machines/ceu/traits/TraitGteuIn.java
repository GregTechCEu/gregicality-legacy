package gregicadditions.machines.ceu.traits;

import gregicadditions.machines.ceu.MTECeu;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TraitGteuIn extends TraitCeu implements IEnergyContainer {
	public TraitGteuIn(final MTECeu ceu) {
		super(ceu);
	}

	@Override
	protected Capability<?> getImplementingCapability() {
		return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
	}

	public String getName() {
		return "TraitGteuIn";
	}

	public int getNetworkID() {
		return 1;
	}

	public long acceptEnergyFromNetwork(final EnumFacing side, final long voltage, final long amperage) {
		return this.ceu.getEnergyStorage().acceptEnergyFromNetwork(side, voltage, amperage);
	}

	public boolean inputsEnergy(final EnumFacing side) {
		return true;
	}

	public long changeEnergy(final long differenceAmount) {
		return this.ceu.getEnergyStorage().changeEnergy(differenceAmount);
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
