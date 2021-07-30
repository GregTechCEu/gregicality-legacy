package gregicadditions.blocks;

import gregtech.api.unification.material.type.Material;
import gregtech.api.util.LocalisationUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
public class GAMetalCasingItemBlock extends ItemBlock {

    private final GAMetalCasing metalCasingBlock;

    public GAMetalCasingItemBlock(GAMetalCasing block) {
        super(block);
        this.metalCasingBlock = block;
        setHasSubtypes(true);

    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @SuppressWarnings("deprecation")
    public IBlockState getBlockState(ItemStack stack) {
        return metalCasingBlock.getStateFromMeta(getMetadata(stack.getItemDamage()));
    }

    @Override
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(ItemStack stack) {
        Material material = getBlockState(stack).getValue(metalCasingBlock.variantProperty);
        return LocalisationUtils.format("tile.ga_metal_casing.material.name", material.getLocalizedName());
    }


}
