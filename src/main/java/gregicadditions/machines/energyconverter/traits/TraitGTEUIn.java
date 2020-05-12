package gregicadditions.machines.energyconverter.traits;

import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TraitGTEUIn extends TraitEnergyConverter implements IEnergyContainer {
    public TraitGTEUIn(final MetaTileEntityEnergyConverter energyConverter) {
        super(energyConverter);
    }

    @Override
    protected Capability<?> getImplementingCapability() {
        return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
    }

    public String getName() {
        return "TraitGTEUIn";
    }

    public int getNetworkID() {
        return 1;
    }

    public long acceptEnergyFromNetwork(final EnumFacing side, final long voltage, final long amperage) {
        return this.energyConverter.getEnergyStorage().acceptEnergyFromNetwork(side, voltage, amperage);
    }

    public boolean inputsEnergy(final EnumFacing side) {
        return true;
    }

    public long changeEnergy(final long differenceAmount) {
        return this.energyConverter.getEnergyStorage().changeEnergy(differenceAmount);
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
