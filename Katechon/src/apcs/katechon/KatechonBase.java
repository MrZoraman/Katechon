package apcs.katechon;

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
	 * This method will run x amount of times per second, as specified somewhere...
	 */
	//TODO: better documentation
	public abstract void onGameTick();
	
	/**
	 * Initialization code goes here! This method is called by the engine once everything has been constructed and the engine itself has been started.
	 */
	public abstract void init();
	
	
	//Just a thought
	/**
	 * Starts the game
	 */
	public abstract void start();
	
	//I'll talk to you about this on Monday
//	/**
//	 * This will initialize the input handling for the game. This should be the only place this is done.
//	 */
//	void initInput()
//	{
//		//Maybe use reflection to instantiate and not have a singleton? Would "ensure" no outside instantiation.
//		this.handler = InputHandler.getInstance();
//	}
}
