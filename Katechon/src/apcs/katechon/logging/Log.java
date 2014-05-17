package apcs.katechon.logging;

/**
 * Static log class. This contains all static methods for easy and clean access throughout the code. The init method must be called before it is used (otherwise the default PrintlnLogger will be used)!
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
	
	static
	{
		//default logger. Gets overwritten with the init() method.
		_logger = new PrintstreamLogger(System.out);
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
		_logger.info(message);
	}

	/**
	 * Logs an error message. Something isn't quite right, but the engine can continue to operate.
	 * @param message The message to log
	 */
	public static void error(String message)
	{
		_logger.error(message);
	}

	/**
	 * Logs a fatal error message. The error is so bad that the engine can no longer continue to operate.
	 * @param message The message to log
	 */
	public static void fatal(String message)
	{
		_logger.fatal(message);
	}

	/**
	 * Logs an exception
	 * @param ex The exception to log
	 */
	public static void exception(Exception ex)
	{
		_logger.exception(ex);
	}

	/**
	 * Sets the state of debugging
	 * @param debugging True if debugging, false if not debugging
	 */
	public static void setDebugging(boolean debugging)
	{
		_logger.setDebugging(debugging);
	}

	/**
	 * Logs a debug message. If the logger is debugging, a debug message will be logged. Otherwise, the message is ignored
	 * @param message
	 */
	public static void debug(String message)
	{
		_logger.debug(message);
	}
	
	public static void onEnd()
	{
		if (_logger instanceof FileLogger)
		{
			((FileLogger) _logger).writeToFile();
		}
	}
}
