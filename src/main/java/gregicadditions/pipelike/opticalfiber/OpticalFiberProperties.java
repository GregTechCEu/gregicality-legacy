package gregicadditions.pipelike.opticalfiber;

import java.util.Objects;

public class OpticalFiberProperties {

    public final int qubit;
    public final int parallel;

    public OpticalFiberProperties(int qubit, int baseAmperage) {
        this.qubit = qubit;
        this.parallel = baseAmperage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpticalFiberProperties)) return false;
        OpticalFiberProperties that = (OpticalFiberProperties) o;
        return qubit == that.qubit &&
                parallel == that.parallel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(qubit, parallel, "optical_fiber");
    }
}
