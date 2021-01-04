package gregicadditions.item.tools;

import gregicadditions.item.behaviors.BehaviourDetravToolElectricProPick;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.tools.ToolBase;
import net.minecraft.item.ItemStack;

public class DetravToolElectricProPick extends ToolBase {

    private int tier;

    public DetravToolElectricProPick(int tier) {
        this.tier = tier;
    }

    @Override
    public int getBaseQuality(ItemStack stack) {
        return tier - 2;
    }

    @Override
    public float getMaxDurabilityMultiplier(ItemStack stack) {
        if (tier - 6 == 0)
            return (float) Math.pow(((float) ((tier - 6F) * 2F)), 0.0D);
        else
            return (float) ((tier - 6F) * 2F);
    }

    @Override
    public void onStatsAddedToTool(MetaItem.MetaValueItem metaValueItem) {
        metaValueItem.addComponents(new BehaviourDetravToolElectricProPick(1, tier));
    }

}
