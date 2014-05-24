package apcs.katechon;

import java.io.File;

import javax.swing.Timer;

import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.scheduler.SchedulerEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.logging.FileLogger;
import apcs.katechon.logging.Log;
import apcs.katechon.logging.ToggleableWindowLogger;
import apcs.katechon.rendering.Display;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.katechon.windowingtoolkit.KWT;

/**
 * This is the class that runs the entire operation. You give it an instance of {@link apcs.katechon.KatechonGameBase KatechonBase} and it does it's magic
 * @author Matt
 */
public class KatechonEngine
{
	private static KatechonEngine instance = null;
	
	//Avoid magic numbers!
	private static final int DEFAULT_WIDTH = 1280;
	private static final int DEFAULT_HEIGHT = 780;
	private static final String DEFAULT_TITLE = "Katechon Game";
	private static final int DEFAULT_AMOUNT_OF_LAYERS = 5;
	
	/**
	 * Get the instance of the {@link apcs.katechon.KatechonEngine KatechonEngine}
	 * @return An instance of {@link apcs.katechon.KatechonEngine KatechonEngine}
	 */
	public static synchronized KatechonEngine getInstance()
	{
		return instance;
	}
	
	private final KatechonGameBase kBase;
	
	/**
	 * Constructs the engine with all default settings
	 * @param kBaseClass The class of the game that extends {@link apcs.katechon.KatechonGameBase KatechonBase}
	 */
	public KatechonEngine(final Class<? extends KatechonGameBase> kBaseClass)
	{
		this(kBaseClass, new MappedConfig());
	}
	
	/**
	 * Create the KatechonEngine. Only one of these can be instantiated.
	 * @param kBaseClass The class of the game that extends {@link apcs.katechon.KatechonGameBase KatechonBase}
	 * @param config The {@link apcs.katechon.utils.IConfig IConfig} to be used for this game.
	 */	
	public KatechonEngine(final Class<? extends KatechonGameBase> kBaseClass, final IConfig config)
	{
		//In order from most important to least important.
		initSingletonInstance();

		this.periodicTimer = new Timer(20, EngineManager.getInstance());

		this.scheduler = initScheduler();
		
		this.kBase = initGameInstance(kBaseClass);
		
		this.window = initWindow(config);
		
		initInputs(window);
		
		initLogger("Logs" + File.separator + "Log.log");
		
		initKWT(scheduler, window.getDisplay());
		
	}
	

	private final SwingWindow window;
	
	private final Timer periodicTimer;
	
	//Permanent engines
	private final SchedulerEngine scheduler;
	
	private KatechonGameBase initGameInstance(Class<? extends KatechonGameBase> kBaseClass)
	{
		KatechonGameBase kBaseInstance = null;
		
		try
		{
			//We are going to use reflection! Why? Because it's nicer on the eyes in the main() method (imo).
			kBaseInstance = kBaseClass.newInstance();
			Log.info("Constructed base game class " + kBaseClass.getName());
		}
		catch (Exception e)
		{
			//Nothing more to do here...
			Log.fatal("Failed to instantiate game!");
			System.exit(1);
		}
		
		//We instantiate an instance in the constructor and then set two references to equal each other down here 
		//so java will shut up about errors that are stupid. It could be circumvented by removing the 'final'
		//modifier, but I want kBase to be final.
		return kBaseInstance;
	}
	
	private void initLogger(String filePath)
	{
		try {
			Log.initLogger(new FileLogger(filePath));
			
			//this bad boy is now built right in
			ToggleableWindowLogger twl = new ToggleableWindowLogger(15, 5, 5);
			twl.initHideKey(Keys.TILDE);
			addDrawable(twl, 0);
			
		} catch (Exception e) {
			System.err.println("Failed to instantiate logger!");
			e.printStackTrace();
			//Because the logger defaults to a prinstream logger, there is nothing we need to do here (hopefully...).
		}
	}
	
	private void initKWT(SchedulerEngine scheduler, Display display)
	{
		KWT kwt = KWT.getInstance();
		scheduler.addItem(kwt);
		display.initKWTLayer(kwt);
	}
	
	private SchedulerEngine initScheduler()
	{
		SchedulerEngine scheduler = new SchedulerEngine();
		EngineManager.getInstance().addEngine(scheduler);
		return scheduler;
	}
	
	private SwingWindow initWindow(IConfig config)
	{
		int width = config.getInt			(ConfigKey.WIDTH, 				DEFAULT_WIDTH);
		int height = config.getInt			(ConfigKey.HEIGHT, 				DEFAULT_HEIGHT);
		String title = config.getString		(ConfigKey.TITLE, 				DEFAULT_TITLE);
		int amountOfLayers = config.getInt	(ConfigKey.AMOUNT_OF_LAYERS, 	DEFAULT_AMOUNT_OF_LAYERS);
		
		return new SwingWindow(width, height, title, amountOfLayers);
	}
	
	private void initInputs(SwingWindow window)
	{
		window.addKeyListener(Keyboard.getInstance());
		window.addMouseListener(Mouse.getInstance());
		window.addMouseMotionListener(Mouse.getInstance());
	}
	
	private void initSingletonInstance()
	{
		if(instance != null)
			throw new IllegalStateException("Cannot create more than one engine!");
		
		instance = this;
	}
	
	/**
	 * Starts the game engine (let the magic begin). This is a blocking method.
	 */
	public void start()
	{
		kBase.init();
		
		//Starts the background processes.
		periodicTimer.start();
		
		//do stuff and run the game loop
		window.show();
	}
	
	/**
	 * Ends the game and closes the window after onGameEnd is called.
	 */
	public void end()
	{
		kBase.onGameEnd();
		window.end();
		Log.onEnd();
		System.exit(0);
	}
	
	/**
	 * Gets the current swing (os level) window
	 * @return The Swing Window (final object!)
	 */
	public SwingWindow getSwingWindow()
	{
		return window;
	}
	
	/**
	 * Adds a drawable to the engine's drawable list
	 * @param drawable The drawable to draw
	 * @param layer The layer to put the drawable on
	 */
	public KatechonEngine addDrawable(IDrawable drawable, int layer)
	{
		this.window.getDisplay().getLayer(layer).addDrawable(drawable);
		return getInstance();
	}
	
	public void scheduleTask(ISchedulerTask task)
	{
		scheduler.addItem(task);
	}
}
