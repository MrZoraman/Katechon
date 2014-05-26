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
		}
		else
		{
			pack.addLeopard();
			Log.info("1 Leopard added to pack.");
		}
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("spawnleopard - Adds a leopard to the pack.")
		.log();
	}
}
