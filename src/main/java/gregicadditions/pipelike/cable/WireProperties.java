package gregicadditions.pipelike.cable;

import java.util.Objects;

public class WireProperties {

    public final int voltage;
    public final int amperage;

    public WireProperties(int voltage, int baseAmperage) {
        this.voltage = voltage;
        this.amperage = baseAmperage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WireProperties)) return false;
        WireProperties that = (WireProperties) o;
        return voltage == that.voltage &&
                amperage == that.amperage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voltage, amperage);
    }
}
