package gregicadditions.machines.ceu.energy;

import gregicadditions.machines.ceu.MTECeu;
import gregicadditions.machines.ceu.utils.Energy;
import gregicadditions.machines.ceu.utils.Ratio;
import gregtech.api.capability.IElectricItem;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorageGteu implements IEnergyStorage {
    private final IElectricItem battery;
    private final Ratio ratio;

    public EnergyStorageGteu(final MTECeu ceu, final IElectricItem battery) {
        this.battery = battery;
        this.ratio = ((ceu.getType().getInput() == Energy.GTEU) ? ceu.ratio() : ceu.ratio().reverse());
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
