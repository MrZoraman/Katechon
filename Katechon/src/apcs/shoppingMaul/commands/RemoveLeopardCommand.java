package apcs.shoppingMaul.commands;

import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;

public class RemoveLeopardCommand extends LeopardPackCommand
{
	public RemoveLeopardCommand(LeopardPack pack)
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
		
		if(amountOfLeopards < 0)
		{
			Log.info("You can't remove a negative amount of leopards!");
			return;
		}
		
		int leopardsRemoved = getPack().removeLeopards(amountOfLeopards);
		
		Log.info(leopardsRemoved + " leopard" + (leopardsRemoved > 1 ? "s" : "") + " removed from the pack.");
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("remove [amount] leopard(s) - Removes some leopards from the pack.")
		.log();
	}
}
