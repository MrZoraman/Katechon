package apcs.shoppingMaul.commands;

import apcs.katechon.commands.parser.Command;
import apcs.shoppingMaul.LeopardPack;

/**
 * This is a base class for commands that interact with the leopard pack. It's only purpose is to have a common way of accessing the Leopard pack instance
 * @author Matt
 *
 */
public abstract class LeopardPackCommand implements Command
{
	/**
	 * Constructor
	 * @param pack The leopard pack instance
	 */
	public LeopardPackCommand(LeopardPack pack)
	{
		this.pack = pack;
	}
	
	private final LeopardPack pack;
	
	/**
	 * @return The instance of the leopad pack
	 */
	protected final LeopardPack getPack()
	{
		return pack;
	}
}
