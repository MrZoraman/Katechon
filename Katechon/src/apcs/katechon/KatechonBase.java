package apcs.katechon;

/**
 * The game will extend this class. It will then be plugged into some sort of game engine singleton that will then do what needs to be done to do what we want to be done.
 * @author Matt
 *
 */
//TODO: maybe a little better documentation here ^
public abstract class KatechonBase
{
	public KatechonBase()
	{
		
	}
	
	/**
	 * This method will run x amount of times per second, as specified somewhere...
	 */
	//TODO: better documentation
	public abstract void onGameTick();
	
	//void initGraphics(GraphicsREnderer gr)
	//{...}
}
