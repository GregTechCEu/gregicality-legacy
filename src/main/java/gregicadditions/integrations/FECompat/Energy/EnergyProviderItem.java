package gregicadditions.integrations.FECompat.Energy;

import gregtech.api.capability.GregtechCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

public class EnergyProviderItem implements ICapabilityProvider {
	private final ItemStack upvalue;
	private ItemEnergyContainerWrapper rfwrapper;
	private GregicEnergyContainerWrapper wrapper;
	private final boolean valid;

	public EnergyProviderItem(ItemStack entCap) {
		upvalue = entCap;

		valid = entCap.getCount() == 1;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (!valid) {
			return false;
		}

		if (capability == CapabilityEnergy.ENERGY) {
			if (rfwrapper == null) {
				rfwrapper = new ItemEnergyContainerWrapper(upvalue.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null));
			}

			return rfwrapper.isValid();
		}

		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (!hasCapability(capability, facing)) {
			return null;
		}

		if (capability == CapabilityEnergy.ENERGY) {
			if (rfwrapper == null) {
				rfwrapper = new ItemEnergyContainerWrapper(upvalue.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null));
			}

			if (rfwrapper.isValid()) {
				return (T) rfwrapper;
			}

			return null;
		}

		return null;
	}
}
