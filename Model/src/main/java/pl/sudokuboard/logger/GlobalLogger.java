package pl.sudokuboard.logger;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import java.util.Locale;
import org.slf4j.LoggerFactory;


public class GlobalLogger {

    private GlobalLogger() {
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalLogger.class);
    private static IMessageConveyor mc = new MessageConveyor(new Locale("pl", "PL"));

    public static void debug(I10N key, Object... args) {
        logger.debug(mc.getMessage(key, args));
    }

    public static void info(I10N key, Object... args) {
        logger.info(mc.getMessage(key, args));
    }

    public static void warn(I10N key, Object... args) {
        logger.warn(mc.getMessage(key, args));
    }

    public static String message(I10N key, Object... args) {
        return mc.getMessage(key, args);
    }

    public static void changeLanguage(Locale loc) {
        mc = new MessageConveyor(loc);
    }

    public static void error(I10N key, Object... args) {
        logger.error(mc.getMessage(key, args));
    }

}
