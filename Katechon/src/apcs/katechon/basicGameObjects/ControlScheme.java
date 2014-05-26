package apcs.katechon.basicGameObjects;

import apcs.katechon.input.keyboard.Keys;

import static apcs.katechon.input.keyboard.Keys.*;

/**
 * Represents an up/down/left/right control button mapping
 * @author Matt
 *
 */
public class ControlScheme
{
	/**
	 * Simple WSAD movement that we all know and love
	 */
	public static final ControlScheme WSAD = new ControlScheme(W, S, D, A);
	
	/**
	 * Mapping with the arrow keys. May be useful for a player 2...
	 */
	public static final ControlScheme ARROWS = new ControlScheme(UP_ARROW, DOWN_ARROW, RIGHT_ARROW, LEFT_ARROW);
	
	public ControlScheme(Keys upKey, Keys downKey, Keys rightKey, Keys leftKey)
	{
		this.upKey = upKey;
		this.downKey = downKey;
		this.rightKey = rightKey;
		this.leftKey = leftKey;
	}
	
	private final Keys upKey, downKey, leftKey, rightKey;

	/**
	 * @return The key for going up
	 */
	public Keys getUpKey()
	{
		return upKey;
	}

	/**
	 * @return The key for going down
	 */
	public Keys getDownKey()
	{
		return downKey;
	}

	/**
	 * @return The key for going left
	 */
	public Keys getLeftKey()
	{
		return leftKey;
	}

	/**
	 * @return The key for going right
	 */
	public Keys getRightKey()
	{
		return rightKey;
	}
}
