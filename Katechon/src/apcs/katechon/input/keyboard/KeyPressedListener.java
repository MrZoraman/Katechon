package apcs.katechon.input.keyboard;

/**
 * An event handler for key presses
 * @author Matt
 *
 */
public interface KeyPressedListener
{
	/**
	 * Called when a key is pressed.
	 * @param key They key that was pressed.
	 */
	public void onKeyPressed(Keys key);
}
