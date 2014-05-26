package apcs.katechon.commands.baseCommands;

import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.parser.Command;

public class ExitGameCommand implements Command
{
	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		KatechonEngine.getInstance().end();
	}
}
