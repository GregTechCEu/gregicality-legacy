package gregicadditions.item.fusion;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GAVacuumCasing extends VariantBlock<GAVacuumCasing.CasingType> {

    public GAVacuumCasing() {
        super(Material.IRON);
        setTranslationKey("ga_vacuum_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.VACUUM_1));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        VACUUM_1("vacuum_1", 1),
        VACUUM_2("vacuum_2", 2),
        VACUUM_3("vacuum_3", 3),
        VACUUM_4("vacuum_4", 4),
        VACUUM_5("vacuum_5", 5),
        VACUUM_6("vacuum_6", 6);



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
            return tier;
        }
    }
}
