package gregicadditions.item.behaviors;

import gregtech.api.items.metaitem.stats.IItemColorProvider;
import gregtech.api.items.metaitem.stats.IItemNameProvider;
import gregtech.api.unification.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        int colorValue = material.getMaterialRGB();
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
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack itemStack, String unlocalizedName) {
        if (material != null)
            return I18n.format(unlocalizedName, material.getLocalizedName());
        else
            return I18n.format(unlocalizedName, I18n.format(this.unlocalizedName));
    }
}
