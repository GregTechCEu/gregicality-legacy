package gregicadditions.machines.miner;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public interface Miner {

	enum Type {
		LV(4, 1, 0, 1),
		MV(2, 1, 0, 2),
		HV(1, 1, 0, 4),
		BASIC(1, 3, 3, 8),
		LARGE(1, 5, 6, 16),
		ADVANCE(1, 7, 9, 32);

		public final int tick;
		public final int chunk;
		public final int fortune;
		public final int drillingFluidConsumePerTick;

		Type(int tick, int chunk, int fortune, int drillingFluidConsumePerTick) {
			this.tick = tick;
			this.chunk = chunk;
			this.fortune = fortune;
			this.drillingFluidConsumePerTick = drillingFluidConsumePerTick;

		}

	}

	Type getType();

	World getWorld();

	long getTimer();

	default long getNbBlock() {
		return 1L;
	}

	static List<BlockPos> getBlockToMinePerChunk(Miner miner, AtomicLong x, AtomicLong y, AtomicLong z, ChunkPos chunkPos) {
		List<BlockPos> blocks = new ArrayList<>();
		for (int i = 0; i < miner.getNbBlock(); i++) {
			if (y.get() >= 0 && miner.getTimer() % miner.getType().tick == 0) {
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
