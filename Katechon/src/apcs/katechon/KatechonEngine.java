package apcs.katechon;

import javax.swing.Timer;

import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.logging.Log;
import apcs.katechon.periodic.IPeriodic;
import apcs.katechon.periodic.PeriodicTicker;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;

/**
 * This is the class that runs the entire operation. You give it an instance of {@link apcs.katechon.KatechonBase KatechonBase} and it does it's magic
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
	
	private final KatechonBase kBase;
	
	/**
	 * Constructs the engine with all default settings
	 * @param kBaseClass The class of the game that extends {@link apcs.katechon.KatechonBase KatechonBase}
	 */
	public KatechonEngine(final Class<? extends KatechonBase> kBaseClass)
	{
		this(kBaseClass, new MappedConfig());
	}
	
	/**
	 * Create the KatechonEngine. Only one of these can be instantiated.
	 * @param kBaseClass The class of the game that extends {@link apcs.katechon.KatechonBase KatechonBase}
	 * @param config The {@link apcs.katechon.utils.IConfig IConfig} to be used for this game.
	 */	
	public KatechonEngine(final Class<? extends KatechonBase> kBaseClass, final IConfig config)
	{
		KatechonBase kBaseInstance = null;
		
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
		this.kBase = kBaseInstance;

		//Logger
		//-----------------------------------------------------------------------------
		try {
			Log.init(kBase.initLogger());
		} catch (Exception e) {
			System.err.println("Failed to instantiate logger!");
			e.printStackTrace();
			//Because the logger defaults to a prinstream logger, there is nothing we need to do here (hopefully...).
		}
		//-----------------------------------------------------------------------------
		
		
		
		//PeriodicTicker
		//-----------------------------------------------------------------------------
			periodicTicker = new PeriodicTicker();
			
			//TODO: Tune this timer as well
			gameTimer = new Timer(20, periodicTicker);
		//-----------------------------------------------------------------------------
		
		
		
		
		//Window
		//-----------------------------------------------------------------------------
		//Use the config to set properties for the game engine state
		int width = config.getInt			(ConfigKey.WIDTH, 				DEFAULT_WIDTH);
		int height = config.getInt			(ConfigKey.HEIGHT, 				DEFAULT_HEIGHT);
		String title = config.getString		(ConfigKey.TITLE, 				DEFAULT_TITLE);
		int amountOfLayers = config.getInt	(ConfigKey.AMOUNT_OF_LAYERS, 	DEFAULT_AMOUNT_OF_LAYERS);
		
		window = new SwingWindow(width, height, title, amountOfLayers);
		//-----------------------------------------------------------------------------
		
		
		
		//Inputs
		//-----------------------------------------------------------------------------
		window.addKeyListener(Keyboard.getInstance());
		window.addMouseListener(Mouse.getInstance());
		//-----------------------------------------------------------------------------
		
		
		
		//This is a singleton class!
		//-----------------------------------------------------------------------------
		if(instance != null)
			throw new IllegalStateException("Cannot create more than one engine!");
		
		instance = this;
		//-----------------------------------------------------------------------------
	}
	
	private final SwingWindow window;
	
	private final PeriodicTicker periodicTicker;
	
	private final Timer gameTimer;
	
	/**
	 * Starts the game engine (let the magic begin). This is a blocking method.
	 */
	public void start()
	{
		kBase.init();
		
		//Starts the background processes.
		gameTimer.start();
		
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
	 * Adds an {@link apcs.katechon.periodic.IPeriodic IPeriodic} to the engine's list.
	 * @param periodic The {@link apcs.katechon.periodic.IPeriodic IPeriodic} to add.
	 */
	public KatechonEngine addPeriodic(IPeriodic periodic)
	{
		this.periodicTicker.addItem(periodic);
		return getInstance();
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
}
