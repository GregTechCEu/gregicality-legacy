package gregicadditions.integrations.FECompat;

import gregicadditions.GAConfig;

public class variables {
    public static final long RATIO_LONG = GAConfig.EUtoRF.RATIO;
    public static final long MAX_VALUE_AS_LONG = Long.MAX_VALUE / RATIO_LONG;
    public static final long OVERFLOW_CHECK = Integer.MAX_VALUE / RATIO_LONG;
    public static final int MAX_VALUE_AS_INT = Integer.MAX_VALUE / GAConfig.EUtoRF.RATIO;
}
