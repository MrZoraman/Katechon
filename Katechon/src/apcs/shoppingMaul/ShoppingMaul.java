package apcs.shoppingMaul;

import java.awt.Color;
import java.io.File;

import apcs.katechon.KatechonBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.logging.FileLogger;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.shoppingMaul.drawables.SnowLeopard;

public class ShoppingMaul extends KatechonBase
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
	public void onGameTick()
	{
		
	}

	@Override
	public void init()
	{
		Log.init(new FileLogger("Logs" + File.separator + "Log.log"));
		Log.setDebugging(true);
		
		mainEngine = KatechonEngine.getInstance();
		
		mainEngine.getSwingWindow().setBackgroundColor(Color.WHITE);
		
		Keyboard.getInstance().addListener(Keys.UP_ARROW, new KeyPressedListener(){

			@Override
			public void onKeyPressed(Keys key)
			{
				for (int ii = 0; ii < config.getInt(ConfigKey.AMOUNT_OF_LAYERS, 5); ii++)
				{
					for (IDrawable sl : mainEngine.getSwingWindow().getDisplay().getLayer(ii).getDrawables())
					{
						if (sl instanceof SnowLeopard)
						{
							sl.changePosition(((SnowLeopard) sl).getX(), ((SnowLeopard) sl).getY() - 3);
						}
					}
				}
			}
		});
		
		Keyboard.getInstance().addListener(Keys.DOWN_ARROW, new KeyPressedListener(){

			@Override
			public void onKeyPressed(Keys key)
			{
				for (int ii = 0; ii < config.getInt(ConfigKey.AMOUNT_OF_LAYERS, 5); ii++)
				{
					for (IDrawable sl : mainEngine.getSwingWindow().getDisplay().getLayer(ii).getDrawables())
					{
						if (sl instanceof SnowLeopard)
						{
							sl.changePosition(((SnowLeopard) sl).getX(), ((SnowLeopard) sl).getY() + 3);
						}
					}
				}
			}
		});
		
		Keyboard.getInstance().addListener(Keys.LEFT_ARROW, new KeyPressedListener(){

			@Override
			public void onKeyPressed(Keys key)
			{
				for (int ii = 0; ii < config.getInt(ConfigKey.AMOUNT_OF_LAYERS, 5); ii++)
				{
					for (IDrawable sl : mainEngine.getSwingWindow().getDisplay().getLayer(ii).getDrawables())
					{
						if (sl instanceof SnowLeopard)
						{
							sl.changePosition(((SnowLeopard) sl).getX() - 3, ((SnowLeopard) sl).getY());
						}
					}
				}
			}
		});
		
		Keyboard.getInstance().addListener(Keys.RIGHT_ARROW, new KeyPressedListener(){

			@Override
			public void onKeyPressed(Keys key)
			{
				for (int ii = 0; ii < config.getInt(ConfigKey.AMOUNT_OF_LAYERS, 5); ii++)
				{
					for (IDrawable sl : mainEngine.getSwingWindow().getDisplay().getLayer(ii).getDrawables())
					{
						if (sl instanceof SnowLeopard)
						{
							sl.changePosition(((SnowLeopard) sl).getX() + 3, ((SnowLeopard) sl).getY());
						}
					}
				}
			}
		});
		
		mainEngine.getSwingWindow().getDisplay().getLayer(0).addDrawable(new SnowLeopard(4));
	}

	@Override
	public void onGameEnd()
	{
		
	}

}
