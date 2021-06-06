package gregicadditions.integrations.FECompat;

import gregicadditions.GAConfig;
import gregtech.api.capability.GregtechCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;

public class EnergyProvider implements ICapabilityProvider {

    private final TileEntity tileEntity;
    private GregicEnergyContainerWrapper wrapper;

    /**
     * Long value of the EU to RF ratio, to not need to cast as often.
     */
    public static final long RATIO_LONG = GAConfig.EUtoRF.RATIO;

    /**
     * "Atomic" boolean to prevent hasCapability and getCapability from colliding.
     * Not sure if this is necessary, or if it could be implemented properly
     * with an AtomicBoolean instead.
     */
    private boolean gettingValue = false;

    public EnergyProvider(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {

        if (!GAConfig.EUtoRF.enableNativeEUtoRF)
            return false;

        if (gettingValue || (capability != CapabilityEnergy.ENERGY && capability != GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER))
            return false;

        // Wrap RF Machines with a GTEU EnergyContainer
        if (wrapper == null) wrapper = new GregicEnergyContainerWrapper(tileEntity);

        gettingValue = true;
        boolean result = wrapper.isValid(facing);
        gettingValue = false;

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {

        if (!GAConfig.EUtoRF.enableNativeEUtoRF)
            return null;

        if (gettingValue || !hasCapability(capability, facing))
            return null;

        if (wrapper == null) wrapper = new GregicEnergyContainerWrapper(tileEntity);

        gettingValue = true;

        if (wrapper.isValid(facing)) {
            gettingValue = false;
            return (T) wrapper;
        }

        gettingValue = false;
        return null;
    }
}
