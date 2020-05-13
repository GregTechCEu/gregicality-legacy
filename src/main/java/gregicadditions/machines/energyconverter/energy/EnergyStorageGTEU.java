package gregicadditions.machines.energyconverter.energy;

import gregicadditions.machines.energyconverter.MetaTileEntityEnergyConverter;
import gregicadditions.machines.energyconverter.utils.Energy;
import gregicadditions.machines.energyconverter.utils.Ratio;
import gregtech.api.capability.IElectricItem;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorageGTEU implements IEnergyStorage {
    private final IElectricItem battery;
    private final Ratio ratio;

    public EnergyStorageGTEU(final MetaTileEntityEnergyConverter energyConverter, final IElectricItem battery) {
        this.battery = battery;
        this.ratio = ((energyConverter.getType().getInput() == Energy.GTEU) ? energyConverter.ratio() : energyConverter.ratio().reverse());
    }

    public int receiveEnergy(final int maxReceive, final boolean simulate) {
        return this.ratio.convertToInt(this.battery.charge(this.ratio.reverse().convertToLong(maxReceive), this.battery.getTier(), false, simulate));
    }

    public int extractEnergy(final int maxExtract, final boolean simulate) {
        return this.ratio.convertToInt(this.battery.discharge(this.ratio.reverse().convertToLong(maxExtract), this.battery.getTier(), false, true, simulate));
    }

    public int getEnergyStored() {
        return this.ratio.convertToInt(this.battery.getCharge());
    }

    public int getMaxEnergyStored() {
        return this.ratio.convertToInt(this.battery.getMaxCharge());
    }

    public boolean canExtract() {
        return this.battery.discharge(1L, this.battery.getTier(), false, true, true) > 0L;
    }

    public boolean canReceive() {
        return this.battery.canProvideChargeExternally() && this.battery.charge(1L, this.battery.getTier(), false, true) > 0L;
    }
}
