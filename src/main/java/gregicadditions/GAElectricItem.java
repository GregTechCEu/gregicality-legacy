package gregicadditions;

import gregtech.api.capability.impl.ElectricItem;
import net.minecraft.item.ItemStack;

public class GAElectricItem extends ElectricItem {
    public GAElectricItem(ItemStack itemStack, long maxCharge, int tier, boolean chargeable, boolean canProvideEnergyExternally) {
        super(itemStack, maxCharge, tier, chargeable, canProvideEnergyExternally);
    }
    @Override
    public long getTransferLimit() {
        return GAValues.V[getTier()];
    }
}
