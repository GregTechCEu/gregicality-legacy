package gregicadditions.integrations.FECompat;

import javax.annotation.Nullable;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import static gregicadditions.integrations.FECompat.EnergyProvider.safeCastLongToInt;

public class GregicEnergyContainerWrapper implements IEnergyContainer {

    private final ICapabilityProvider upvalue;
    private final IEnergyStorage[] facesRF = new IEnergyStorage[7];

    private int rfBuffer = 0;

    protected GregicEnergyContainerWrapper(ICapabilityProvider upvalue) {
        this.upvalue = upvalue;
    }

    protected boolean isValid(EnumFacing face) {

        if (upvalue.hasCapability(CapabilityEnergy.ENERGY, face))
            return true;

        if (face == null) {
            for (EnumFacing face2 : EnumFacing.VALUES) {
                if (upvalue.hasCapability(CapabilityEnergy.ENERGY, face2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private IEnergyStorage getStorageCap() {

        IEnergyStorage container = def();

        if (container != null && container.getMaxEnergyStored() > 0)
            return container;

        for (EnumFacing face : EnumFacing.VALUES) {
            container = facesRF[face.getIndex()];

            if (container == null) {
                container = upvalue.getCapability(CapabilityEnergy.ENERGY, face);
                facesRF[face.getIndex()] = container;
            }

            if (container != null && container.getMaxEnergyStored() > 0)
                return container;
        }

        return container;
    }

    private IEnergyStorage getAcceptionCap() {

        IEnergyStorage container = def();

        if (container != null && container.receiveEnergy(Integer.MAX_VALUE, true) > 0)
            return container;

        for (EnumFacing face : EnumFacing.VALUES) {
            container = facesRF[face.getIndex()];

            if (container == null) {
                container = upvalue.getCapability(CapabilityEnergy.ENERGY, face);
                facesRF[face.getIndex()] = container;
            }

            if (container != null && container.receiveEnergy(Integer.MAX_VALUE, true) > 0)
                return container;
        }

        return container;
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing facing, long voltage, long amperage) {

        int faceID = facing == null ? 6 : facing.getIndex();
        IEnergyStorage container = facesRF[faceID];

        if (container == null) {
            container = upvalue.getCapability(CapabilityEnergy.ENERGY, facing);
            facesRF[faceID] = container;
        }

        if (container == null)
            return 0L;

        int receive = 0;

        // Try to use the internal buffer before consuming a new packet
        if (rfBuffer > 0) {

            receive = container.receiveEnergy(rfBuffer, true);

            if (receive == 0)
                return 0;

            // Internal Buffer could provide the max RF the consumer could consume
            if (rfBuffer > receive) {
                rfBuffer -= receive;
                container.receiveEnergy(receive, false);
                return 0;

            // Buffer could not provide max value, save the remainder and continue processing
            } else {
                receive = rfBuffer;
                rfBuffer = 0;
            }
        }

        long maxPacket = voltage * EnergyProvider.RATIO_LONG;
        long maximalValue = maxPacket * amperage;

        // Try to consume our remainder buffer plus a fresh packet
        if (receive != 0) {

            int consumable = container.receiveEnergy(safeCastLongToInt(maximalValue + receive), true);

            // Machine unable to consume any power
            // Probably unnecessary in this case, but just to be safe
            if (consumable == 0)
                return 0;

            // Only able to consume our buffered amount
            if (consumable == receive) {
                container.receiveEnergy(consumable, false);
                return 0;
            }

            // Able to consume our full packet as well as our remainder buffer
            if (consumable == maximalValue + receive) {
                container.receiveEnergy(consumable, false);
                return amperage;
            }

            int newPower = consumable - receive;

            // Able to consume buffered amount plus an even amount of packets (no buffer needed)
            if (newPower % maxPacket == 0) {
                return container.receiveEnergy(consumable, false) / maxPacket;
            }

            // Able to consume buffered amount plus some amount of power with a packet remainder
            int ampsToConsume = safeCastLongToInt((newPower / maxPacket) + 1);
            rfBuffer = safeCastLongToInt((maxPacket * ampsToConsume) - consumable);
            container.receiveEnergy(consumable, false);
            return ampsToConsume;

        // Else try to draw 1 full packet
        } else {

            int consumable = container.receiveEnergy(safeCastLongToInt(maximalValue), true);

            // Machine unable to consume any power
            if (consumable == 0)
                return 0;

            // Able to accept the full amount of power
            if (consumable == maximalValue) {
                container.receiveEnergy(consumable, false);
                return amperage;
            }

            // Able to consume an even amount of packets
            if (consumable % maxPacket == 0) {
                return container.receiveEnergy(consumable, false) / maxPacket;
            }

            // Able to consume power with some amount of power remainder in the packet
            int ampsToConsume = safeCastLongToInt((consumable / maxPacket) + 1);
            rfBuffer = safeCastLongToInt((maxPacket * ampsToConsume) - consumable);
            container.receiveEnergy(consumable, false);
            return ampsToConsume;
        }
    }

    @Override
    public long changeEnergy(long delta) {

        IEnergyStorage container = getStorageCap();

        if (container == null || delta == 0)
            return 0;

        long energyValue = delta * EnergyProvider.RATIO_LONG;
        if (energyValue > Integer.MAX_VALUE)
            energyValue = Integer.MAX_VALUE;

        if (delta < 0L) {

            int extract = container.extractEnergy(safeCastLongToInt(energyValue), true);

            if (extract != GAConfig.EUtoRF.RATIO)
                extract -= extract % GAConfig.EUtoRF.RATIO;

            return container.extractEnergy(extract, false) / EnergyProvider.RATIO_LONG;

        } else {

            int receive = container.receiveEnergy((int) energyValue, true);

            if (receive != GAConfig.EUtoRF.RATIO)
                receive -= receive % GAConfig.EUtoRF.RATIO;

            return container.receiveEnergy(receive, false) / EnergyProvider.RATIO_LONG;
        }
    }

    @Nullable
    private IEnergyStorage def() {

        if (facesRF[6] == null)
            facesRF[6] = upvalue.getCapability(CapabilityEnergy.ENERGY, null);

        return facesRF[6];
    }

    @Override
    public long getEnergyCapacity() {
        IEnergyStorage cap = getStorageCap();

        if (cap == null)
            return 0L;

        return cap.getMaxEnergyStored() / GAConfig.EUtoRF.RATIO;
    }

    @Override
    public long getEnergyStored() {
        IEnergyStorage cap = getStorageCap();

        if (cap == null)
            return 0L;

        return cap.getEnergyStored() / GAConfig.EUtoRF.RATIO;
    }

    @Override
    public long getInputAmperage() {
        IEnergyStorage container = getAcceptionCap();

        if (container == null)
            return 0;

        long voltage = getInputVoltage();

        return voltage == 0 ? 0 : 2;
    }

    @Override
    public long getInputVoltage() {
        IEnergyStorage container = getStorageCap();

        if (container == null)
            return 0;

        long grabMaxInput = container.receiveEnergy(Integer.MAX_VALUE, true);

        if (grabMaxInput == 0)
            return 0;

        grabMaxInput /= EnergyProvider.RATIO_LONG;
        return GAValues.V[GAUtility.getTierByVoltage(grabMaxInput)];
    }

    @Override
    public boolean inputsEnergy(EnumFacing facing) {

        int faceID = facing == null ? 6 : facing.getIndex();
        IEnergyStorage container = facesRF[faceID];

        if (container == null) {
            container = upvalue.getCapability(CapabilityEnergy.ENERGY, facing);
            facesRF[faceID] = container;
        }

        if (container == null)
            return false;

        return container.canReceive();
    }

    /**
     * We just want to receive energy from ENet without hacks. FE based blocks will
     * push energy on it's own to us using EnergyContainerWrapper.
     */
    @Override
    public boolean outputsEnergy(EnumFacing facing) {
        return false;
    }

    @Override
    public boolean isOneProbeHidden() {
        return true;
    }
}
