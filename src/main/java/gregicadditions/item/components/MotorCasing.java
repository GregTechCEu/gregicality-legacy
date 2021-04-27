package gregicadditions.item.components;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class MotorCasing extends ReTexturedCasing<MotorCasing.CasingType> {

    public MotorCasing() {
        super(new ResourceLocation("gtadditions","block/casing/motor"));
        setTranslationKey("ga_motor_casing");
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
        MOTOR_UV("motor_uv", 8),
        MOTOR_UHV("motor_uhv", 9),
        MOTOR_UEV("motor_uev", 10),
        MOTOR_UIV("motor_uiv", 11),
        MOTOR_UMV("motor_umv", 12),
        MOTOR_UXV("motor_uxv", 13),
        MOTOR_MAX("motor_max", 14);

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
