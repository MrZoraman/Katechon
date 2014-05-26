package apcs.shoppingMaul.commands;

import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;

public class RemoveLeopardCommand implements Command
{
	private final LeopardPack pack;
	
	public RemoveLeopardCommand(LeopardPack pack)
	{
		this.pack = pack;
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
			if(pack.size() > 0)
			{
				pack.removeLeopard();
				Log.info("1 Leopard removed from pack.");
			}
			else
			{
				Log.info("This pack is empty. :(");
			}
		}
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("removeleopard - Removes a leopard from the pack.")
		.log();
	}
}
