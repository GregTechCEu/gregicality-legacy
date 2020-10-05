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
        setTranslationKey("ga_multiblock_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.HYPER_CORE));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        HYPER_CORE("hyper_core"),
        HYPER_CORE_2("hyper_core_2"),
        HYPER_CORE_3("hyper_core_3"),
        FUSION_3("fusion_casing_3"),
        FUSION_4("fusion_casing_4"),
        FUSION_COIL_2("fusion_coil_2"),
        FUSION_COIL_3("fusion_coil_3"),
        HYPER_CASING("hyper_casing"),
        HYPER_CASING_2("hyper_casing_2");


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
