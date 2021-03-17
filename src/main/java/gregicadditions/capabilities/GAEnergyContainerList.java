package gregicadditions.capabilities;

import gregtech.api.capability.IEnergyContainer;

import java.util.List;

public class GAEnergyContainerList extends EnergyContainerListWithAmps{

    private List<IEnergyContainer> containerList;

    public GAEnergyContainerList(List<IEnergyContainer> energyContainerList) {
        super(energyContainerList);
        this.containerList = energyContainerList;
    }

    /**
     * @return the voltage of the container with the highest voltage
     */
    public long getMaxInputVoltage() {
        long voltage = 0L;
        for(IEnergyContainer container : containerList) {
            voltage = Math.max(voltage, container.getInputVoltage());
        }
        return voltage;
    }

    /**
     * @return the voltage of the container with the highest voltage
     */
    public long getMaxOutputVoltage() {
        long voltage = 0L;
        for(IEnergyContainer container : containerList) {
            voltage = Math.max(voltage, container.getOutputVoltage());
        }
        return voltage;
    }
}
