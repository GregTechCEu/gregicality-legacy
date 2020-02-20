package gregicadditions.machines;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import gnu.trove.map.hash.TIntObjectHashMap;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.IPatternCenterPredicate;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.util.IntRange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SingleLimitBlockPattern extends BlockPattern {

	private Predicate<BlockWorldState>[] predicatesCheckLayers;

	private int[] centerOffset = null;
	private final Predicate<BlockWorldState>[][][] blockMatches;
	private final RelativeDirection[] structureDir;
	private final int[][] aisleRepetitions;

	public SingleLimitBlockPattern(Predicate<BlockWorldState>[][][] predicatesIn, List<Pair<Predicate<BlockWorldState>, IntRange>> countMatches, RelativeDirection[] structureDir, int[][] aisleRepetitions, Predicate<BlockWorldState>[] predicatesCheckLayers) {
		super(predicatesIn, countMatches, new TIntObjectHashMap<>(), new ArrayList<>(), structureDir, aisleRepetitions);
		this.predicatesCheckLayers = predicatesCheckLayers;
		blockMatches = predicatesIn;
		this.aisleRepetitions = aisleRepetitions;
		this.structureDir = structureDir;

		initializeCenterOffsets();
	}

	private void initializeCenterOffsets() {
		label35: for (int x = 0; x < this.getPalmLength(); ++x) {
			for (int y = 0; y < this.getThumbLength(); ++y) {
				int z = 0;
				int minZ = 0;

				for (int maxZ = 0; z < this.getFingerLength(); ++z) {
					Predicate<BlockWorldState> predicate = this.blockMatches[z][y][x];
					if (predicate instanceof IPatternCenterPredicate) {
						this.centerOffset = new int[] { x, y, z, minZ, maxZ };
						break label35;
					}

					minZ += this.aisleRepetitions[z][0];
					maxZ += this.aisleRepetitions[z][1];
				}
			}
		}

		if (this.centerOffset == null) { throw new IllegalArgumentException("Didn't found center predicate"); }
	}

	@Override
	public PatternMatchContext checkPatternAt(World world, BlockPos centerPos, EnumFacing facing) {
		BlockWorldState worldState = new BlockWorldState();
		BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		PatternMatchContext matchContext = new PatternMatchContext();
		boolean findFirstAisle = false;
		int minZ = -centerOffset[4];
		for (int c = 0, z = minZ++, r; c < this.getFingerLength(); c++) {

			loop: for (r = 0; findFirstAisle ? r < aisleRepetitions[c][1] : z <= -centerOffset[3]; r++) {//Checking repeatable slices
				boolean hasBlock = false;

				for (int b = 0, y = -centerOffset[1]; b < this.getThumbLength(); b++, y++) {//Checking single slice
					for (int a = 0, x = -centerOffset[0]; a < this.getPalmLength(); a++, x++) {
						Predicate<BlockWorldState> predicate = this.blockMatches[c][b][a];
						setActualRelativeOffset(blockPos, x, y, z, facing);
						blockPos.setPos(blockPos.getX() + centerPos.getX(), blockPos.getY() + centerPos.getY(), blockPos.getZ() + centerPos.getZ());

						worldState.update(world, blockPos, matchContext, new PatternMatchContext());

						if (predicatesCheckLayers != null && predicatesCheckLayers[c] != null) {
							if (hasBlock && predicatesCheckLayers[c].test(worldState)) return null;
							else if (!hasBlock && predicatesCheckLayers[c].test(worldState)) hasBlock = true;
						} else hasBlock = true;
						if (!predicate.test(worldState)) {
							if (findFirstAisle) {
								if (r < aisleRepetitions[c][0]) {//retreat to see if the first aisle can start later
									r = c = 0;
									z = minZ++;
									matchContext.reset();
									findFirstAisle = false;
								}
							} else {
								z++;//continue searching for the first aisle
							}
							continue loop;
						}
					}
				}
				if (!hasBlock) return null;
				findFirstAisle = true;
				z++;

			}

			if (r < aisleRepetitions[c][0]) {//Repetitions out of range
				return null;
			}
		}

		return matchContext;
	}

	private BlockPos.MutableBlockPos setActualRelativeOffset(BlockPos.MutableBlockPos pos, int x, int y, int z, EnumFacing facing) {
		if (!ArrayUtils.contains(EnumFacing.HORIZONTALS, facing)) throw new IllegalArgumentException("Can rotate only horizontally");

		int[] c0 = new int[] { x, y, z }, c1 = new int[3];
		for (int i = 0; i < 3; i++) {
			switch (structureDir[i].getActualFacing(facing)) {
			case UP:
				c1[1] = c0[i];
				break;
			case DOWN:
				c1[1] = -c0[i];
				break;
			case WEST:
				c1[0] = -c0[i];
				break;
			case EAST:
				c1[0] = c0[i];
				break;
			case NORTH:
				c1[2] = -c0[i];
				break;
			case SOUTH:
				c1[2] = c0[i];
				break;
			}
		}
		return pos.setPos(c1[0], c1[1], c1[2]);
	}
}
