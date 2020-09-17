package gregicadditions.item.components;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class MotorCasing extends VariantBlock<MotorCasing.CasingType> {

    public MotorCasing() {
        super(Material.IRON);
        setTranslationKey("ga_motor_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.MOTOR_LV));
    }

    public enum CasingType implements IStringSerializable {
        MOTOR_LV("motor_lv", 1),
        MOTOR_MV("motor_mv", 2),
        MOTOR_HV("motor_hv", 3),
        MOTOR_EV("motor_ev", 4),
        MOTOR_IV("motor_iv", 5),
        MOTOR_LUV("motor_luv", 6),
        MOTOR_ZPM("motor_zpm", 7),
        MOTOR_UV("motor_uv", 8);

        private final String name;
        private final int tier;

        public int getTier() {
            return tier;
        }

        CasingType(String name, int tier) {
            this.name = name;
            this.tier = tier;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
