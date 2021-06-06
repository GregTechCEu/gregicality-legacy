package gregicadditions.integrations.FECompat.Energy;

import gregicadditions.GAConfig;
import gregicadditions.integrations.FECompat.Constants;
import gregicadditions.GAValues;
import gregtech.api.capability.IElectricItem;
import net.minecraftforge.energy.IEnergyStorage;

import static gregicadditions.integrations.FECompat.Constants.safeCastLongToInt;

public class ItemEnergyContainerWrapper implements IEnergyStorage {

    private final IElectricItem container;

    public ItemEnergyContainerWrapper(IElectricItem container) {
        this.container = container;
    }

    boolean isValid() {
        return container != null;
    }

    private int getMaxSpeed() {
        return safeCastLongToInt(GAValues.V[container.getTier()] * Constants.RATIO_LONG);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {

        // Assuming we hit Mekanism or EnderIO Charger
        if (maxReceive == 1 && simulate)
            maxReceive = Integer.MAX_VALUE;

        int speed = getMaxSpeed();

        if (maxReceive > speed)
            maxReceive = speed;

        if (maxReceive != GAConfig.EUtoRF.RATIO)
            maxReceive -= maxReceive % GAConfig.EUtoRF.RATIO;

        if (maxReceive <= 0)
            return 0;

        long simulated = container.charge(maxReceive / Constants.RATIO_LONG, Integer.MAX_VALUE, false, true);

        if (simulated < 0)
            return 0;

        if (!simulate)
            container.charge(simulated, Integer.MAX_VALUE, false, false);

        return safeCastLongToInt(simulated * Constants.RATIO_LONG);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {

        // Assuming we hit Mekanism
        if (maxExtract == 1 && simulate)
            maxExtract = Integer.MAX_VALUE;

        int speed = getMaxSpeed();

        if (maxExtract > speed)
            maxExtract = speed;

        if (maxExtract != GAConfig.EUtoRF.RATIO)
            maxExtract -= maxExtract % GAConfig.EUtoRF.RATIO;

        if (maxExtract <= 0)
            return 0;

        long simulated = container.discharge(maxExtract / Constants.RATIO_LONG, Integer.MAX_VALUE, false, true, true);

        if (simulated < 0L)
            return 0;

        if (!simulate)
            container.discharge(simulated, Integer.MAX_VALUE, false, true, false);

        return safeCastLongToInt(simulated * Constants.RATIO_LONG);
    }

    @Override
    public int getEnergyStored() {
        return safeCastLongToInt(container.getCharge());
    }

    @Override
    public int getMaxEnergyStored() {
        return safeCastLongToInt(container.getMaxCharge());
    }

    @Override
    public boolean canExtract() {
        return container.canProvideChargeExternally();
    }

    @Override
    public boolean canReceive() {
        return container.charge(container.getTransferLimit(), Integer.MAX_VALUE, true, true) != 0;
    }
}
