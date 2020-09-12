package gregicadditions.item.components;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class PistonCasing extends VariantBlock<PistonCasing.CasingType> {

    public PistonCasing() {
        super(Material.IRON);
        setTranslationKey("ga_piston_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.PISTON_LV));
    }

    public enum CasingType implements IStringSerializable {
        PISTON_LV("piston_lv", 1),
        PISTON_MV("piston_mv", 2),
        PISTON_HV("piston_hv", 3),
        PISTON_EV("piston_ev", 4),
        PISTON_IV("piston_iv", 5),
        PISTON_LUV("piston_luv", 6),
        PISTON_ZPM("piston_zpm", 7),
        PISTON_UV("piston_uv", 8);

        private final String name;
        private final int tier;

        CasingType(String name, int tier) {
            this.name = name;
            this.tier = tier;
        }

        public int getTier() {
            return tier;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
