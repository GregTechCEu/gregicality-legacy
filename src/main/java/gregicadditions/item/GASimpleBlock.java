package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class GASimpleBlock extends VariantBlock<GASimpleBlock.BlockType> {


    public GASimpleBlock() {
        super(Material.IRON);
        setTranslationKey("ga_simple_block");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(BlockType.SUPERHEAVY_BLOCK));
    }

    public enum BlockType implements IStringSerializable {

        SUPERHEAVY_BLOCK("superheavy_block");


        private final String name;

        BlockType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }
}
