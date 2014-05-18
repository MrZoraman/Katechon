package apcs.katechon.test;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import apcs.katechon.KatechonBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.input.mouse.MouseClickedListener;
import apcs.katechon.logging.FileLogger;
import apcs.katechon.logging.ILogger;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;

/**
 * This is a test class for general Katechon testing
 * @author Matt
 *
 */
public class Test extends KatechonBase
{
	/**
	 * Entry point
	 * @param args command line arguments (unused [probably])
	 */
	public static void main(String[] args)
	{
		IConfig config = new MappedConfig();
		config.setString(ConfigKey.TITLE, "abba jeezles");
		
		//see this line of code looks pretty clean to me
		new KatechonEngine(Test.class, config).start();
	}
	
	@Override
	public ILogger initLogger() throws Exception
	{
		Log.setDebugging(true);
		return new FileLogger("Testing" + File.separator + "Testing.log");
	}

	@Override
	public void init() 
	{
		//Tested the FileLogger, seems to function properly.
//		Log.init(new FileLogger("Testing" + File.separator + "Testing.log"));
		Log.setDebugging(true);
		
		//Change the background color.
		KatechonEngine.getInstance().getSwingWindow().setBackgroundColor(Color.BLACK);
		
		Keyboard.getInstance().addListener(Keys.A, new KeyPressedListener() {
			@Override
			public void onKeyPressed(Keys key)
			{
				Log.debug("Key pressed!: " + key);
			}
		});
		
		Keyboard.getInstance().addListener(Keys.ESCAPE, new KeyPressedListener() {

			@Override
			public void onKeyPressed(Keys key)
			{
				Log.info("Exiting.");
				KatechonEngine.getInstance().end();				
			}
			
		
		});
		
		Mouse.getInstance().addListener(new MouseClickedListener() {
			@Override
			public void onClick(int x, int y) {
//				System.out.println("Mouse clicked!: " + x + ", " + y);
				Log.debug("Mouse clicked!: " + x + ", " + y);
			}
		});
		
		for(int ii = 0; ii < 5; ii++)
		{
			for (int jj = 0; jj < 5; jj++)
			{
				IDrawable drawable;
				
				Random r = new Random();
				
				//Really crude color changing thing.
				Color color = Color.GRAY;
				switch (ii){
					case 0:
						color = Color.GREEN;
						break;
					case 1:
						color = Color.RED;
						break;
					case 2:
						color = Color.CYAN;
						break;
					case 3:
						color = Color.YELLOW;
						break;
					case 4:
						color = Color.MAGENTA;
						break;
				}
				
				drawable = new TestDrawable(r.nextInt(1000), r.nextInt(1000), r.nextInt(100), r.nextInt(100), color);
				//drawable = new TestDrawable(0, 0, r.nextInt(100), r.nextInt(100), color);
				KatechonEngine.getInstance().getSwingWindow().getDisplay().getLayer(ii).addDrawable(drawable);
				KatechonEngine.getInstance().addPeriodic(drawable);
			}
		}
		
		KatechonEngine.getInstance().addPeriodic(new TestPeriodic());

		
//		KatechonEngine.getInstance().getSwingWindow().getDisplay().getLayer(0).addDrawable(new IDrawable() {
//			@Override
//			public void draw(Graphics g) {
//				
//			}
//
//			@Override
//			public void changeSize(int width, int height) {
//			}
//
//			@Override
//			public void changePosition(int x, int y) {
//			}
//		});
	}

	@Override
	public void onGameEnd()
	{
		Log.debug("Exited through game logic.");
	}
}