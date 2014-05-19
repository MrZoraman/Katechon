package apcs.katechon.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	private static ILogger _logger;
	private static boolean debugging;
	
	static
	{
		//default logger. Gets overwritten with the init() method.
		_logger = new PrintstreamLogger(System.out);
		debugging = false;
	}
	
	/**
	 * Initializes a non-default logger.
	 * @param logger An implementation of ILogger.
	 */
	public static void init(ILogger logger)
	{
		_logger = logger;
	}

	/**
	 * Logs a general info message
	 * @param message The message to log
	 */
	public static void info(String message)
	{
		_logger.log(getTimeStamp() + " [INFO] " + "\t"  + message);
	}

	/**
	 * Logs an error message. Something isn't quite right, but the engine can continue to operate.
	 * @param message The message to log
	 */
	public static void error(String message)
	{
		_logger.log(getTimeStamp() + " [ERROR] " + "\t" + message);
	}

	/**
	 * Logs a fatal error message. The error is so bad that the engine can no longer continue to operate.
	 * @param message The message to log
	 */
	public static void fatal(String message)
	{
		_logger.log(getTimeStamp() + " [FATAL] " + "\t"  + message);
	}

	/**
	 * Logs an exception
	 * @param ex The exception to log
	 */
	public static void exception(Exception ex)
	{
		_logger.log(getTimeStamp() + " [EXCEPTION] " + ex.getClass().getCanonicalName() + ": " + ex.getLocalizedMessage());
		for(StackTraceElement element : ex.getStackTrace())
		{
			_logger.log(getTimeStamp() + " [EXCEPTION] \t" + element.toString());
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
			_logger.log(getTimeStamp() + " [DEBUG] " + "\t"  + message);
		}
	}
	
	/**
	 * When the logger is finished. Calls the saveLog() method.
	 */
	public static void onEnd()
	{
		_logger.saveLog();
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
