package gregicadditions.item.components;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class ConveyorCasing extends ReTexturedCasing<ConveyorCasing.CasingType> {

    public ConveyorCasing() {
        super(new ResourceLocation("gtadditions","block/casing/conveyor"));
        setTranslationKey("ga_conveyor_casing");
        setDefaultState(getState(CasingType.CONVEYOR_LV));
    }

    public enum CasingType implements IStringSerializable {
        CONVEYOR_LV("conveyor_lv", 1),
        CONVEYOR_MV("conveyor_mv", 2),
        CONVEYOR_HV("conveyor_hv", 3),
        CONVEYOR_EV("conveyor_ev", 4),
        CONVEYOR_IV("conveyor_iv", 5),
        CONVEYOR_LUV("conveyor_luv", 6),
        CONVEYOR_ZPM("conveyor_zpm", 7),
        CONVEYOR_UV("conveyor_uv", 8),
        CONVEYOR_UHV("conveyor_uhv", 9),
        CONVEYOR_UEV("conveyor_uev", 10),
        CONVEYOR_UIV("conveyor_uiv", 11),
        CONVEYOR_UMV("conveyor_umv", 12),
        CONVEYOR_UXV("conveyor_uxv", 13),
        CONVEYOR_MAX("conveyor_max", 14);

        private final String name;
        private final int tier;

        CasingType(String name, int tier) {
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
