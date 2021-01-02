package gregicadditions.capabilities.impl;

import gregicadditions.capabilities.IQubitContainer;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class QubitContainerList implements IQubitContainer {

    private List<IQubitContainer> qubitContainerList;

    public QubitContainerList(List<IQubitContainer> qubitContainerList) {
        this.qubitContainerList = qubitContainerList;
    }

    @Override
    public long acceptQubitFromNetwork(EnumFacing side, long voltage, long amperage) {
        long amperesUsed = 0L;
        for (IQubitContainer energyContainer : qubitContainerList) {
            amperesUsed += energyContainer.acceptQubitFromNetwork(null, voltage, amperage);
            if (amperage == amperesUsed) break;
        }
        return amperesUsed;
    }

    @Override
    public boolean inputsQubit(EnumFacing side) {
        return true;
    }

    @Override
    public boolean outputsQubit(EnumFacing side) {
        return true;
    }

    @Override
    public long changeQubit(long energyToAdd) {
        long energyAdded = 0L;
        for (IQubitContainer energyContainer : qubitContainerList) {
            energyAdded += energyContainer.changeQubit(energyToAdd - energyAdded);
            if (energyAdded == energyToAdd) break;
        }
        return energyAdded;
    }

    @Override
    public long getQubitStored() {
        return qubitContainerList.stream()
                .mapToLong(IQubitContainer::getQubitStored)
                .sum();
    }

    @Override
    public long getQubitCapacity() {
        return qubitContainerList.stream()
                .mapToLong(IQubitContainer::getQubitCapacity)
                .sum();
    }

    @Override
    public long getInputParallel() {
        return 1L;
    }

    @Override
    public long getOutputParallel() {
        return 1L;
    }

    @Override
    public long getInputQubit() {
        return qubitContainerList.stream()
                .mapToLong(v -> v.getInputQubit() * v.getInputParallel())
                .sum();
    }

    @Override
    public long getOutputQubit() {
        return qubitContainerList.stream()
                .mapToLong(v -> v.getOutputQubit() * v.getOutputParallel())
                .sum();
    }

}
