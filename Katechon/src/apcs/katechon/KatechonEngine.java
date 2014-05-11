package apcs.katechon;

import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.utils.IConfig;

/**
 * This is the class that runs the entire operation. You give it an instance of {@link apcs.katechon.KatechonBase KatechonBase} and it does it's magic
 * @author Matt
 *
 */
public class KatechonEngine
{
	private static KatechonEngine instance = null;
	
	//Avoid magic numbers!
	private static final int DEFAULT_WIDTH = 1280;
	private static final int DEFAULT_HEIGHT = 780;
	private static final String DEFAULT_TITLE = "Katechon Game";
	
	public static synchronized KatechonEngine getInstance()
	{
		return instance;
	}
	
	private final KatechonBase kBase;
	
	public KatechonEngine(final Class<? extends KatechonBase> kBaseClass, final IConfig config)
	{
		KatechonBase kBaseInstance = null;
		
		try
		{
			//We are going to use reflection! Why? Because it's nicer on the eyes in the main() method (imo).
			kBaseInstance = kBaseClass.newInstance();
		}
		catch (Exception e)
		{
			//Nothing more to do here...
			System.err.println("Failed to instantiate game!");
			System.exit(1);
		}
		
		//We instantiate an instance in the constructor and then set two references to equal each other down here 
		//so java will shut up about errors that are stupid. It could be circumvented by removing the 'final'
		//modifier, but I want kBase to be final.
		this.kBase = kBaseInstance;
		
		//Window
		//-----------------------------------------------------------------------------
		//Use the config to set properties for the game engine state
		int width = config.getInt("width", DEFAULT_WIDTH);
		int height = config.getInt("height", DEFAULT_HEIGHT);
		String title = config.getString("title", DEFAULT_TITLE);
		
		window = new SwingWindow(width, height, title);
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
	
	/**
	 * Starts the game engine (let the magic begin). This is a blocking method.
	 */
	public void start()
	{
		kBase.init();
		
		//do stuff and run the game loop
		window.show();
		
		//Maybe have this?
		kBase.start();
	}
}
