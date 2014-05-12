package apcs.katechon.logging;

/**
 * The interface for a logger class. Implementations of this interface will be plugged into the static log class init() method.
 * @author Matt
 *
 */
public interface ILogger
{
	/**
	 * Logs a general info message
	 * @param message The message to log
	 */
	public void info(String message);
	
	/**
	 * Logs an error message. Something isn't quite right, but the engine can continue to operate.
	 * @param message The message to log
	 */
	public void error(String message);
	
	/**
	 * Logs a fatal error message. The error is so bad that the engine can no longer continue to operate.
	 * @param message The message to log
	 */
	public void fatal(String message);
	
	/**
	 * Logs an exception
	 * @param ex The exception to log
	 */
	public void exception(Exception ex);
	
	/**
	 * Sets the state of debugging
	 * @param debugging True if debugging, false if not debugging
	 */
	public void setDebugging(boolean debugging);
	
	/**
	 * Logs a debug message. If the logger is debugging, a debug message will be logged. Otherwise, the message is ignored
	 * @param message
	 */
	public void debug(String message);
}
