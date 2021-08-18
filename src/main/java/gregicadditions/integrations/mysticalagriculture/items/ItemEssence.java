package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.iface.IColoredItem;
import com.blakebr0.cucumber.item.ItemBase;
import gregtech.api.unification.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEssence extends ItemBase implements IColoredItem {

    private Material material;

    public ItemEssence(Material material) {
        super(material.toString() + ".essence");
        this.material = material;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("item.ga_essence.material.name", material.getLocalizedName());
    }

    @Override
    public int color() {
        return material.getMaterialRGB();
    }
}
