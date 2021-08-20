package gregicadditions.integrations.FECompat;

import gregicadditions.GAConfig;
import gregtech.api.capability.GregtechCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import java.util.concurrent.locks.ReentrantLock;

public class EnergyProvider implements ICapabilityProvider {

    private final TileEntity tileEntity;
    private GregicEnergyContainerWrapper wrapper;

    /**
     * Lock used for concurrency protection between hasCapability and getCapability.
     */
    ReentrantLock lock = new ReentrantLock();

    public EnergyProvider(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {

        if (!GAConfig.EnergyConversion.enableNativeEUtoFE)
            return false;

        if (lock.isLocked() || (capability != CapabilityEnergy.ENERGY && capability != GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER))
            return false;

        // Wrap FE Machines with a GTEU EnergyContainer
        if (wrapper == null) wrapper = new GregicEnergyContainerWrapper(tileEntity);

        lock.lock();
        try {
            return wrapper.isValid(facing);
        } finally {
            lock.unlock();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {

        if (!GAConfig.EnergyConversion.enableNativeEUtoFE)
            return null;

        if (lock.isLocked() || !hasCapability(capability, facing))
            return null;

        if (wrapper == null) wrapper = new GregicEnergyContainerWrapper(tileEntity);

        lock.lock();
        try {
            return wrapper.isValid(facing) ? (T) wrapper : null;
        } finally {
            lock.unlock();
        }
    }
}
