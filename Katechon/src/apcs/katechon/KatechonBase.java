package apcs.katechon;

import apcs.katechon.logging.ILogger;
import apcs.katechon.logging.PrintstreamLogger;

/**
 * The game will extend this class. It will then be plugged into some sort of game engine singleton that will then do what needs to be done to do what we want to be done.
 * @author Matt
 *
 */
//TODO: maybe a little better documentation here ^
public abstract class KatechonBase
{
//	private InputHandler handler;
	
	public KatechonBase()
	{
		
	}
	
	/**
	 * This method will run x amount of times per second, as specified in the config.
	 */
	//TODO: better documentation
	public abstract void onGameTick();
	
	/**
	 * Initialization code goes here! This method is called by the engine once everything has been constructed and the engine itself has been started.
	 */
	public abstract void init();
	
	/**
	 * Runs when the game has been closed through the window.
	 */
	public abstract void onGameEnd();
	
	/**
	 * Initializes the logger
	 * @return The logger to use.
	 */
	public ILogger initLogger()
	{
		//Default logger
		return new PrintstreamLogger(System.out);
	}
}
