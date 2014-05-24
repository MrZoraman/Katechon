package apcs.katechon.input.keyboard;

import apcs.katechon.engine.IEngineItem;

/**
 * An event handler for key presses
 * @author Matt
 *
 */
public interface KeyPressedListener extends IEngineItem
{
	/**
	 * Called when a key is pressed.
	 * @param key They key that was pressed.
	 */
	public void onKeyPressed(Keys key, char keyChar);
}
