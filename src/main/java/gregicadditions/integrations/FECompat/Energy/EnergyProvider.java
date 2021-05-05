
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

public class EnergyProvider implements ICapabilityProvider {
	private final TileEntity upvalue;
	private final EnergyContainerWrapper[] facesRF = new EnergyContainerWrapper[7];
	private GregicEnergyContainerWrapper wrapper;
	private boolean gettingValue = false;

	public EnergyProvider(TileEntity entCap) {
		upvalue = entCap;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (gettingValue) {
			return false;
		}

		//if (capability != GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) {
		if (capability != CapabilityEnergy.ENERGY && capability != GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) {
			return false;
		}

		if (capability == CapabilityEnergy.ENERGY) {
			int faceID = facing == null ? 6 : facing.getIndex();

			if (facesRF[faceID] == null) {
				facesRF[faceID] = new EnergyContainerWrapper(upvalue.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, facing), facing);
			}

			gettingValue = true;
			boolean result = facesRF[faceID].isValid();
			gettingValue = false;
			return result;
		}

		if (wrapper == null) {
			wrapper = new GregicEnergyContainerWrapper(upvalue);
		}

		gettingValue = true;
		boolean result = wrapper.isValid(facing);
		gettingValue = false;

		return result;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
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
