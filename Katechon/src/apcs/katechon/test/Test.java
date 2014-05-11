package apcs.katechon.test;

import apcs.katechon.KatechonBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.input.mouse.MouseClickedListener;
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
		// TODO Auto-generated method stub
	}

	@Override
	public void init() 
	{
		Keyboard.getInstance().addListener(Keys.A, new KeyPressedListener() {
			@Override
			public void onKeyPressed(Keys key)
			{
				System.out.println("Key pressed!: " + key);
			}
		});
		
		Keyboard.getInstance().addListener(Keys.ESCAPE, new KeyPressedListener() {

			@Override
			public void onKeyPressed(Keys key)
			{
				System.out.println("Exiting.");
				
			}
			
		
		});
		
		Mouse.getInstance().addListener(new MouseClickedListener() {
			@Override
			public void onClick(int x, int y) {
				System.out.println("Mouse clicked!: " + x + ", " + y);
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
		
	}
}