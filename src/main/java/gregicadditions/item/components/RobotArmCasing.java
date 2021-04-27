package gregicadditions.item.components;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class RobotArmCasing extends ReTexturedCasing<RobotArmCasing.CasingType> {

    public RobotArmCasing() {
        super(new ResourceLocation("gtadditions","block/casing/robot_arm"));
        setTranslationKey("ga_robot_arm_casing");
        setDefaultState(getState(CasingType.ROBOT_ARM_LV));
    }

    public enum CasingType implements IStringSerializable {
        ROBOT_ARM_LV("robot_arm_lv", 1),
        ROBOT_ARM_MV("robot_arm_mv", 2),
        ROBOT_ARM_HV("robot_arm_hv", 3),
        ROBOT_ARM_EV("robot_arm_ev", 4),
        ROBOT_ARM_IV("robot_arm_iv", 5),
        ROBOT_ARM_LUV("robot_arm_luv", 6),
        ROBOT_ARM_ZPM("robot_arm_zpm", 7),
        ROBOT_ARM_UV("robot_arm_uv", 8),
        ROBOT_ARM_UHV("robot_arm_uhv", 9),
        ROBOT_ARM_UEV("robot_arm_uev", 10),
        ROBOT_ARM_UIV("robot_arm_uiv", 11),
        ROBOT_ARM_UMV("robot_arm_umv", 12),
        ROBOT_ARM_UXV("robot_arm_uxv", 13),
        ROBOT_ARM_MAX("robot_arm_max", 14);

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
