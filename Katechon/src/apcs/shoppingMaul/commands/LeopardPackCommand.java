package apcs.shoppingMaul.commands;

import apcs.katechon.commands.parser.Command;
import apcs.shoppingMaul.LeopardPack;

public abstract class LeopardPackCommand implements Command
{
	public LeopardPackCommand(LeopardPack pack)
	{
		this.pack = pack;
	}
	
	private final LeopardPack pack;
	
	protected final LeopardPack getPack()
	{
		return pack;
	}
}
