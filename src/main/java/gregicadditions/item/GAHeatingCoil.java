package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GAHeatingCoil extends VariantBlock<GAHeatingCoil.CasingType> {

    public GAHeatingCoil() {
        super(Material.IRON);
        setTranslationKey("ga_heating_coil");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.HEATING_COIL_1));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        HEATING_COIL_1("heating_coil_1", 1, 10000, 32, 8, null),
        HEATING_COIL_2("heating_coil_2", 2, 25000, 32, 8, null),
        HEATING_COIL_3("heating_coil_3", 3, 50000, 64, 16, null),
        HEATING_COIL_4("heating_coil_4", 4, 75000, 64, 16, null),
        HEATING_COIL_5("heating_coil_5", 5, 10000, 128, 32, null);

        private final String name;
        private final int coilTemperature;
        private final int level;
        private final int energyDiscount;
        private final Material material;

        CasingType(String name, int tier, int coilTemperature, int level, int energyDiscount, Material material) {
            this.name = name;
            this.coilTemperature = coilTemperature;
            this.level = level;
            this.energyDiscount = energyDiscount;
            this.material = material;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }
}
