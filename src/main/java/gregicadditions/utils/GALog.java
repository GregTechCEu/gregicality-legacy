package gregicadditions.utils;

import org.apache.logging.log4j.Logger;

public class GALog {
    public static Logger logger;

    public GALog() {
    }

    public static void init(Logger modLogger) {
        logger = modLogger;
    }

}