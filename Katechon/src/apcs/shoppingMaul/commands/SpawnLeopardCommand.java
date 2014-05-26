package apcs.shoppingMaul.commands;

import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;

public class SpawnLeopardCommand extends LeopardPackCommand
{
	public SpawnLeopardCommand(LeopardPack pack)
	{
		super(pack);
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
		
		getPack().addLeopards(amountOfLeopards);
		
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
