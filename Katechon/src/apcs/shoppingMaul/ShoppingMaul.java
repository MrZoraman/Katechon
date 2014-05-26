package apcs.shoppingMaul;

import java.awt.Color;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.commands.CommandManager;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.SimpleCollisionEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.Log;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.shoppingMaul.commands.RemoveLeopardCommand;
import apcs.shoppingMaul.commands.SpawnLeopardCommand;

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
		
		LeopardPack pack = new LeopardPack(ControlScheme.WSAD, 500, 500, 10, 1);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(pack);
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(pack);
		
		CommandManager.getInstance().registerCommand("add * {leopard|leopards}", new SpawnLeopardCommand(pack));
		CommandManager.getInstance().registerCommand("removeleopard", new RemoveLeopardCommand(pack));
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
