package apcs.shoppingMaul.commands;

import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;

public class SpawnLeopardCommand implements Command
{
	private final LeopardPack pack;
	
	public SpawnLeopardCommand(LeopardPack pack)
	{
		this.pack = pack;
	}
	
	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if(args.length > 0)
		{
			showUsage();
			return;
		}
		
		int amountOfLeopards = 1;
		if(preArgs.length > 0)
		{
			try
			{
				amountOfLeopards = Integer.parseInt(preArgs[0]);
			}
			catch (Exception e)
			{
				Log.error("'" + preArgs[0] + "' is not a number!");
				showUsage();
				return;
			}
		}
		
		for(int ii = 0; ii < amountOfLeopards; ii++)
		{
			pack.addLeopard();
		}
		
		Log.info(amountOfLeopards + " leopard" + (amountOfLeopards > 1 ? "s" : "") + " added to the pack.");
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("add [amount] leopard(s) - Adds some leopards to the pack.")
		.log();
	}
}
