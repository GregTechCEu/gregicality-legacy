package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GAMultiblockCasing extends VariantBlock<GAMultiblockCasing.CasingType> {

    public GAMultiblockCasing() {
        super(Material.IRON);
        setTranslationKey("ga_multiblock_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.ASSEMBLY_LINE_CASING));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        ASSEMBLY_LINE_CASING("assembly_line_casing", -1),
        CHEMICALLY_INERT("chemically_inert_casing", -1),
        LARGE_ASSEMBLER("large_assembler_casing", -1),
        TIERED_HULL_ULV("tiered_hull_ulv", 0),
        TIERED_HULL_LV("tiered_hull_lv", 1),
        TIERED_HULL_MV("tiered_hull_mv", 2),
        TIERED_HULL_HV("tiered_hull_hv", 3),
        TIERED_HULL_EV("tiered_hull_ev", 4),
        TIERED_HULL_IV("tiered_hull_iv", 5),
        TIERED_HULL_LUV("tiered_hull_luv", 6),
        TIERED_HULL_ZPM("tiered_hull_zpm", 7),
        TIERED_HULL_UV("tiered_hull_uv", 8),
        TIERED_HULL_MAX("tiered_hull_max", 14),
        CLADDED_REACTOR_CASING("cladded_reactor_casing", -1),
        PTFE_PIPE("ptfe_pipe_casing", -1),
        TUNGSTENSTEEL_GEARBOX_CASING("tungstensteel_gearbox_casing", -1);

        private final String name;
        private final int tier;

        CasingType(String name, int tier) {
            this.name = name;
            this.tier = tier;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getTier() {
            return this.tier;
        }

    }
}
