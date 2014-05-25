package apcs.shoppingMaul.commands;

import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.SnowLeopard;

public class SpawnLeopardCommand implements Command
{
	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if(args.length < 2)
		{
			showUsage();
		}
		else
		{
			int x;
			int y;
			
			try
			{
				String xArg = args[0];
				String yArg = args[1];
				
				x = Integer.parseInt(xArg);
				y = Integer.parseInt(yArg);
				
				SnowLeopard leopard = new SnowLeopard(x, y, 10, 28, 1, false);
				KatechonEngine.getInstance().addDrawable(leopard, 1);
				KatechonEngine.getInstance().scheduleTask(leopard);
				
				Log.info("Leopard spawned at " + x + ", " + y);
			}
			catch (NumberFormatException e)
			{
				showUsage();
			}
		}
	}
	
	private void showUsage()
	{
		Log.info("spawnleopard [x] [y]");
		Log.info("USAGE:");
	}
}
