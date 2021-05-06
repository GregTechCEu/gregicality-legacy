
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

// Modified by Irgendwer and DStrand1 2021

package gregicadditions.integrations.FECompat.Energy;

import gregtech.api.capability.IEnergyContainer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;
import gregicadditions.integrations.FECompat.Constants;

import static gregicadditions.integrations.FECompat.Constants.safeCastLongToInt;

/**
 * This class is nearly redundant, as it is only used to allow GTEU producers to
 * tell RF consumers that it can send RF natively.
 *
 * Maybe we can remove this in the future.
 */
public class EnergyContainerWrapper implements IEnergyStorage {

    private final IEnergyContainer container;

    public EnergyContainerWrapper(IEnergyContainer container, EnumFacing facing) {
        this.container = container;
    }

    boolean isValid() {
        return container != null && !(container instanceof GregicEnergyContainerWrapper);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    /**
      * Even though we can potentially extract energy from this GT machine, we do not want
      * it handled in this class, instead in {@link GregicEnergyContainerWrapper}.
      */
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return safeCastLongToInt(container.getEnergyStored() * Constants.RATIO_LONG);
    }

    @Override
    public int getMaxEnergyStored() {
        return safeCastLongToInt(container.getEnergyCapacity() * Constants.RATIO_LONG);
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
