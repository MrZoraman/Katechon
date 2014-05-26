package apcs.shoppingMaul.commands;

import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;

public class CountLeopardsCommand extends LeopardPackCommand
{
	public CountLeopardsCommand(LeopardPack pack)
	{
		super(pack);
	}

	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if(args.length > 0)
		{
			showUsage();
		}
		else
		{
			int amountOfLeopards = getPack().size();
			
			if(amountOfLeopards > 1)
			{
				Log.info("There are " + getPack().size() + " leopards in the pack.");
			}
			else if (amountOfLeopards == 1)
			{
				Log.info("There is 1 leopard in the pack.");
			}
			else
			{
				Log.info("There aren't any leopards in the pack :(");
			}
		}
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("count leopards - Counts the amount of leopards in the pack.")
		.log();
	}
}
