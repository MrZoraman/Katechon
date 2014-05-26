package apcs.shoppingMaul.commands;

import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;

/**
 * This command adds a specified amount of leopards to the pack
 * @author Matt
 *
 */
public class AddLeopardCommand extends LeopardPackCommand
{
	public AddLeopardCommand(LeopardPack pack)
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
			Log.info("You can't add a negative amount of leopards!");
			return;
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
