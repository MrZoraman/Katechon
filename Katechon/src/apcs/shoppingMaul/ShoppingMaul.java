package apcs.shoppingMaul;

import java.awt.Color;
import java.io.File;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.logging.FileLogger;
import apcs.katechon.logging.ILogger;
import apcs.katechon.logging.Log;
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
	public ILogger initLogger() throws Exception
	{
		Log.setDebugging(true);
		return new FileLogger("Logs" + File.separator + "Log.log", true);
	}

	@Override
	public void init()
	{
		mainEngine = KatechonEngine.getInstance();
		
		mainEngine.getSwingWindow().setBackgroundColor(Color.WHITE);
		
		SnowLeopard snowLeopard = new SnowLeopard(40, 3);
		
		mainEngine
			.addDrawable(snowLeopard, 0)
			.addPeriodic(snowLeopard);
	}

	@Override
	public void onGameEnd()
	{
		
	}

}
