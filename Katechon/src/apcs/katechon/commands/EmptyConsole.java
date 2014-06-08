package apcs.katechon.commands;

import apcs.katechon.logging.IConsole;

/**
 * Represents an empty console. It does nothing! This is used for the default console to avoid nullpointers.
 * @author Matt
 *
 */
public class EmptyConsole implements IConsole
{
	@Override
	public String readLine()
	{
		return null;
	}
}
