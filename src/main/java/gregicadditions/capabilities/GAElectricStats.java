package gregicadditions.capabilities;

import gregicadditions.GAElectricItem;
import gregtech.api.capability.impl.ElectricItem;
import gregtech.api.items.metaitem.ElectricStats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class GAElectricStats extends ElectricStats {
    public GAElectricStats(long maxCharge, long tier, boolean chargeable, boolean dischargeable) {
        super(maxCharge, tier, chargeable, dischargeable);
    }
    @Override
    public ICapabilityProvider createProvider(ItemStack itemStack) {
        return new GAElectricItem(itemStack, maxCharge, tier, chargeable, dischargeable);
    }

    public static GAElectricStats createElectricItem(long maxCharge, long tier) {
        return new GAElectricStats(maxCharge, tier, true, false);
    }

    public static ElectricStats createRechargeableBattery(long maxCharge, int tier) {
        return new GAElectricStats(maxCharge, tier, true, true);
    }

    public static GAElectricStats createBattery(long maxCharge, int tier, boolean rechargeable) {
        return new GAElectricStats(maxCharge, tier, rechargeable, true);
    }
}
