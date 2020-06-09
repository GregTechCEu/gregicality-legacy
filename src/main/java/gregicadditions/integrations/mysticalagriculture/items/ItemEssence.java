package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.item.ItemBase;
import forestry.core.items.IColoredItem;
import gregtech.api.unification.material.type.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class ItemEssence extends ItemBase implements IColoredItem {

    private Material material;

    public ItemEssence(Material material) {
        super(material.toString() + ".essence");
        this.material = material;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("item.ga_essence.material.name", material.getLocalizedName());
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        return material.materialRGB;
    }
}
