package gregicadditions.blocks;

import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.common.blocks.OreItemBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GAOreItemBlock extends OreItemBlock {

    private GABlockOre oreBlock;

    public GAOreItemBlock(GABlockOre oreBlock) {
        super(oreBlock);
        this.oreBlock = oreBlock;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        IBlockState blockState = getBlockState(stack);
        StoneType stoneType = blockState.getValue(oreBlock.STONE_TYPE);
        OrePrefix orePrefix = stoneType.processingPrefix == OrePrefix.ore ? oreBlock.getOrePrefix() :
                OrePrefix.valueOf(oreBlock.getOrePrefix().name() + stoneType.processingPrefix.name().substring(3));
        return orePrefix.getLocalNameForItem(oreBlock.material);
    }
}
