package gregicadditions.pipelike.opticalfiber.tile;

import gregicadditions.capabilities.GregicalityCapabilities;
import gregicadditions.capabilities.IQubitContainer;
import gregicadditions.pipelike.opticalfiber.OpticalFiberProperties;
import gregicadditions.pipelike.opticalfiber.OpticalFiberSize;
import gregicadditions.pipelike.opticalfiber.net.OpticalFiberNet;
import gregicadditions.pipelike.opticalfiber.net.RoutePath;
import gregicadditions.pipelike.opticalfiber.net.WorldOpticalFiberNet;
import gregtech.api.pipenet.tile.IPipeTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.PooledMutableBlockPos;
import net.minecraft.world.World;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class CableQubitContainer implements IQubitContainer {

    private final IPipeTile<OpticalFiberSize, OpticalFiberProperties> tileEntityCable;
    private WeakReference<OpticalFiberNet> currentOpticalFiberNet = new WeakReference<>(null);
    private long lastCachedUpdate;
    private List<RoutePath> pathsCache;

    public CableQubitContainer(IPipeTile<OpticalFiberSize, OpticalFiberProperties> tileEntityCable) {
        this.tileEntityCable = tileEntityCable;
    }

    @Override
    public long acceptQubitFromNetwork(EnumFacing side, long qubit, long parallel) {
        OpticalFiberNet opticalFiberNet = getQubitNet();
        if (opticalFiberNet == null) {
            return 0L;
        }
        List<RoutePath> paths = getPaths();
        long currentParallel = 0;
        for (RoutePath routePath : paths) {
            BlockPos destinationPos = routePath.destination;
            int blockedConnections = opticalFiberNet.getAllNodes().get(destinationPos).blockedConnections;
            currentParallel += dispatchQubitToNode(destinationPos, blockedConnections, qubit, parallel - currentParallel);

            if (currentParallel == parallel) {
                break; //do not continue if all amperes are exhausted
            }
        }
        opticalFiberNet.incrementCurrentAmperage(parallel, qubit);
        return currentParallel;
    }


    private long dispatchQubitToNode(BlockPos nodePos, int nodeBlockedConnections, long voltage, long amperage) {
        long currentParallel = 0L;
        //use pooled mutable to avoid creating new objects every tick
        World world = tileEntityCable.getPipeWorld();
        PooledMutableBlockPos blockPos = PooledMutableBlockPos.retain();
        for (EnumFacing facing : EnumFacing.VALUES) {
            if ((nodeBlockedConnections & 1 << facing.getIndex()) > 0) {
                continue; //do not dispatch energy to blocked sides
            }
            blockPos.setPos(nodePos).move(facing);
            if (!world.isBlockLoaded(nodePos)) {
                continue; //do not allow cables to load chunks
            }
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (tileEntity == null || tileEntityCable.getPipeBlock().getPipeTileEntity(tileEntity) != null) {
                continue; //do not emit into other cable tile entities
            }
            IQubitContainer qubitContainer = tileEntity.getCapability(GregicalityCapabilities.QBIT_CAPABILITY, facing.getOpposite());
            if (qubitContainer == null) continue;
            currentParallel += qubitContainer.acceptQubitFromNetwork(facing.getOpposite(), voltage, amperage - currentParallel);
            if (currentParallel == amperage)
                break;
        }
        blockPos.release();
        return currentParallel;
    }

    @Override
    public long getInputParallel() {
        return tileEntityCable.getNodeData().parallel;
    }

    @Override
    public long getInputQubit() {
        return tileEntityCable.getNodeData().qubit;
    }

    @Override
    public long getQubitCapacity() {
        return getInputQubit() * getInputParallel();
    }

    @Override
    public long changeQubit(long energyToAdd) {
        //just a fallback case if somebody will call this method
        return acceptQubitFromNetwork(EnumFacing.UP,
                energyToAdd / getInputQubit(),
                energyToAdd / getInputParallel()) * getInputQubit();
    }

    @Override
    public boolean outputsQubit(EnumFacing side) {
        return true;
    }

    @Override
    public boolean inputsQubit(EnumFacing side) {
        return true;
    }

    @Override
    public long getQubitStored() {
        return 0;
    }

    private void recomputePaths(OpticalFiberNet opticalFiberNet) {
        this.lastCachedUpdate = opticalFiberNet.getLastUpdate();
        this.pathsCache = opticalFiberNet.computePatches(tileEntityCable.getPipePos());
    }

    private List<RoutePath> getPaths() {
        OpticalFiberNet opticalFiberNet = getQubitNet();
        if (opticalFiberNet == null) {
            return Collections.emptyList();
        }
        if (pathsCache == null || opticalFiberNet.getLastUpdate() > lastCachedUpdate) {
            recomputePaths(opticalFiberNet);
        }
        return pathsCache;
    }

    private OpticalFiberNet getQubitNet() {
        OpticalFiberNet opticalFiberNet = this.currentOpticalFiberNet.get();
        if (opticalFiberNet != null && opticalFiberNet.isValid() &&
                opticalFiberNet.containsNode(tileEntityCable.getPipePos()))
            return opticalFiberNet; //return current net if it is still valid
        WorldOpticalFiberNet worldOpticalFiberNet = (WorldOpticalFiberNet) tileEntityCable.getPipeBlock().getWorldPipeNet(tileEntityCable.getPipeWorld());
        opticalFiberNet = worldOpticalFiberNet.getNetFromPos(tileEntityCable.getPipePos());
        if (opticalFiberNet != null) {
            this.currentOpticalFiberNet = new WeakReference<>(opticalFiberNet);
        }
        return opticalFiberNet;
    }

    @Override
    public boolean isOneProbeHidden() {
        return true;
    }
}
