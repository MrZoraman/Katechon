package apcs.shoppingMaul.commands;

import java.util.Random;

import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.ShoppingMaul;
import apcs.shoppingMaul.SnowLeopard;

public class SpawnLeopardPackCommand implements Command
{

	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if(args.length > 0)
		{
			showUsage();
		}
		else
		{
			Random r = new Random(1237089230);
			
			int packSize = r.nextInt(20);
			
			for (int ii = 0; ii < packSize; ii++)
			{
				SnowLeopard leopard = new SnowLeopard(ShoppingMaul.getMainLeopard().getRightFace(), ShoppingMaul.getMainLeopard().getTopFace(), 10, 28, 1, false);
				KatechonEngine.getInstance().addDrawable(leopard, 1);
				KatechonEngine.getInstance().scheduleTask(leopard);
			}
			
			Log.info("Spawned a pack of size: " + packSize);
		}
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("spawnpack")
		.log();
	}

}
