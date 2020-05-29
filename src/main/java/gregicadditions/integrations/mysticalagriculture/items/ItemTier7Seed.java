package gregicadditions.integrations.mysticalagriculture.items;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemTier7Seed extends ItemSeeds {

    private final Block crops;

    public ItemTier7Seed(String name, Block crops) {
        super(crops, Blocks.FARMLAND);
        this.setTranslationKey("ma." + name);
        this.setCreativeTab(MysticalAgradditions.tabMysticalAgradditions);
        this.crops = crops;
    }

    public int getTier() {
        return 7;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, java.util.List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Tooltips.TIER + Colors.LIGHT_PURPLE + "7");
    }

}