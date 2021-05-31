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
        setDefaultState(getState(CasingType.TUNGSTENSTEEL_GEARBOX_CASING));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        TUNGSTENSTEEL_GEARBOX_CASING("tungstensteel_gearbox_casing"),
        CHEMICALLY_INERT("chemically_inert_casing"),
        LARGE_ASSEMBLER("large_assembler_casing"),
        TIERED_HULL_ULV("tiered_hull_ulv"),
        TIERED_HULL_LV("tiered_hull_lv"),
        TIERED_HULL_MV("tiered_hull_mv"),
        TIERED_HULL_HV("tiered_hull_hv"),
        TIERED_HULL_EV("tiered_hull_ev"),
        TIERED_HULL_IV("tiered_hull_iv"),
        TIERED_HULL_LUV("tiered_hull_luv"),
        TIERED_HULL_ZPM("tiered_hull_zpm"),
        TIERED_HULL_UV("tiered_hull_uv"),
        TIERED_HULL_MAX("tiered_hull_max"),
        CLADDED_REACTOR_CASING("cladded_reactor_casing"),
        PTFE_PIPE("ptfe_pipe_casing");

        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }
}
