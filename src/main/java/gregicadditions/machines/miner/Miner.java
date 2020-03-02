package gregicadditions.machines.miner;

public interface Miner {

	enum Type {
		LV(4, 1, 0, 0),
		MV(2, 1, 0,0),
		HV(1, 1, 0,0),
		BASIC(1, 1, 1,1),
		LARGE(1, 1, 2,2),
		ADVANCE(1, 1, 4,3);

		private int tick;
		private int block;
		private int chunk;
		private int fortune;

		Type(int tick, int block, int chunk, int fortune) {
			this.tick= tick;
			this.block = block;
			this.chunk = chunk;
			this.fortune=fortune;

		}

	}



}
