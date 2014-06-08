package apcs.katechon.logging;

/**
 * The interface for a logger class. Implementations of this interface will be plugged into the static log class init() method.
 * @author Matt
 *
 */
public interface ILogger
{
	/**
	 * Writes a message to whatever needs to be written do depending on the implementation
	 * @param message The message to log
	 */
	public void log(String message);
	
	/**
	 * Logs an array of messages. The implementation is expected to print these messages in an order that will make sense to the user (some logs go from the top down and some go from the bottom up)
	 * @param messages The messages to log
	 */
	public void chainLog(String[] messages);
	
	/**
	 * Save the log to disk if needed. Otherwise this can be left empty.
	 */
	public void saveLog();
}
