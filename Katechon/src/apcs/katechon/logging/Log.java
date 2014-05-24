package apcs.katechon.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Static log class. This contains all static methods for easy and clean access throughout the code.
 * The init method must be called before it is used (otherwise the default PrintlnLogger will be used)!
 * @author Matt
 *
 */
public class Log
{
	/**
	 * Private constructor (static class!)
	 */
	private Log()
	{
	}
	
	private static final Set<ILogger> loggers;

	private static boolean debugging;
	
	static
	{
		loggers = new HashSet<ILogger>();
		//always prints out to the printstream
		loggers.add(new PrintstreamLogger(System.out));
		
		debugging = false;
	}
	
	/**
	 * Adds a logger to the set of loggers that will handle logging
	 * @param logger An implementation of ILogger.
	 */
	public static void initLogger(ILogger logger)
	{
		loggers.add(logger);
	}

	/**
	 * Logs a general info message
	 * @param message The message to log
	 */
	public static void info(String message)
	{
		for(ILogger logger : loggers)
		{
			logger.log(getTimeStamp() + " [INFO] " + "\t"  + message);
		}
	}

	/**
	 * Logs an error message. Something isn't quite right, but the engine can continue to operate.
	 * @param message The message to log
	 */
	public static void error(String message)
	{
		for(ILogger logger : loggers)
		{
			logger.log(getTimeStamp() + " [ERROR] " + "\t" + message);
		}
	}

	/**
	 * Logs a fatal error message. The error is so bad that the engine can no longer continue to operate.
	 * @param message The message to log
	 */
	public static void fatal(String message)
	{
		for(ILogger logger : loggers)
		{
			logger.log(getTimeStamp() + " [FATAL] " + "\t"  + message);
		}
	}

	/**
	 * Logs an exception
	 * @param ex The exception to log
	 */
	public static void exception(Exception ex)
	{
		for(ILogger logger : loggers)
		{
			logger.log(getTimeStamp() + " [EXCEPTION] " + ex.getClass().getCanonicalName() + ": " + ex.getLocalizedMessage());
			for(StackTraceElement element : ex.getStackTrace())
			{
				logger.log(getTimeStamp() + " [EXCEPTION] \t" + element.toString());
			}
		}
	}

	/**
	 * Sets the state of debugging
	 * @param debugging True if debugging, false if not debugging
	 */
	public static void setDebugging(boolean debugging)
	{
		Log.debugging = debugging;
	}

	/**
	 * Logs a debug message. If the logger is debugging, a debug message will be logged. Otherwise, the message is ignored
	 * @param message
	 */
	public static void debug(String message)
	{
		if(debugging)
		{
			for(ILogger logger : loggers)
			{
				logger.log(getTimeStamp() + " [DEBUG] " + "\t"  + message);
			}
		}
	}
	
	/**
	 * When the logger is finished. Calls the saveLog() method.
	 */
	public static void onEnd()
	{
		for(ILogger logger : loggers)
		{
			logger.saveLog();
		}
	}
	
	/**
	 * Gets the timestamp
	 * @return The current timestamp. This is appended to the beginning of every log message.
	 */
	private static String getTimeStamp()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
		return sdf.format(date);
	}
}
