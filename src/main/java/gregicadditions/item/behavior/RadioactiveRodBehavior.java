package gregicadditions.item.behavior;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemDurabilityManager;
import gregtech.api.items.metaitem.stats.IItemMaxStackSizeProvider;
import gregtech.common.items.behaviors.AbstractMaterialPartBehavior;
import net.minecraft.item.ItemStack;

public class RadioactiveRodBehavior extends AbstractMaterialPartBehavior implements IItemMaxStackSizeProvider {

    public static RadioactiveRodBehavior getInstanceFor(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof MetaItem)) {
            return null;
        }
        MetaItem<?> metaItem = (MetaItem<?>) itemStack.getItem();
        MetaItem.MetaValueItem valueItem = metaItem.getItem(itemStack);
        if (valueItem == null) {
            return null;
        }
        IItemDurabilityManager durabilityManager = valueItem.getDurabilityManager();
        if (!(durabilityManager instanceof RadioactiveRodBehavior)) {
            return null;
        }
        return (RadioactiveRodBehavior) durabilityManager;
    }


    @Override
    public int getPartMaxDurability(ItemStack itemStack) {
        return 100;
    }

    @Override
    public int getMaxStackSize(ItemStack itemStack, int i) {
        return 1;
    }
}
