package gregicadditions.utils;

import org.apache.logging.log4j.Logger;

public class GregicalityLogger {
    public static Logger logger;

    public GregicalityLogger() {
    }

    public static void init(Logger modLogger) {
        logger = modLogger;
    }

}