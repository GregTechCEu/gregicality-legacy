package gregicadditions.integrations.FECompat;

import gregicadditions.GAConfig;

public class Constants {

    public static final long RATIO_LONG = GAConfig.EUtoRF.RATIO;
    public static final long MAX_VALUE_AS_LONG = Long.MAX_VALUE / RATIO_LONG;
    public static final long OVERFLOW_CHECK = Integer.MAX_VALUE / RATIO_LONG;

    public static int safeCastLongToInt(long v) {
        return v > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) v;
    }
}
