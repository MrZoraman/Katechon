package apcs.katechon.commands.baseCommands;

import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;

/**
 * A command that causes the game to close. Also has the ability to try to force the game to close.
 * @author Matt
 *
 */
public class ExitGameCommand implements Command
{
	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if(args.length > 0)
		{
			if(args[0].equals("--force"))
			{
				System.exit(2);
			}
			else
			{
				showUsage();
			}
		}
		else
		{
			KatechonEngine.getInstance().end();
		}
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("exit - Exits the game")
			.info("exit --force - forces the game to exit. Data may be lost.")
		.log();
	}
}
