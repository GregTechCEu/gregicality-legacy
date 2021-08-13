package gregicadditions;

public class GAUtility {

    public static byte getTierByVoltage(long voltage) {
        byte tier = 0;

        do {
            ++tier;
            if (tier >= GAValues.V.length) {
                return (byte)Math.min(GAValues.V.length - 1, tier);
            }

            if (voltage == GAValues.V[tier]) {
                return tier;
            }
        } while(voltage >= GAValues.V[tier]);

        return (byte)Math.max(0, tier - 1);
    }

    public static int setBetweenInclusive(int value, int start, int end) {
        if (value < start)
            return start;
        return Math.min(value, end);
    }
}
