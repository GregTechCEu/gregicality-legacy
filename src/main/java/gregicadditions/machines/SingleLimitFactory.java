package gregicadditions.machines;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.BACK;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.RIGHT;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.UP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Joiner;

import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.util.IntRange;

public class SingleLimitFactory {
	private static final Joiner COMMA_JOIN = Joiner.on(",");
	private final List<String[]> depth = new ArrayList<>();
	private final List<int[]> aisleRepetitions = new ArrayList<>();
	private Predicate<BlockWorldState>[] predicatesCheckLayers;
	private final Map<Character, IntRange> countLimits = new HashMap<>();
	private final Map<Character, Predicate<BlockWorldState>> symbolMap = new HashMap<>();
	private int aisleHeight;
	private int rowWidth;
	private BlockPattern.RelativeDirection[] structureDir = new BlockPattern.RelativeDirection[3];

	private SingleLimitFactory(BlockPattern.RelativeDirection charDir, BlockPattern.RelativeDirection stringDir, BlockPattern.RelativeDirection aisleDir) {
		structureDir[0] = charDir;
		structureDir[1] = stringDir;
		structureDir[2] = aisleDir;
		int flags = 0;
		for (int i = 0; i < 3; i++) {
			switch (structureDir[i]) {
			case UP:
			case DOWN:
				flags |= 0x1;
				break;
			case LEFT:
			case RIGHT:
				flags |= 0x2;
				break;
			case FRONT:
			case BACK:
				flags |= 0x4;
				break;
			}
		}
		if (flags != 0x7) throw new IllegalArgumentException("Must have 3 different axes!");
		this.symbolMap.put(' ', k -> true);
	}

	/**
	 * Adds a repeatable aisle to this pattern.
	 */
	public SingleLimitFactory aisleRepeatable(int minRepeat, int maxRepeat, String... aisle) {
		if (!ArrayUtils.isEmpty(aisle) && !StringUtils.isEmpty(aisle[0])) {
			if (this.depth.isEmpty()) {
				this.aisleHeight = aisle.length;
				this.rowWidth = aisle[0].length();
			}

			if (aisle.length != this.aisleHeight) {
				throw new IllegalArgumentException("Expected aisle with height of " + this.aisleHeight + ", but was given one with a height of " + aisle.length + ")");
			} else {
				for (String s : aisle) {
					if (s.length() != this.rowWidth) {
						throw new IllegalArgumentException("Not all rows in the given aisle are the correct width (expected " + this.rowWidth + ", found one with " + s.length() + ")");
					}

					for (char c0 : s.toCharArray()) {
						if (!this.symbolMap.containsKey(c0)) {
							this.symbolMap.put(c0, null);
						}
					}
				}

				this.depth.add(aisle);
				if (minRepeat > maxRepeat) throw new IllegalArgumentException("Lower bound of repeat counting must smaller than upper bound!");
				aisleRepetitions.add(new int[] { minRepeat, maxRepeat });
				return this;
			}
		} else {
			throw new IllegalArgumentException("Empty pattern for aisle");
		}
	}

	/**
	 * Adds a single aisle to this pattern. (so multiple calls to this will increase the aisleDir by 1)
	 */
	public SingleLimitFactory aisle(String... aisle) {
		return aisleRepeatable(1, 1, aisle);
	}

	/**
	 * Set last aisle repeatable
	 */
	public SingleLimitFactory setRepeatable(int minRepeat, int maxRepeat) {
		if (minRepeat > maxRepeat) throw new IllegalArgumentException("Lower bound of repeat counting must smaller than upper bound!");
		aisleRepetitions.set(aisleRepetitions.size() - 1, new int[] { minRepeat, maxRepeat });
		return this;
	}

	/**
	 * Set last aisle repeatable
	 */
	public SingleLimitFactory setRepeatable(int repeatCount) {
		return setRepeatable(repeatCount, repeatCount);
	}

	@SuppressWarnings("unchecked")
	public SingleLimitFactory addCheck(int layer, Predicate<BlockWorldState> predicate) {
		if (this.predicatesCheckLayers == null) this.predicatesCheckLayers = new Predicate[this.depth.size()];
		this.predicatesCheckLayers[layer] = predicate;

		return this;
	}

	public static SingleLimitFactory start() {
		return new SingleLimitFactory(RIGHT, UP, BACK);
	}

	public static SingleLimitFactory start(BlockPattern.RelativeDirection charDir, BlockPattern.RelativeDirection stringDir, BlockPattern.RelativeDirection aisleDir) {
		return new SingleLimitFactory(charDir, stringDir, aisleDir);
	}

	public SingleLimitFactory where(char symbol, Predicate<BlockWorldState> blockMatcher) {
		this.symbolMap.put(symbol, blockMatcher);
		return this;
	}

	public BlockPattern build() {
		return new SingleLimitBlockPattern(this.makePredicateArray(), makeCountLimitsList(), structureDir, aisleRepetitions.toArray(new int[aisleRepetitions.size()][]), predicatesCheckLayers);
	}

	@SuppressWarnings("unchecked")
	private Predicate<BlockWorldState>[][][] makePredicateArray() {
		this.checkMissingPredicates();
		Predicate<BlockWorldState>[][][] predicate = (Predicate<BlockWorldState>[][][]) Array.newInstance(Predicate.class, this.depth.size(), this.aisleHeight, this.rowWidth);

		for (int i = 0; i < this.depth.size(); ++i) {
			for (int j = 0; j < this.aisleHeight; ++j) {
				for (int k = 0; k < this.rowWidth; ++k) {
					predicate[i][j][k] = this.symbolMap.get(this.depth.get(i)[j].charAt(k));
				}
			}
		}

		return predicate;
	}

	private void checkMissingPredicates() {
		List<Character> list = new ArrayList<>();

		for (Map.Entry<Character, Predicate<BlockWorldState>> entry : this.symbolMap.entrySet()) {
			if (entry.getValue() == null) {
				list.add(entry.getKey());
			}
		}

		if (!list.isEmpty()) {
			throw new IllegalStateException("Predicates for character(s) " + COMMA_JOIN.join(list) + " are missing");
		}
	}

	private List<Pair<Predicate<BlockWorldState>, IntRange>> makeCountLimitsList() {
		List<Pair<Predicate<BlockWorldState>, IntRange>> array = new ArrayList<>(countLimits.size());
		for (Map.Entry<Character, IntRange> entry : this.countLimits.entrySet()) {
			Predicate<BlockWorldState> predicate = this.symbolMap.get(entry.getKey());
			array.add(Pair.of(predicate, entry.getValue()));
		}
		return array;
	}

}
