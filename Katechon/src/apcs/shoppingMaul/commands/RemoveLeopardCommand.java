package apcs.shoppingMaul.commands;

import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;

/**
 * Removes a specified amount of leopards from the pack (or 1 if the amount is not specified)
 * @author Matt
 *
 */
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
		if(preArgs.length > 0 && !(preArgs[0].equals("leopard") || preArgs[0].equals("leopards")))
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
