package gregicadditions.item.components;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class SensorCasing extends ReTexturedCasing<SensorCasing.CasingType> {

    public SensorCasing() {
        super(new ResourceLocation("gtadditions","block/casing/sensor"));
        setTranslationKey("ga_sensor_casing");
        setDefaultState(getState(CasingType.SENSOR_LV));
    }

    public enum CasingType implements IStringSerializable {
        SENSOR_LV("sensor_lv", 1),
        SENSOR_MV("sensor_mv", 2),
        SENSOR_HV("sensor_hv", 3),
        SENSOR_EV("sensor_ev", 4),
        SENSOR_IV("sensor_iv", 5),
        SENSOR_LUV("sensor_luv", 6),
        SENSOR_ZPM("sensor_zpm", 7),
        SENSOR_UV("sensor_uv", 8),
        SENSOR_UHV("sensor_uhv", 9),
        SENSOR_UEV("sensor_uev", 10),
        SENSOR_UIV("sensor_uiv", 11),
        SENSOR_UMV("sensor_umv", 12),
        SENSOR_UXV("sensor_uxv", 13),
        SENSOR_MAX("sensor_max", 14);

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
