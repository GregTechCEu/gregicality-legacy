package gregicadditions.capabilities;

import net.minecraft.util.EnumFacing;

public interface IQubitContainer {

    /**
     * @return amount of used amperes. 0 if not accepted anything.
     */
    long acceptQubitFromNetwork(EnumFacing side, long voltage, long parallel);

    boolean inputsQubit(EnumFacing side);

    default boolean outputsQubit(EnumFacing side) {
        return false;
    }

    /**
     * @param differenceAmount amount of energy to add (>0) or remove (<0)
     * @return amount of energy added or removed
     */
    long changeQubit(long differenceAmount);

    /**
     * Adds specified amount of energy to this energy container
     *
     * @param qubitToAdd amount of energy to add
     * @return amount of energy added
     */
    default long addQubit(long qubitToAdd) {
        return changeQubit(qubitToAdd);
    }

    /**
     * Removes specified amount of energy from this energy container
     *
     * @param qubitToRemove amount of energy to remove
     * @return amount of energy removed
     */
    default long removeQubit(long qubitToRemove) {
        return changeQubit(-qubitToRemove);
    }

    default long getQubitCanBeInserted() {
        return getQubitCapacity() - getQubitStored();
    }

    /**
     * Gets the stored electric energy
     */
    long getQubitStored();

    /**
     * Gets the largest electric energy capacity
     */
    long getQubitCapacity();

    /**
     * Gets the amount of energy packets per tick.
     */
    default long getOutputParallel() {
        return 0L;
    }

    /**
     * Gets the output in energy units per energy packet.
     */
    default long getOutputQubit() {
        return 0L;
    }

    /**
     * Gets the amount of energy packets this machine can receive
     */
    long getInputParallel();

    /**
     * Gets the maximum voltage this machine can receive in one energy packet.
     * Overflowing this value will explode machine.
     */
    long getInputQubit();

    default boolean isOneProbeHidden() {
        return false;
    }

}
