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
        setDefaultState(getState(GAVacuumCasing.CasingType.FUSION_3));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        FUSION_3("fusion_casing_3"),
        FUSION_4("fusion_casing_4"),
        FUSION_COIL_2("fusion_coil_2"),
        FUSION_COIL_3("fusion_coil_3");


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
