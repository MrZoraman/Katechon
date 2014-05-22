package apcs.katechon.test;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import apcs.katechon.KatechonBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.SimpleCollisionEngine;
import apcs.katechon.engine.scheduler.SchedulerTask;
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

/**
 * This is a test class for general Katechon testing
 * @author Matt
 *
 */
@SuppressWarnings("unused")
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
//		return new FileLogger("Testing" + File.separator + "Testing.log", true);
//		WindowLogger windowLogger = new WindowLogger(15);
		final ToggleableWindowLogger twl = new ToggleableWindowLogger(15);
		
		KatechonEngine.getInstance().scheduleTask(new SchedulerTask() {
			@Override
			public boolean doTask() {
				KatechonEngine.getInstance().addDrawable(twl, 0);
				twl.initHideKey(Keys.TILDE);
				return true;
			}
		});
		
		return twl;
	}

	@Override
	public void init() 
	{
		Log.info("hello there!");
		Log.info("abba jeezles");
		
		
		Mouse.getInstance().addListener(new MouseClickedListener() {
			@Override
			public void onClick(int x, int y) {
				Log.info("Mouse clicked!: " + x + ", " + y);
			}
		});
		
		KatechonEngine.getInstance().scheduleTask(new SchedulerTask() {
			@Override
			public boolean doTask()
			{
				Log.info("task done!");
				return true;
			}
		});
		
//		KatechonEngine.getInstance().getSwingWindow().setBackgroundColor(Color.WHITE);
//		
//		SimpleCollisionEngine sce = new SimpleCollisionEngine();
//		EngineManager.getInstance().addEngine(sce);
//		
//		Random r = new Random();
//		
//		SimpleCollidable sc1 = new SimpleCollidable(r.nextInt(1000), r.nextInt(1000), 50, 50, 10, true);
//		SimpleCollidable sc2 = new SimpleCollidable(r.nextInt(1000), r.nextInt(1000), 50, 50, 10, false);
//		
//		KatechonEngine.getInstance().addDrawable(sc1, 0);
//		KatechonEngine.getInstance().addDrawable(sc2, 0);
//
//		KatechonEngine.getInstance().addPeriodic(sc1);
//		KatechonEngine.getInstance().addPeriodic(sc2);
//		
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(sc1);
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(sc2);
	}

	@Override
	public void onGameEnd()
	{
		Log.debug("Exited through game logic.");
	}
}
