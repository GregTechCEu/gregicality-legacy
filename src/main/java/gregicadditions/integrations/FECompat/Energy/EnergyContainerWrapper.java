package gregicadditions.integrations.FECompat.Energy;

import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;
import gregicadditions.integrations.FECompat.Constants;

import static gregicadditions.integrations.FECompat.Constants.safeCastLongToInt;

/**
 * This class is nearly redundant, as it is only used to allow GTEU producers to
 * tell RF consumers that it can send RF natively.
 *
 * Maybe we can remove this in the future.
 */
public class EnergyContainerWrapper implements IEnergyStorage {

    private final IEnergyContainer container;

    public EnergyContainerWrapper(IEnergyContainer container, EnumFacing facing) {
        this.container = container;
    }

    boolean isValid() {
        return container != null && !(container instanceof GregicEnergyContainerWrapper);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    /**
      * Even though we can potentially extract energy from this GT machine, we do not want
      * it handled in this class, instead in {@link GregicEnergyContainerWrapper}.
      */
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return safeCastLongToInt(container.getEnergyStored() * Constants.RATIO_LONG);
    }

    @Override
    public int getMaxEnergyStored() {
        return safeCastLongToInt(container.getEnergyCapacity() * Constants.RATIO_LONG);
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
