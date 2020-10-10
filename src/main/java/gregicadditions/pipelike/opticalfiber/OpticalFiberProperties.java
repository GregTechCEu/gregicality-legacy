package gregicadditions.pipelike.opticalfiber;

import java.util.Objects;

public class OpticalFiberProperties {

    public final int voltage;
    public final int amperage;

    public OpticalFiberProperties(int voltage, int baseAmperage) {
        this.voltage = voltage;
        this.amperage = baseAmperage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpticalFiberProperties)) return false;
        OpticalFiberProperties that = (OpticalFiberProperties) o;
        return voltage == that.voltage &&
                amperage == that.amperage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voltage, amperage, "optical_fiber");
    }
}
