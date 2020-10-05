package gregicadditions.item.components;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class SensorCasing extends VariantBlock<SensorCasing.CasingType> {

    public SensorCasing() {
        super(Material.IRON);
        setTranslationKey("ga_sensor_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
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
