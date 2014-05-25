package apcs.shoppingMaul;

import java.awt.Color;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;

public class ShoppingMaul extends KatechonGameBase
{
	public static IConfig config;
	
	private static KatechonEngine mainEngine;
	
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
		
		SnowLeopard snowLeopard = new SnowLeopard(100, 100, 10, 28, 2, true);
		
		mainEngine.addDrawable(snowLeopard, 1);
		mainEngine.scheduleTask(snowLeopard);
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(snowLeopard);
	
		
//		mainEngine.addDrawable(snowLeopard, 0);
//		mainEngine.scheduleTask(snowLeopard);
	}

	@Override
	public void onGameEnd()
	{
		
	}

}
