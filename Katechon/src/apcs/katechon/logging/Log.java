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
		_logger = new PrintlnLogger();
	}
	
	public static void init(ILogger logger)
	{
		_logger = logger;
	}

	public static void info(String message)
	{
		_logger.info(message);
	}

	public static void error(String message)
	{
		_logger.error(message);
	}

	public static void fatal(String message)
	{
		_logger.fatal(message);
	}

	public static void exception(Exception ex)
	{
		_logger.exception(ex);
	}

	public static void setDebugging(boolean debugging)
	{
		_logger.setDebugging(debugging);
	}

	public static void debug(String message)
	{
		_logger.debug(message);
	}
}
