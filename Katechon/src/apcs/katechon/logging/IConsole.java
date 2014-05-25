package apcs.katechon.logging;

/**
 * Represents a console. Implementations of this interface are expected to be able to read input from the user
 * @author Matt
 *
 */
public interface IConsole
{
	/**
	 * Gets a line input into the console
	 * @return The line read. Or null if there was no input.
	 */
	public String readLine();
}
