package apcs.shoppingMaul.commands;

import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.parser.Command;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.LeopardPack;
import apcs.shoppingMaul.ShoppingMaul;
import apcs.shoppingMaul.SnowLeopard;

public class SpawnLeopardPackCommand implements Command
{
	private final ShoppingMaul sm;
	
	public SpawnLeopardPackCommand(ShoppingMaul sm)
	{
		this.sm = sm;
	}

	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if (args.length == 4)
		{
			try
			{
				int packSize = Integer.parseInt(args[0]);
				int x = Integer.parseInt(args[1]);
				int y = Integer.parseInt(args[2]);
				int speed = Integer.parseInt(args[3]);
				
				LeopardPack pack = new LeopardPack(x, y, false, new SnowLeopard(sm, x, y, 10, 28, speed, false));
				
				for (int ii = 0; ii < packSize; ii++)
				{
					pack.addLeopard(new SnowLeopard(sm, x, y, 10, 28, speed, false));
				}

				KatechonEngine.getInstance().addDrawable(pack, 1);
				KatechonEngine.getInstance().scheduleTask(pack);
				EngineManager.getInstance().getEngine(ICollidable.class).addItem(pack);
				
				sm.addPack(pack);
				
				Log.info("Spawned a pack of size: " + packSize);
				
			}
			catch(NumberFormatException e)
			{
				showUsage();
			}
		}
		else if (args.length == 1)
		{
			try
			{
				int packSize = Integer.parseInt(args[0]);
				int x = sm.getMainLeopard().getLeftFace();
				int y = sm.getMainLeopard().getTopFace();
				int speed = sm.getMainLeopard().getSpeed();
				
				LeopardPack pack = new LeopardPack(x, y, false, sm.getMainLeopard());
				
				for (int ii = 0; ii < packSize; ii++)
				{
					pack.addLeopard(new SnowLeopard(sm, x, y, 10, 28, speed, false));
				}

				KatechonEngine.getInstance().addDrawable(pack, 1);
				KatechonEngine.getInstance().scheduleTask(pack);
				EngineManager.getInstance().getEngine(ICollidable.class).addItem(pack);
				
				Log.info("Spawned a pack of size: " + packSize);
				
			}
			catch(NumberFormatException e)
			{
				showUsage();
			}
		}
		else
		{
			showUsage();
		}
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("spawnpack [size] [x] [y] [speed]")
			.info("spawnpack [size]")
		.log();
	}

}
