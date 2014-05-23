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
	 * Save the log to disk if needed. Otherwise this can be left empty.
	 */
	public void saveLog();
	
	/**
	 * Gets a line input into the console
	 * @return The line read. Or null if there was no input.
	 */
	public String readLine();
}
