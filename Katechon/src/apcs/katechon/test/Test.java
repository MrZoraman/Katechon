package apcs.katechon.test;

import apcs.katechon.KatechonBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.input.mouse.MouseClickedListener;
import apcs.katechon.logging.FileLogger;
import apcs.katechon.logging.Log;
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
		config.setString("title", "abba jeezles");
		
		//see this line of code looks pretty clean to me
		new KatechonEngine(Test.class, config).start();
	}

	@Override
	public void onGameTick() {
		
	}

	@Override
	public void init() 
	{
		//Tested the FileLogger, seems to function properly.
		Log.init(new FileLogger("Testing\\Testing.log"));
		Log.setDebugging(true);
		
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
	}

	@Override
	public void start()
	{
		//Would begin actual game logic here.
		
	}

	@Override
	public void onGameEnd()
	{
		Log.debug("Exited through game logic.");
	}
}