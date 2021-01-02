package gregicadditions.pipelike.opticalfiber.tile;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import gregicadditions.pipelike.opticalfiber.OpticalFiberSize;
import gregtech.api.pipenet.tile.TileEntityPipeBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileEntityOpticalFiber extends TileEntityPipeBase<OpticalFiberSize, OpticalFiberProperties> {

    private IQubitContainer energyContainer;

    private IQubitContainer getEnergyContainer() {
        if (energyContainer == null) {
            energyContainer = new CableQubitContainer(this);
        }
        return energyContainer;
    }

    @Override
    public Class<OpticalFiberSize> getPipeTypeClass() {
        return OpticalFiberSize.class;
    }

    @Override
    public boolean supportsTicking() {
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapabilityInternal(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == GregicAdditionsCapabilities.QBIT_CAPABILITY) {
            return (T) getEnergyContainer();
        }
        return super.getCapabilityInternal(capability, facing);
    }

}
