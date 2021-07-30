package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.iface.IColoredItem;
import com.blakebr0.cucumber.item.ItemBase;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.LocalisationUtils;
import net.minecraft.item.ItemStack;

public class ItemEssence extends ItemBase implements IColoredItem {

    private Material material;

    public ItemEssence(Material material) {
        super(material.toString() + ".essence");
        this.material = material;
    }

    @Override
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(ItemStack stack) {
        return LocalisationUtils.format("item.ga_essence.material.name", material.getLocalizedName());
    }

    @Override
    public int color() {
        return material.materialRGB;
    }
}
