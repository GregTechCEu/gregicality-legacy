package gregicadditions.item.components;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class FieldGenCasing extends ReTexturedCasing<FieldGenCasing.CasingType> {

    public FieldGenCasing() {
        super(new ResourceLocation("gtadditions","block/casing/field_gen"));
        setTranslationKey("ga_field_gen_casing");
        setDefaultState(getState(CasingType.FIELD_GENERATOR_LV));
    }

    public enum CasingType implements IStringSerializable {
        FIELD_GENERATOR_LV("field_generator_lv", 1),
        FIELD_GENERATOR_MV("field_generator_mv", 2),
        FIELD_GENERATOR_HV("field_generator_hv", 3),
        FIELD_GENERATOR_EV("field_generator_ev", 4),
        FIELD_GENERATOR_IV("field_generator_iv", 5),
        FIELD_GENERATOR_LUV("field_generator_luv", 6),
        FIELD_GENERATOR_ZPM("field_generator_zpm", 7),
        FIELD_GENERATOR_UV("field_generator_uv", 8),
        FIELD_GENERATOR_UHV("field_generator_uhv", 9),
        FIELD_GENERATOR_UEV("field_generator_uev", 10),
        FIELD_GENERATOR_UIV("field_generator_uiv", 11),
        FIELD_GENERATOR_UMV("field_generator_umv", 12),
        FIELD_GENERATOR_UXV("field_generator_uxv", 13),
        FIELD_GENERATOR_MA("field_generator_max", 14);

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
