package fr.paris.lutece.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

/**
 * Inspired by AppLogService, but with .info only, to be able to troubleshoot the tests base class
 */
public class TestLogService
{

    private static final String LOGGER_EVENTS = "lutece.event";
    private static Logger _loggerEvents = LogManager.getLogger(LOGGER_EVENTS);

    public static void info(Object objToLog)
    {
        _loggerEvents.info(objToLog);
    }

    public static void info(String message, Object... params)
    {
        _loggerEvents.info(message, params);
    }

    public static void info(String message, Supplier<?>... paramSuppliers)
    {
        _loggerEvents.info(message, paramSuppliers);
    }
}
