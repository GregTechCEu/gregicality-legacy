package gregicadditions.machines.multi.miner;

import gregicadditions.GAConfig;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public interface Miner {

    enum Type {
        STEAM(8, 1, 0, "", 1),
        LV(4, 1, 0, "", 1),
        MV(2, 1, 0, "", 2),
        HV(1, 1, 0, "", 4),
        BASIC(1, GAConfig.multis.largeMiner.basicMinerDiameter, GAConfig.multis.largeMiner.basicMinerFortune, fortuneString(GAConfig.multis.largeMiner.basicMinerFortune), 8),
        LARGE(1, GAConfig.multis.largeMiner.largeMinerDiameter, GAConfig.multis.largeMiner.largeMinerFortune, fortuneString(GAConfig.multis.largeMiner.largeMinerFortune), 16),
        ADVANCE(1, GAConfig.multis.largeMiner.advancedMinerDiameter, GAConfig.multis.largeMiner.advancedMinerFortune, fortuneString(GAConfig.multis.largeMiner.advancedMinerFortune), 32);

        public final int tick;
        public final int chunk;
        public final int fortune;
        public final int drillingFluidConsumePerTick;
        public final String fortuneString;

        Type(int tick, int chunk, int fortune, String fortuneString, int drillingFluidConsumePerTick) {
            this.tick = tick;
            this.chunk = chunk;
            this.fortune = fortune;
            this.drillingFluidConsumePerTick = drillingFluidConsumePerTick;
            this.fortuneString = fortuneString;
        }

    }

    Type getType();

    World getWorld();

    long getTimer();

    default long getNbBlock() {
        return 1L;
    }

    List orePrefixes = Arrays.asList(OrePrefix.ore, OrePrefix.valueOf("oreRich"), OrePrefix.valueOf("orePoor"), OrePrefix.valueOf("orePure"));

    static List<BlockPos> getBlockToMinePerChunk(Miner miner, AtomicLong x, AtomicLong y, AtomicLong z, ChunkPos chunkPos) {
        List<BlockPos> blocks = new ArrayList<>();
        for (int i = 0; i < miner.getNbBlock(); i++) {
            if (y.get() >= 0 && miner.getTimer() % miner.getType().tick == 0) {
                if (z.get() <= chunkPos.getZEnd()) {
                    if (x.get() <= chunkPos.getXEnd()) {
                        BlockPos blockPos = new BlockPos(x.get(), y.get(), z.get());
                        Block block = miner.getWorld().getBlockState(blockPos).getBlock();
                        if (miner.getWorld().getTileEntity(blockPos) == null) {
                            if (isOre(block)) {
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

    static boolean isOre(Block block) {
        OrePrefix orePrefix = OreDictUnifier.getPrefix(new ItemStack(block));
        return orePrefix != null && orePrefixes.contains(orePrefix);
    }


    static String fortuneString(int fortuneLevel) {

        final TreeMap<Integer, String> map = new TreeMap<Integer, String>();

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
            map.put(100, "C");
            map.put(90, "XC");
            map.put(50, "L");
            map.put(40, "XL");
            map.put(10, "X");
            map.put(9, "IX");
            map.put(5, "V");
            map.put(4, "IV");
            map.put(1, "I");

        int l =  map.floorKey(fortuneLevel);
        if ( fortuneLevel == l ) {
            return map.get(fortuneLevel);
        }
        return map.get(l) + fortuneString(fortuneLevel-l);
    }

}
