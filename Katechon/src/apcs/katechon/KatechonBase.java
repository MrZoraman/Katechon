package apcs.katechon;

import apcs.katechon.input.InputHandler;

/**
 * The game will extend this class. It will then be plugged into some sort of game engine singleton that will then do what needs to be done to do what we want to be done.
 * @author Matt
 *
 */
//TODO: maybe a little better documentation here ^
public abstract class KatechonBase
{
	private InputHandler handler;
	
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
	
	void initInput()
	{
		//Maybe use reflection to instantiate and not have a singleton? Would "ensure" no outside instantiation.
		this.handler = InputHandler.getInstance();
	}
}