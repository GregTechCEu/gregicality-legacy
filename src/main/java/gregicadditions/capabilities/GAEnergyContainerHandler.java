package gregicadditions.capabilities;

import gregicadditions.GAUtility;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandlerModifiable;

public class GAEnergyContainerHandler extends EnergyContainerHandler {

    public GAEnergyContainerHandler(MetaTileEntity tileEntity, long maxCapacity, long maxInputVoltage, long maxInputAmperage, long maxOutputVoltage, long maxOutputAmperage) {
        super(tileEntity, maxCapacity, maxInputVoltage, maxInputAmperage, maxOutputVoltage, maxOutputAmperage);
    }

    @Override
    public boolean dischargeOrRechargeEnergyContainers(IItemHandlerModifiable itemHandler, int slotIndex) {
        ItemStack stackInSlot = itemHandler.getStackInSlot(slotIndex);
        if (!stackInSlot.isEmpty()) {
            IElectricItem electricItem = stackInSlot.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
            if (electricItem != null && electricItem.canProvideChargeExternally()) {
                int machineTier = GAUtility.getTierByVoltage(Math.max(this.getInputVoltage(), this.getOutputVoltage()));
                if (this.getEnergyCanBeInserted() > 0L) {
                    double chargePercent = (double) this.getEnergyStored() / ((double) this.getEnergyCapacity() * 1.0D);
                    long chargedBy;
                    if (chargePercent <= 0.5D) {
                        chargedBy = electricItem.discharge(this.getEnergyCanBeInserted(), machineTier, false, true, false);
                        this.addEnergy(chargedBy);
                        return chargedBy > 0L;
                    }

                    if (chargePercent >= 0.9D) {
                        chargedBy = electricItem.charge(this.getEnergyStored(), machineTier, false, false);
                        this.removeEnergy(chargedBy);
                        return chargedBy > 0L;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
        long canAccept = this.getEnergyCapacity() - this.getEnergyStored();
        if (voltage > 0L && amperage > 0L && (side == null || this.inputsEnergy(side))) {
            if (voltage > this.getInputVoltage()) {
                GAUtility.doOvervoltageExplosion(this.metaTileEntity, voltage);
                return Math.min(amperage, this.getInputAmperage());
            }

            if (canAccept >= voltage) {
                long amperesAccepted = Math.min(canAccept / voltage, Math.min(amperage, this.getInputAmperage()));
                if (amperesAccepted > 0L) {
                    this.setEnergyStored(this.getEnergyStored() + voltage * amperesAccepted);
                    return amperesAccepted;
                }
            }
        }

        return 0L;
    }


}
