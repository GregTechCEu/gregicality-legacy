package gregicadditions.machines.ceu.energy;

import gregicadditions.machines.ceu.MTECeu;
import gregicadditions.machines.ceu.utils.Energy;
import gregicadditions.machines.ceu.utils.Ratio;
import gregtech.api.GTValues;
import gregtech.api.capability.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.function.BiConsumer;

public class ElectricItemFE implements IElectricItem {
    private final MTECeu ceu;
    private final IEnergyStorage storage;
    private final Ratio ratio;

    public ElectricItemFE(final MTECeu ceu, final IEnergyStorage storage) {
        this.ceu = ceu;
        this.storage = storage;
        this.ratio = ((ceu.getType().getInput() == Energy.FE) ? ceu.ratio() : ceu.ratio().reverse());
    }

    public boolean canProvideChargeExternally() {
        return true;
    }

    public void addChargeListener(final BiConsumer<ItemStack, Long> chargeListener) {
    }

    public long charge(final long amount, final int tier, final boolean ignoreTransferLimit, final boolean simulate) {
        return this.ratio.convertToLong(this.storage.receiveEnergy(this.ratio.reverse().convertToInt(amount), simulate));
    }

    public long discharge(final long amount, final int tier, final boolean ignoreTransferLimit, final boolean externally, final boolean simulate) {
        return this.ratio.convertToLong(this.storage.extractEnergy(this.ratio.reverse().convertToInt(amount), simulate));
    }

    public long getCharge() {
        return this.ratio.convertToLong(this.storage.getEnergyStored());
    }

    public long getMaxCharge() {
        return this.ratio.convertToLong(this.storage.getMaxEnergyStored());
    }

    public boolean canUse(final long amount) {
        return this.ratio.convertToLong(this.storage.getEnergyStored()) >= amount;
    }

    public int getTier() {
        return this.ceu.getTier();
    }

    public long getTransferLimit() {
        return GTValues.V[this.getTier()];
    }
}
