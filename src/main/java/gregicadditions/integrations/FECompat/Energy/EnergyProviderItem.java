package gregicadditions.integrations.FECompat.Energy;

import gregtech.api.capability.GregtechCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;

public class EnergyProviderItem implements ICapabilityProvider {

    private final ItemStack upvalue;
    private ItemEnergyContainerWrapper rfWrapper;
    private GregicItemContainerWrapper gtWrapper;
    private final boolean valid;

    public EnergyProviderItem(ItemStack entCap) {
        upvalue = entCap;
        valid = entCap.getCount() == 1;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {

        if (!valid)
            return false;

        if (capability == CapabilityEnergy.ENERGY) {

            if (rfWrapper == null)
                rfWrapper = new ItemEnergyContainerWrapper(upvalue.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null));

            return rfWrapper.isValid();
        }
        // TODO
        else if (capability == GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM) {

            if (gtWrapper == null)
                gtWrapper = new GregicItemContainerWrapper(upvalue.getCapability(CapabilityEnergy.ENERGY, null));

            return gtWrapper.isValid();
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {

        if (!hasCapability(capability, facing))
            return null;

        if (capability == CapabilityEnergy.ENERGY) {

            if (rfWrapper == null)
                rfWrapper = new ItemEnergyContainerWrapper(upvalue.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null));

            if (rfWrapper.isValid())
                return (T) rfWrapper;

        } else if (capability == GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM) {

            if (gtWrapper == null)
                gtWrapper = new GregicItemContainerWrapper(upvalue.getCapability(CapabilityEnergy.ENERGY, null));

            if (gtWrapper.isValid())
                return (T) gtWrapper;
        }
        return null;
    }
}
