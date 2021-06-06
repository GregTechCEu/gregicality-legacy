package gregicadditions.integrations.FECompat.Energy;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;

public class EnergyProvider implements ICapabilityProvider {

    private final TileEntity upvalue;

    private final EnergyContainerWrapper[] facesRF = new EnergyContainerWrapper[7];

    private GregicEnergyContainerWrapper wrapper;

    /**
     * "Atomic" boolean to prevent hasCapability and getCapability from colliding.
     * Not sure if this is necessary
     */
    private boolean gettingValue = false;

    public EnergyProvider(TileEntity entCap) {
		upvalue = entCap;
	}

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {

        if (gettingValue)
            return false;

        if (capability != CapabilityEnergy.ENERGY && capability != GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER)
            return false;

        // Wrap GTEU Machines with a RF EnergyContainer
        if (capability == CapabilityEnergy.ENERGY) {

            int faceID = facing == null ? 6 : facing.getIndex();

            if (facesRF[faceID] == null) {

                IEnergyContainer container = upvalue.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing);
                if (container != null) {

                    // Locks the Capability only to EU producers
                    // TODO Give capability to wires and cables?
                    if (container.getOutputVoltage() != 0)
                        facesRF[faceID] = new EnergyContainerWrapper(container, facing);
                }
            }

            gettingValue = true;
            boolean result = facesRF[faceID] != null && facesRF[faceID].isValid();
            gettingValue = false;
            return result;
        }

        // Wrap RF Machines with a GTEU EnergyContainer
        if (wrapper == null) {
            wrapper = new GregicEnergyContainerWrapper(upvalue);
        }

        gettingValue = true;
        boolean result = wrapper.isValid(facing);
        gettingValue = false;

        return result;
    }

    // TODO Cleanup
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
		if (gettingValue) {
			return null;
		}

		if (!hasCapability(capability, facing)) {
			return null;
		}

		if (capability == CapabilityEnergy.ENERGY) {
			int faceID = facing == null ? 6 : facing.getIndex();

			if (facesRF[faceID] == null) {
				facesRF[faceID] = new EnergyContainerWrapper(upvalue.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing), facing);
			}

			gettingValue = true;

			if (facesRF[faceID].isValid()) {
				gettingValue = false;
				return (T) facesRF[faceID];
			}

			gettingValue = false;

			return null;
		}

		if (wrapper == null) {
			wrapper = new GregicEnergyContainerWrapper(upvalue);
		}

		gettingValue = true;

		if (wrapper.isValid(facing)) {
			gettingValue = false;
			return (T) wrapper;
		}

		gettingValue = false;

		return null;
	}
}
