package gregicadditions.jei;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.util.BlockInfo;
import gregtech.common.blocks.MetaBlocks;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class GAMultiblockShapeInfo extends MultiblockShapeInfo {

    private final BlockInfo[][][] blocks; //[z][y][x]

    public GAMultiblockShapeInfo(BlockInfo[][][] blocks) {
        super(blocks);
        this.blocks = blocks;
    }

    @Override
    public BlockInfo[][][] getBlocks() {
        return blocks;
    }

    public static Builder builder() {
        return builder(RIGHT, UP, FRONT);
    }

    public static Builder builder(BlockPattern.RelativeDirection charDir, BlockPattern.RelativeDirection stringDir, BlockPattern.RelativeDirection aisleDir) {
        return new GAMultiblockShapeInfo.Builder(charDir, stringDir, aisleDir);
    }


    public static class Builder extends MultiblockShapeInfo.Builder {

        private List<String[]> shape = new ArrayList<>();
        private Map<Character, BlockInfo> symbolMap = new HashMap<>();
        private BlockPattern.RelativeDirection[] structureDir = new BlockPattern.RelativeDirection[3];
        private final BlockPattern.RelativeDirection[] idealDir = {RIGHT, UP, FRONT};


        public Builder(BlockPattern.RelativeDirection charDir, BlockPattern.RelativeDirection stringDir, BlockPattern.RelativeDirection aisleDir) {
            this.structureDir[0] = charDir;
            this.structureDir[1] = stringDir;
            this.structureDir[2] = aisleDir;

            int flags = 0;
            for (int i = 0; i < 3; ++i) {
                switch (this.structureDir[i]) {
                    case UP:
                    case DOWN:
                        flags |= 1;
                        break;
                    case LEFT:
                    case RIGHT:
                        flags |= 2;
                        break;
                    case FRONT:
                    case BACK:
                        flags |= 4;
                }
            }
            if (flags != 7) {
                throw new IllegalArgumentException("Must have 3 different axes!");
            }
        }

        @Override
        public Builder aisle(String... data) {
            this.shape.add(data);
            return this;
        }

        @Override
        public Builder where(char symbol, BlockInfo value) {
            this.symbolMap.put(symbol, value);
            return this;
        }

        @Override
        public Builder where(char symbol, IBlockState blockState) {
            return where(symbol, new BlockInfo(blockState));
        }

        @Override
        public Builder where(char symbol, MetaTileEntity tileEntity, EnumFacing frontSide) {
            MetaTileEntityHolder holder = new MetaTileEntityHolder();
            holder.setMetaTileEntity(tileEntity);
            holder.getMetaTileEntity().setFrontFacing(frontSide);
            return where(symbol, new BlockInfo(MetaBlocks.MACHINE.getDefaultState(), holder));
        }

        private BlockInfo[][][] bakeArray() {
            Triple<Integer, Integer, Integer> maximumBounds = transformPos(shape.size(), shape.get(0).length, shape.get(0)[0].length(), 0, 0, 0, false); // Find the bounds of the transformed array by transforming the final position
            BlockInfo[][][] blockInfos = new BlockInfo[maximumBounds.getLeft()][maximumBounds.getMiddle()][maximumBounds.getRight()];
            for (int i = 0; i < shape.size(); i++) {
                String[] aisleEntry = shape.get(i);
                for (int j = 0; j < aisleEntry.length; j++) {
                    String rowEntry = aisleEntry[j];
                    for (int k = 0; k < rowEntry.length(); k++) {
                        BlockInfo positionData = symbolMap.getOrDefault(rowEntry.charAt(k), BlockInfo.EMPTY);
                        if (positionData.getTileEntity() != null && positionData.getTileEntity() instanceof MetaTileEntityHolder) {

                            MetaTileEntityHolder holder = (MetaTileEntityHolder) positionData.getTileEntity();

                            MetaTileEntityHolder newHolder = new MetaTileEntityHolder();
                            newHolder.setMetaTileEntity(holder.getMetaTileEntity().createMetaTileEntity(newHolder));
                            newHolder.getMetaTileEntity().setFrontFacing(holder.getMetaTileEntity().getFrontFacing());

                            positionData = new BlockInfo(positionData.getBlockState(), newHolder);
                        }
                        if (idealDir != structureDir) {
                            Triple<Integer, Integer, Integer> blockInfoPosition = transformPos(i, j, k, shape.size(), aisleEntry.length, rowEntry.length(), true);
                            blockInfos[blockInfoPosition.getLeft()][blockInfoPosition.getMiddle()][blockInfoPosition.getRight()] = positionData;
                        } else {
                            blockInfos[i][j][k] = positionData;
                        }
                    }
                }
            }
            return blockInfos;
        }

        // Transforms from the builder's structureDir to the default (RIGHT, UP, FRONT) at a position in the 3D array. If
        private Triple<Integer, Integer, Integer> transformPos(int posZ, int posY, int posX, int maxZ, int maxY, int maxX, boolean canReverseLines) { // This reversal is required because the array structure does not fit easily with the structureDir formatting.
            BlockPattern.RelativeDirection[] currentDir = this.structureDir.clone();
            int[] position = {posX, posY, posZ};
            int[] bounds = {maxX, maxY, maxZ};

            // First pass: swap all lefts, downs, and backs to their corresponding opposite sides.
            for (int i = 0; i < 3; i++) {
                if (currentDir[i] == LEFT || currentDir[i] == DOWN || currentDir[i] == BACK) {
                    if (canReverseLines) {
                        position[i] = bounds[i] - position[i] - 1;
                    }
                    switch (currentDir[i]) {
                        case LEFT:
                            currentDir[i] = RIGHT;
                            break;
                        case DOWN:
                            currentDir[i] = UP;
                            break;
                        case BACK:
                            currentDir[i] = FRONT;
                            break;
                    }

                }
            }

            // Second pass: check the first and second elements to see if they're the correct directions for their particular position, and if not, swap them.
            for (int i = 0; i < 2; i++) {
                if (currentDir[i] != idealDir[i]) {
                    for (int j = i; j < 3; j++) {
                        if (currentDir[j] == idealDir[i]) {
                            currentDir[j] = currentDir[i];
                            currentDir[i] = idealDir[i];
                            int tempPos = position[i];
                            position[i] = position[j];
                            position[j] = tempPos;
                        }
                    }
                }
            }

            return new MutableTriple<Integer, Integer, Integer>(position[2], position[1], position[0]);
        }

        @Override
        public GAMultiblockShapeInfo build() {
            return new GAMultiblockShapeInfo(bakeArray());
        }

    }

}