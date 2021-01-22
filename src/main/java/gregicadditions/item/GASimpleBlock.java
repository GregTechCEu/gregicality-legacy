package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class GASimpleBlock extends VariantBlock<GASimpleBlock.CasingType> {

    public GASimpleBlock() {
        super(Material.IRON);
        setTranslationKey("ga_simple_block");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.SUPERHEAVY_BLOCK));
    }

    public enum CasingType implements IStringSerializable {

        SUPERHEAVY_BLOCK("superheavy_block"),
        TARANIUM_CHARGE("taranium_charge"),
        NAQUADRIA_CHARGE("naquadria_charge"),
        LEPTONIC_CHARGE("leptonic_charge");


        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }
}
