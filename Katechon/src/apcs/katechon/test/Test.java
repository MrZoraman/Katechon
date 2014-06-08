package apcs.katechon.test;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.CommandManager;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.SimpleCollisionEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.input.mouse.MouseClickedListener;
import apcs.katechon.logging.FileLogger;
import apcs.katechon.logging.ILogger;
import apcs.katechon.logging.Log;
import apcs.katechon.logging.ToggleableWindowLogger;
import apcs.katechon.logging.WindowLogger;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.katechon.windowingtoolkit.KWT;
import apcs.katechon.windowingtoolkit.Window;

/**
 * This is a test class for general Katechon testing
 * @author Matt
 *
 */
@SuppressWarnings("unused")
public class Test extends KatechonGameBase
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
	public void init(final KatechonEngine engine) 
	{
		engine.getSwingWindow().setBackgroundColor(Color.WHITE);
		
		SimpleCollisionEngine sce = new SimpleCollisionEngine();
		EngineManager.getInstance().addEngine(sce);
		
		Random r = new Random();
		
		SimpleCollidable sc1 = new SimpleCollidable(r.nextInt(500), r.nextInt(500), 50, 50, 10, true);
		SimpleCollidable sc2 = new SimpleCollidable(r.nextInt(500), r.nextInt(500), 50, 50, 10, false);
		SimpleCollidable sc3 = new SimpleCollidable(r.nextInt(500), r.nextInt(500), 50, 50, 10, false);
		
		engine.addDrawable(sc1, 0);
		engine.addDrawable(sc2, 0);
		engine.addDrawable(sc3, 0);

		engine.scheduleTask(sc1);
		engine.scheduleTask(sc2);
		engine.scheduleTask(sc3);
		
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(sc1);
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(sc2);
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(sc3);
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(new Border());
		
		Mouse.getInstance().addListener(new MouseClickedListener() {
			@Override
			public void onClick(int x, int y)
			{
				Log.info("Mouse clicked!: " + x + ", " + y);
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public void setFinished(boolean finished)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		Window window = new Window(40, 40, 500, 500);
		KWT.getInstance().addWindow(window);
		window.setVisible(true);
		
		CommandManager.getInstance().registerCommand("test", new TestCommand());
	}

	@Override
	public void onGameEnd()
	{
		Log.debug("Exited through game logic.");
	}
}
