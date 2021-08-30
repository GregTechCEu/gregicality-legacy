package gregicadditions.machines.multi;

public interface IMaintenance {

    byte getProblems();

    boolean hasProblems();

    void setMaintenanceFixed(int index);

    void storeTaped(boolean isTaped);
}
