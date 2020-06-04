package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import forestry.core.items.IColoredItem;
import gregtech.api.unification.material.type.Material;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemTier7Seed extends ItemSeeds implements IColoredItem {

    private final Block crops;
    private final Material material;

    public ItemTier7Seed(String name, Block crops, Material material) {
        super(crops, Blocks.FARMLAND);
        this.setTranslationKey(name);
        this.setCreativeTab(MysticalAgradditions.tabMysticalAgradditions);
        this.crops = crops;
        this.material = material;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("item.ga_seed.material.name", material.getLocalizedName());
    }

    public int getTier() {
        return 7;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, java.util.List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Tooltips.TIER + Colors.LIGHT_PURPLE + "7");
    }

    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        return material.materialRGB;
    }

}