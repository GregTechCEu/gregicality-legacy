package gregicadditions.blocks;

import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.common.blocks.OreItemBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class GAOreItemBlock extends OreItemBlock {

    private final GABlockOre oreBlock;

    public GAOreItemBlock(GABlockOre oreBlock) {
        super(oreBlock);
        this.oreBlock = oreBlock;
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        IBlockState blockState = getBlockState(stack);
        StoneType stoneType = blockState.getValue(oreBlock.STONE_TYPE);
        OrePrefix orePrefix = stoneType.processingPrefix == OrePrefix.ore ? oreBlock.orePrefix :
                OrePrefix.getPrefix(oreBlock.orePrefix.name() + stoneType.processingPrefix.name().substring(3));
        return orePrefix.getLocalNameForItem(oreBlock.material);
    }
}
