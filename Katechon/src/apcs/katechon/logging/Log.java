package apcs.katechon.logging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	 * Begins a chain log. This is the preferred method of logging multiple messages at once because it 
	 * 		will print the messages in an implementation dependent manner so that they stay in order among 
	 * 		the various different implementations (some log from the top down and some log from the bottom up).
	 * @return A ChainLogger object to handle all of your logging needs :)
	 */
	public static ChainLogger chainLog()
	{
		return new ChainLogger(loggers, debugging);
	}

	/**
	 * Logs a general info message
	 * @param message The message to log
	 */
	public static void info(String message)
	{
		for(ILogger logger : loggers)
		{
			logger.log(toInfoMessage(message));
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
			logger.log(toErrorMessage(message));
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
			logger.log(toFatalMessage(message));
		}
	}

	/**
	 * Logs an exception
	 * @param ex The exception to log
	 */
	public static void exception(Exception ex)
	{
		String[] exceptionMessage = toExceptionMessage(ex);
		
		for(ILogger logger : loggers)
		{
			for(String messagePart : exceptionMessage)
			{
				logger.log(messagePart);
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
				logger.log(toDebugMessage(message));
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
	 * Formats a message to be an info message
	 * @param message The message to format
	 * @return A properly formatted info message
	 */
	static String toInfoMessage(String message)
	{
		return getTimeStamp() + "[INFO] " + message;
	}
	
	/**
	 * Formats a message to be an error message
	 * @param message The message to format
	 * @return A properly formatted error message
	 */
	static String toErrorMessage(String message)
	{
		return getTimeStamp() + "[ERROR] " + message;
	}
	
	/**
	 * Formats a message to be a fatal message
	 * @param message The message to format
	 * @return The properly formatted message
	 */
	static String toFatalMessage(String message)
	{
		return getTimeStamp() + "[FATAL] " + message;
	}
	
	/**
	 * Formats an exception to be an exception message
	 * @param ex The exception to format
	 * @return An array of the lines, with the first index being the error message and the rest being the stack trace
	 */
	static String[] toExceptionMessage(Exception ex)
	{
		List<String> message = new ArrayList<String>();
		
		message.add(getTimeStamp() + "[EXCEPTION] " + ex.getClass().getCanonicalName() + ": " + ex.getLocalizedMessage());
		for(StackTraceElement element : ex.getStackTrace())
		{
			message.add(getTimeStamp() + "[EXCEPTION] " + element.toString());
		}
		
		return message.toArray(new String[message.size()]);
	}
	
	/**
	 * Formats a message to be a debug message
	 * @param message The message to format
	 * @return The properly formatted message
	 */
	static String toDebugMessage(String message)
	{
		return getTimeStamp() + "[DEBUG] " + message;
	}
	
	/**
	 * Gets the timestamp
	 * @return The current timestamp. This is appended to the beginning of every log message.
	 */
	private static String getTimeStamp()
	{
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
//		return sdf.format(date);
		return "";
	}
}
