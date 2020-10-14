package gregicadditions.pipelike.opticalfiber.net;

import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import gregtech.api.pipenet.Node;
import gregtech.api.pipenet.PipeNet;
import gregtech.api.pipenet.WorldPipeNet;
import gregtech.api.util.PerTickLongCounter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class OpticalFiberNet extends PipeNet<OpticalFiberProperties> {

    private final PerTickLongCounter currentParallelCounter = new PerTickLongCounter(0L);
    private final PerTickLongCounter currentMaxQubitCounter = new PerTickLongCounter(0L);

    protected OpticalFiberNet(WorldPipeNet<OpticalFiberProperties, OpticalFiberNet> world) {
        super(world);
    }

    public void incrementCurrentAmperage(long parallel, long voltage) {
        currentParallelCounter.increment(worldData.getWorld(), parallel);
        long currentMaxQubit = currentMaxQubitCounter.get(worldData.getWorld());
        if (voltage > currentMaxQubit) {
            currentMaxQubitCounter.set(worldData.getWorld(), voltage);
        }
    }

    public List<RoutePath> computePatches(BlockPos startPos) {
        ArrayList<RoutePath> readyPaths = new ArrayList<>();
        RoutePath currentPath = new RoutePath();
        Node<OpticalFiberProperties> firstNode = getNodeAt(startPos);
        currentPath.path.put(startPos, firstNode.data);
        readyPaths.add(currentPath.cloneAndCompute(startPos));
        HashSet<BlockPos> observedSet = new HashSet<>();
        observedSet.add(startPos);
        MutableBlockPos currentPos = new MutableBlockPos(startPos);
        Stack<EnumFacing> moveStack = new Stack<>();
        main:
        while (true) {
            for (EnumFacing facing : EnumFacing.VALUES) {
                currentPos.move(facing);
                Node<OpticalFiberProperties> secondNode = getNodeAt(currentPos);
                if (secondNode != null && canNodesConnect(firstNode, facing, secondNode, this) && !observedSet.contains(currentPos)) {
                    BlockPos immutablePos = currentPos.toImmutable();
                    observedSet.add(immutablePos);
                    firstNode = secondNode;
                    moveStack.push(facing.getOpposite());
                    currentPath.path.put(immutablePos, getNodeAt(immutablePos).data);
                    if (secondNode.isActive) {
                        //if we are on active node, this is end of our path
                        RoutePath finalizedPath = currentPath.cloneAndCompute(immutablePos);
                        readyPaths.add(finalizedPath);
                    }
                    continue main;
                } else {
                    currentPos.move(facing.getOpposite());
                }
            }
            if (!moveStack.isEmpty()) {
                currentPos.move(moveStack.pop());
                //also remove already visited block from path
                currentPath.path.remove(currentPos);
            } else break;
        }
        return readyPaths;
    }


    @Override
    protected void writeNodeData(OpticalFiberProperties nodeData, NBTTagCompound tagCompound) {
        tagCompound.setInteger("qubit", nodeData.qubit);
        tagCompound.setInteger("parallel", nodeData.parallel);
    }

    @Override
    protected OpticalFiberProperties readNodeData(NBTTagCompound tagCompound) {
        int qubit = tagCompound.getInteger("qubit");
        int parallel = tagCompound.getInteger("parallel");
        return new OpticalFiberProperties(qubit, parallel);
    }
}
