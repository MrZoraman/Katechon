package apcs.shoppingMaul;

import java.awt.Color;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.CommandManager;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.SimpleCollisionEngine;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.shoppingMaul.commands.SpawnLeopardCommand;
import apcs.shoppingMaul.commands.SpawnLeopardPackCommand;

public class ShoppingMaul extends KatechonGameBase
{
	public static IConfig config;
	
	private static KatechonEngine mainEngine;
	
	private static SnowLeopard snowLeopard;
	
	public static void main(String[] args)
	{
		config = new MappedConfig();
		config.setString(ConfigKey.TITLE, "abba jeezles");
		
		//see this line of code looks pretty clean to me
		mainEngine = new KatechonEngine(ShoppingMaul.class, config);
		
		mainEngine.start();
	}

	@Override
	public void init()
	{
		mainEngine = KatechonEngine.getInstance();
		
		mainEngine.getSwingWindow().setBackgroundColor(Color.WHITE);
		
		snowLeopard = new SnowLeopard(100, 100, 10, 28, 2, true);
		
		mainEngine.addDrawable(snowLeopard, 1);
		mainEngine.scheduleTask(snowLeopard);
		EngineManager.getInstance().addEngine(new SimpleCollisionEngine());
		
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(snowLeopard);
	
		
//		mainEngine.addDrawable(snowLeopard, 0);
//		mainEngine.scheduleTask(snowLeopard);
		
		CommandManager.getInstance().registerCommand("spawnleopard", new SpawnLeopardCommand());
		CommandManager.getInstance().registerCommand("spawnpack", new SpawnLeopardPackCommand());
	}

	@Override
	public void onGameEnd()
	{
		
	}
	
	public static SnowLeopard getMainLeopard()
	{
		return snowLeopard; 
	}
}
