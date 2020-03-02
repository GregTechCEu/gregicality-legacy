package gregicadditions.machines.miner;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Miner {

	public enum Type {
		LV(4, 1, 1, 0, 8000, 1),
		MV(2, 1, 1, 0, 16000, 2),
		HV(1, 1, 1, 0, 32000, 4),
		BASIC(1, 1, 3, 1, 0, 8),
		LARGE(1, 1, 5, 2, 0, 16),
		ADVANCE(1, 1, 7, 3, 0, 32);

		public final int tick;
		public final int block;
		public final int chunk;
		public final int fortune;
		public final int tankSize;
		public final int drillingFluidConsumePerTick;

		Type(int tick, int block, int chunk, int fortune, int tankSize, int drillingFluidConsumePerTick) {
			this.tick = tick;
			this.block = block;
			this.chunk = chunk;
			this.fortune = fortune;
			this.tankSize = tankSize;
			this.drillingFluidConsumePerTick = drillingFluidConsumePerTick;

		}

	}

	public static List<BlockPos> getBlockToMinePerChunk(TileEntityMiner miner, AtomicLong x, AtomicLong y, AtomicLong z, ChunkPos chunkPos) {
		List<BlockPos> blocks = new ArrayList<>();
		for (int i = 0; i < miner.type.block; i++) {
			if (y.get() >= 0 && miner.getTimer() % miner.type.tick == 0) {
				if (z.get() <= chunkPos.getZEnd()) {
					if (x.get() <= chunkPos.getXEnd()) {
						BlockPos blockPos = new BlockPos(x.get(), y.get(), z.get());
						Block block = miner.getWorld().getBlockState(blockPos).getBlock();
						if (miner.getWorld().getTileEntity(blockPos) == null) {
							OrePrefix orePrefix = OreDictUnifier.getPrefix(Item.getItemFromBlock(block).getDefaultInstance());
							if (orePrefix == OrePrefix.ore) {
								blocks.add(blockPos);
							}
						}
						x.incrementAndGet();
					} else {
						x.set(chunkPos.getXStart());
						z.incrementAndGet();
					}
				} else {
					z.set(chunkPos.getZStart());
					y.decrementAndGet();
				}
			}
		}
		return blocks;
	}


}
