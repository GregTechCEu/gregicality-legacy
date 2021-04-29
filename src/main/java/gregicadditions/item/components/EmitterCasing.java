package gregicadditions.item.components;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class EmitterCasing extends ReTexturedCasing<EmitterCasing.CasingType> {

    public EmitterCasing() {
        super(new ResourceLocation("gtadditions","block/casing/emitter"));
        setDefaultState(getState(CasingType.EMITTER_LV));
    }

    public enum CasingType implements IStringSerializable {
        EMITTER_LV("emitter_lv", 1),
        EMITTER_MV("emitter_mv", 2),
        EMITTER_HV("emitter_hv", 3),
        EMITTER_EV("emitter_ev", 4),
        EMITTER_IV("emitter_iv", 5),
        EMITTER_LUV("emitter_luv", 6),
        EMITTER_ZPM("emitter_zpm", 7),
        EMITTER_UV("emitter_uv", 8),
        EMITTER_UHV("emitter_uhv", 9),
        EMITTER_UEV("emitter_uev", 10),
        EMITTER_UIV("emitter_uiv", 11),
        EMITTER_UMV("emitter_umv", 12),
        EMITTER_UXV("emitter_uxv", 13),
        EMITTER_MAX("emitter_max", 14);

        private final String name;
        private final int tier;

        CasingType(String name, int tier)
        {
            this.tier = tier;
            this.name = name;
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
