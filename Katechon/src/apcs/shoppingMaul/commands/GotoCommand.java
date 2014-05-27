package apcs.shoppingMaul.commands;

import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.Board;

//TODO: document, fix
public class GotoCommand implements Command
{
	private final Board board;
	
	public GotoCommand(Board board)
	{
		this.board = board;
	}

	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if(args.length < 2)
		{
			showUsage();
		}
		else
		{
			try
			{
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				
				board.goTo(x, y);
				Log.info("Moved to position " + x + ", " + y);
			}
			catch (Exception e)
			{
				Log.error("You need to type numbers for the x and y coordinates!");
			}
		}
	}

	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("goto [x] [y]")
		.log();
	}
}
