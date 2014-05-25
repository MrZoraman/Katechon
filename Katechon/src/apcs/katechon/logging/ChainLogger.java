package apcs.katechon.logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static apcs.katechon.logging.Log.*;

/**
 * Chains messages together and logs them all at once with the ILogger's chainLog method.
 * 		This allows implementations to log groups of messages in the correct, human readable order
 * 		(good for help menus and other messages that span multiple lines).
 * @author Matt
 *
 */
public class ChainLogger
{
	private final List<String> messages;
	private final boolean debug;
	private final Set<ILogger> loggers;
	
	/**
	 * Constructor. Only visible within the apcs.katechon.logging package
	 * @param loggers The loggers that will print the messages gathered in this ChainLogger
	 * @param debug If the log is debugging or not
	 */
	ChainLogger(Set<ILogger> loggers, boolean debug)
	{
		this.loggers = loggers;
		this.messages = new ArrayList<String>();
		this.debug = debug;
	}
	
	/**
	 * Prints all of the messages to the various loggers.
	 */
	public void log()
	{
		for(ILogger logger : loggers)
		{
			logger.chainLog(messages.toArray(new String[messages.size()]));
		}
	}
	
	/**
	 * Adds an info message to the chain. See {@link Log#info(String)}. <b> When you are done logging don't forget to call {@link #log()}!</b>
	 * @param message The message to log
	 * @return The same chain logger object (for method chaining)
	 */
	public ChainLogger info(String message)
	{
		messages.add(toInfoMessage(message));
		return this;
	}
	
	/**
	 * Adds an error message to the chain. See {@link Log#error(String)}. <b> When you are done logging don't forget to call {@link #log()}!</b>
	 * @param message The message to log
	 * @return The same chain logger object (for method chaining)
	 */
	public ChainLogger error(String message)
	{
		messages.add(toErrorMessage(message));
		return this;
	}
	
	/**
	 * Adds a fatal message to the chain. See {@link Log#fatal(String)}. <b> When you are done logging don't forget to call {@link #log()}!</b>
	 * @param message The message to log
	 * @return The same chain logger object (for method chaining)
	 */
	public ChainLogger fatal(String message)
	{
		messages.add(toFatalMessage(message));
		return this;
	}
	
	/**
	 * Adds an exception message to the chain. See {@link Log#exception(Exception)}. <b> When you are done logging don't forget to call {@link #log()}!</b>
	 * @param ex The message to log
	 * @return The same chain logger object (for method chaining)
	 */
	public ChainLogger exception(Exception ex)
	{
		messages.addAll(Arrays.asList(toExceptionMessage(ex)));
		return this;
	}
	
	/**
	 * Adds a debug message to the chain if debugging is set to true in the Log class. See {@link Log#debug(String)}. <b> When you are done logging don't forget to call {@link #log()}!</b>
	 * @param message The message to log
	 * @return The same chain logger object (for method chaining)
	 */
	public ChainLogger debug(String message)
	{
		if(debug)
		{
			messages.add(toDebugMessage(message));
		}
		
		return this;
	}
}
