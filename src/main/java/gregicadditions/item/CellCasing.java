package gregicadditions.item;

import gregtech.api.GTValues;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class CellCasing extends VariantBlock<CellCasing.CellType> {
    public CellCasing() {
        super(Material.IRON);
        setTranslationKey("cell_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CellType.CELL_LUV));
    }


    public enum CellType implements IStringSerializable {

        CELL_UV("cell_uv", 25600000000L, GTValues.UV),
        CELL_ZPM("cell_zpm", 6400000000L, GTValues.ZPM),
        CELL_LUV("cell_luv", 1600000000L, GTValues.LuV),
        CELL_IV("cell_iv", 400000000L, GTValues.IV),
        CELL_EV("cell_ev", 100000000L, GTValues.EV),
        CELL_HV("cell_hv", 25000000L, GTValues.HV);

        private final String name;
        private final long storage;
        private final int tier;

        CellType(String name, long storage, int tier) {
            this.name = name;
            this.storage = storage;
            this.tier = tier;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public long getStorage() {
            return storage;
        }

        public int getTier() {
            return tier;
        }

    }
}
