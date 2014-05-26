package apcs.shoppingMaul;

import java.awt.Color;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.SimpleCollisionEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.Log;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;

public class ShoppingMaul extends KatechonGameBase
{
	private SnowLeopard snowLeopard;
//	private LeopardPack mainPack;
	
	public static void main(String[] args)
	{
		IConfig config = new MappedConfig();
		config.setString(ConfigKey.TITLE, "Shopping Maul");
		
		new KatechonEngine(ShoppingMaul.class, config).start();
	}

	@Override
	public void init(final KatechonEngine engine)
	{
		Log.setDebugging(true);
		EngineManager.getInstance().addEngine(new SimpleCollisionEngine());
		
		engine.getSwingWindow().setBackgroundColor(Color.WHITE);
		
		SnowLeopard leopard = new SnowLeopard(50, 50, 10);
		engine.addDrawable(leopard, 1);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(leopard);
		
//		leopard.setDestination(700, 700);
		
		LeopardPack pack = new LeopardPack(ControlScheme.WSAD, 500, 500, 10, 5);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(pack);
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(pack);
		
		
		
//		snowLeopard = new SnowLeopard(100, 100, 10, 28, 8, true);
		
//		mainPack = new LeopardPack(100, 100, true, snowLeopard);
//		
//		engine.addDrawable(mainPack, 1);
//		engine.scheduleTask(mainPack);
//		
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(mainPack);
//		
//		CommandManager.getInstance().registerCommand("spawnleopard", new SpawnLeopardCommand());
//		
//		CommandManager.getInstance().registerCommand("addpillar", new AddPillarCommand());
	}

	@Override
	public void onGameEnd()
	{
		
	}
	
	public SnowLeopard getMainLeopard()
	{
		return snowLeopard; 
	}
}
