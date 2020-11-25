package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GAMultiblockCasing2 extends VariantBlock<GAMultiblockCasing2.CasingType> {

    public GAMultiblockCasing2() {
        super(Material.IRON);
        setTranslationKey("ga_multiblock_casing2");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.TIERED_HULL_UHV));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        TIERED_HULL_UHV("tiered_hull_uhv"),
        TIERED_HULL_UEV("tiered_hull_uev"),
        TIERED_HULL_UIV("tiered_hull_uiv"),
        TIERED_HULL_UMV("tiered_hull_umv"),
        TIERED_HULL_UXV("tiered_hull_uxv"),
        NAQUADRIA_CHARGE("naquadria_charge"),
        STELLAR_CONTAINMENT("stellar_containment"),
        BIO_REACTOR("bio_reactor_casing"),
        LASER_ENGRAVER("laser_engraver_casing");

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
