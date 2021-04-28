package gregicadditions.item.components;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class PistonCasing extends ReTexturedCasing<PistonCasing.CasingType> {

    public PistonCasing() {
        super(new ResourceLocation("gtadditions","block/casing/piston"));
        setTranslationKey("ga_piston_casing");
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
        PISTON_UV("piston_uv", 8),
        PISTON_UHV("piston_uhv", 9),
        PISTON_UEV("piston_uev", 10),
        PISTON_UIV("piston_uiv", 11),
        PISTON_UMV("piston_umv", 12),
        PISTON_UXV("piston_uxv", 13),
        PISTON_MAX("piston_max", 14);

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
