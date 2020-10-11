package gregicadditions.pipelike.opticalfiber.tile;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IOpticalFiberContainer;
import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import gregicadditions.pipelike.opticalfiber.OpticalFiberSize;
import gregtech.api.pipenet.tile.TileEntityPipeBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileEntityOpticalFiber extends TileEntityPipeBase<OpticalFiberSize, OpticalFiberProperties> {

    private IOpticalFiberContainer energyContainer;

    private IOpticalFiberContainer getEnergyContainer() {
        if (energyContainer == null) {
            energyContainer = new CableOpticalFiberContainer(this);
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
        if (capability == GregicAdditionsCapabilities.OPTICAL_FIBER_CAPABILITY) {
            return (T) getEnergyContainer();
        }
        return super.getCapabilityInternal(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        System.out.println("save");
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        System.out.println("read");
        super.readFromNBT(compound);
    }
}
