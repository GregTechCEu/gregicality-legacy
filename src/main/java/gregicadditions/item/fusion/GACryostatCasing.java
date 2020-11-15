package gregicadditions.item.fusion;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GACryostatCasing extends VariantBlock<GACryostatCasing.CasingType> {

    public GACryostatCasing() {
        super(Material.IRON);
        setTranslationKey("ga_cryostat_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.CRYOSTAT_1));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        CRYOSTAT_1("cryostat_1", 1),
        CRYOSTAT_2("cryostat_2", 2),
        CRYOSTAT_3("cryostat_3", 3),
        CRYOSTAT_4("cryostat_4", 4),
        CRYOSTAT_5("cryostat_5", 5),
        CRYOSTAT_6("cryostat_6", 6);


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
