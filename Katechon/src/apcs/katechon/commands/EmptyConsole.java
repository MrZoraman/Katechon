package apcs.katechon.commands;

import apcs.katechon.logging.IConsole;

//TODO: documentation
public class EmptyConsole implements IConsole
{
	@Override
	public String readLine()
	{
		return null;
	}
}
