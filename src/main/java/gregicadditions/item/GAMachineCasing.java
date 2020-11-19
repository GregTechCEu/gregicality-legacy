package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GAMachineCasing extends VariantBlock<GAMachineCasing.CasingType> {

    public GAMachineCasing() {
        super(Material.IRON);
        setTranslationKey("ga_machine_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.CASING_UHV));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        CASING_UHV("casing_uhv"),
        CASING_UEV("casing_uev"),
        CASING_UIV("casing_uiv"),
        CASING_UMV("casing_umv"),
        CASING_UXV("casing_uxv");


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
