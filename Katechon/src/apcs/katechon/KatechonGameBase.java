package apcs.katechon;

/**
 * This is the base class for a game that will be based off of the Katechon engine. When plugging a class into the katechon engine constructor, it must extend this class.
 * @author Matt
 *
 */
public abstract class KatechonGameBase
{
	/**
	 * Constructor. The default empty constructor is the one that is called by the Katechon engine when a game instance is constructed.
	 */
	public KatechonGameBase()
	{
		
	}
	
	/**
	 * Initialization code goes here! This method is called by the engine once everything has been constructed and the engine itself has been started.
	 */
	public abstract void init();
	
	/**
	 * Runs when the game has been closed through the window.
	 */
	public abstract void onGameEnd();
}
