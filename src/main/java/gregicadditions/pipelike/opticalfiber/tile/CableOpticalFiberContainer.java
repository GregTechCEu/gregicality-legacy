package gregicadditions.pipelike.opticalfiber.tile;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.IOpticalFiberContainer;
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

public class CableOpticalFiberContainer implements IOpticalFiberContainer {

    private final IPipeTile<OpticalFiberSize, OpticalFiberProperties> tileEntityCable;
    private WeakReference<OpticalFiberNet> currentEnergyNet = new WeakReference<>(null);
    private long lastCachedUpdate;
    private List<RoutePath> pathsCache;

    public CableOpticalFiberContainer(IPipeTile<OpticalFiberSize, OpticalFiberProperties> tileEntityCable) {
        this.tileEntityCable = tileEntityCable;
    }

    @Override
    public long acceptEnergyFromNetwork(EnumFacing side, long voltage, long amperage) {
        OpticalFiberNet opticalFiberNet = getEnergyNet();
        if (opticalFiberNet == null) {
            return 0L;
        }
        List<RoutePath> paths = getPaths();
        long amperesUsed = 0;
        for (RoutePath routePath : paths) {
            BlockPos destinationPos = routePath.destination;
            int blockedConnections = opticalFiberNet.getAllNodes().get(destinationPos).blockedConnections;
            amperesUsed += dispatchEnergyToNode(destinationPos, blockedConnections, voltage, amperage - amperesUsed);

            if (amperesUsed == amperage) {
                break; //do not continue if all amperes are exhausted
            }
        }
        opticalFiberNet.incrementCurrentAmperage(amperage, voltage);
        return amperesUsed;
    }


    private long dispatchEnergyToNode(BlockPos nodePos, int nodeBlockedConnections, long voltage, long amperage) {
        long amperesUsed = 0L;
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
            IOpticalFiberContainer energyContainer = tileEntity.getCapability(GregicAdditionsCapabilities.OPTICAL_FIBER_CAPABILITY, facing.getOpposite());
            if (energyContainer == null) continue;
            amperesUsed += energyContainer.acceptEnergyFromNetwork(facing.getOpposite(), voltage, amperage - amperesUsed);
            if (amperesUsed == amperage)
                break;
        }
        blockPos.release();
        return amperesUsed;
    }

    @Override
    public long getInputAmperage() {
        return tileEntityCable.getNodeData().amperage;
    }

    @Override
    public long getInputVoltage() {
        return tileEntityCable.getNodeData().voltage;
    }

    @Override
    public long getEnergyCapacity() {
        return getInputVoltage() * getInputAmperage();
    }

    @Override
    public long changeEnergy(long energyToAdd) {
        //just a fallback case if somebody will call this method
        return acceptEnergyFromNetwork(EnumFacing.UP,
                energyToAdd / getInputVoltage(),
                energyToAdd / getInputAmperage()) * getInputVoltage();
    }

    @Override
    public boolean outputsEnergy(EnumFacing side) {
        return true;
    }

    @Override
    public boolean inputsEnergy(EnumFacing side) {
        return true;
    }

    @Override
    public long getEnergyStored() {
        return 0;
    }

    private void recomputePaths(OpticalFiberNet opticalFiberNet) {
        this.lastCachedUpdate = opticalFiberNet.getLastUpdate();
        this.pathsCache = opticalFiberNet.computePatches(tileEntityCable.getPipePos());
    }

    private List<RoutePath> getPaths() {
        OpticalFiberNet opticalFiberNet = getEnergyNet();
        if (opticalFiberNet == null) {
            return Collections.emptyList();
        }
        if (pathsCache == null || opticalFiberNet.getLastUpdate() > lastCachedUpdate) {
            recomputePaths(opticalFiberNet);
        }
        return pathsCache;
    }

    private OpticalFiberNet getEnergyNet() {
        OpticalFiberNet currentOpticalFiberNet = this.currentEnergyNet.get();
        if (currentOpticalFiberNet != null && currentOpticalFiberNet.isValid() &&
                currentOpticalFiberNet.containsNode(tileEntityCable.getPipePos()))
            return currentOpticalFiberNet; //return current net if it is still valid
        WorldOpticalFiberNet worldOpticalFiberNet = (WorldOpticalFiberNet) tileEntityCable.getPipeBlock().getWorldPipeNet(tileEntityCable.getPipeWorld());
        currentOpticalFiberNet = worldOpticalFiberNet.getNetFromPos(tileEntityCable.getPipePos());
        if (currentOpticalFiberNet != null) {
            this.currentEnergyNet = new WeakReference<>(currentOpticalFiberNet);
        }
        return currentOpticalFiberNet;
    }

    @Override
    public boolean isOneProbeHidden() {
        return true;
    }
}
