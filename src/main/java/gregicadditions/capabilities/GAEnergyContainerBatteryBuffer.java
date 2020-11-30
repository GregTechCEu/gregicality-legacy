package gregicadditions.capabilities;

import gregicadditions.GAValues;
import gregtech.api.capability.impl.EnergyContainerBatteryBuffer;
import gregtech.api.metatileentity.MetaTileEntity;

public class GAEnergyContainerBatteryBuffer extends EnergyContainerBatteryBuffer {

    public GAEnergyContainerBatteryBuffer(MetaTileEntity metaTileEntity, int tier) {
        super(metaTileEntity, tier);
    }

    @Override
    public long getInputVoltage() {
        return GAValues.V[getTier()];
    }

}
