package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.iface.IColoredItem;
import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.LocalisationUtils;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemTierSeed extends ItemSeed implements IColoredItem {

    private final Material material;


    public ItemTierSeed(Block crops, Material material, int tier) {
        super("", crops, tier);
        this.setTranslationKey(material.toString() + ".seeds");
        this.setRegistryName(material.toString() + ".seeds");
        this.setCreativeTab(MysticalAgradditions.tabMysticalAgradditions);

        this.material = material;
    }

    @Override
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(ItemStack stack) {
        return LocalisationUtils.format("item.ga_seed.material.name", material.getLocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, java.util.List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        switch (this.getTier() - 1) {
            case 5:
                tooltip.add(Tooltips.TIER + Colors.DARK_PURPLE + "6");
                break;
            case 6:
                tooltip.add(Tooltips.TIER + Colors.BLUE + "7");
                break;
            case 7:
                tooltip.add(Tooltips.TIER + Colors.DARK_RED + "8");
                break;
        }
    }

    @Override
    public int color() {
        return material.materialRGB;
    }

}