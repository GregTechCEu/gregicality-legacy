
// Copyright (C) 2018 DBot

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all copies
// or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
// INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
// FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
// DEALINGS IN THE SOFTWARE.

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
