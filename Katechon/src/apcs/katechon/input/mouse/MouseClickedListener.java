package apcs.katechon.input.mouse;

import apcs.katechon.engine.IEngineItem;

/**
 * An event handler for mouse clicks (left or right click)
 * @author Matt
 *
 */
public interface MouseClickedListener extends IEngineItem
{
	/**
	 * Called when the mouse is clicked
	 * @param x The x location in the screen
	 * @param y The y location in the screen
	 */
	public abstract void onClick(int x, int y);
}
