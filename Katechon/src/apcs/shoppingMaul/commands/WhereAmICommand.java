package apcs.shoppingMaul.commands;

import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.Board;

/**
 * Tells the user where the {@link apcs.shoppingMaul.Board Board} is at any given time.
 * @author Matt
 */
public class WhereAmICommand implements Command
{
	private Board board;
	
	public WhereAmICommand(Board board)
	{
		this.board = board;
	}

	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		Log.chainLog()
			.info("You are at: ")
			.info("\tX: " + board.getXPosition())
			.info("\tY: " + board.getYPosition())
			.log();
	}

}
