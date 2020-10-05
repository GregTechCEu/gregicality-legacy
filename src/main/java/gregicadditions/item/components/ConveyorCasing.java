package gregicadditions.item.components;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class ConveyorCasing extends VariantBlock<ConveyorCasing.CasingType> {

    public ConveyorCasing() {
        super(Material.IRON);
        setTranslationKey("ga_conveyor_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
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
        CONVEYOR_UEV("conveyor_uev", 10);

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
