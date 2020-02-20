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
		setDefaultState(getState(CasingType.COKE_OVEN_BRICKS));
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
		return false;
	}

	public enum CasingType implements IStringSerializable {

		COKE_OVEN_BRICKS("coke_oven_bricks"),
		TUNGSTENSTEEL_GEARBOX_CASING("tungstensteel_gearbox_casing"),
		CHEMICALLY_INERT("chemically_inert_casing"),
		LARGE_ASSEMBLER("large_assembler_casing");

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
