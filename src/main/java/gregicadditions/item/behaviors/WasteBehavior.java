package gregicadditions.item.behaviors;

import gregtech.api.items.metaitem.stats.IItemColorProvider;
import gregtech.api.items.metaitem.stats.IItemNameProvider;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.LocalisationUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class WasteBehavior implements IItemColorProvider, IItemNameProvider {

    private Material material;
    private String unlocalizedName;
    private int color;

    public WasteBehavior(Material material) {
        this.material = material;
    }

    public WasteBehavior(String unlocalizedName, int color) {
        this.unlocalizedName = unlocalizedName;
        this.color = color;
    }

    @Override
    public int getItemStackColor(ItemStack itemStack, int i) {
        if (color != 0) {
            return color;
        }
        int colorValue = material.materialRGB;
        int colorOffset = 0x25;
        int r = (colorValue >> 16) & 0xFF;
        int g = (colorValue >> 8) & 0xFF;
        int b = (colorValue & 0xFF);

        r = Math.min(r + colorOffset, 0xFF);
        g = Math.min(g + colorOffset, 0xFF);
        b = Math.min(b + colorOffset, 0xFF);
        return (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF);
    }

    @Override
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(ItemStack itemStack, String unlocalizedName) {
        if (material != null)
            return LocalisationUtils.format(unlocalizedName, material.getLocalizedName());
        else
            return LocalisationUtils.format(unlocalizedName, I18n.format(this.unlocalizedName));
    }
}
