package apcs.katechon;

import apcs.katechon.utils.IConfig;

/**
 * This is the class that runs the entire operation. You give it an instance of {@link apcs.katechon.KatechonBase KatechonBase} and it does it's magic
 * @author Matt
 *
 */
public class KatechonEngine
{
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
		
		//We instantiate an instance the constructor and then set two references to equal each other down here 
		//so java will shut up about errors that are stupid. It could be circumented by removing the 'final'
		//modifier, but I want kBase to be final.
		this.kBase = kBaseInstance;
		
		//Use the config to set properties for the game engine state
	}
	
	private final KatechonBase kBase;
	
	/**
	 * Starts the game engine (let the magic begin). This is a blocking method.
	 */
	public void start()
	{
		//do stuff and run the game loop
	}
}
