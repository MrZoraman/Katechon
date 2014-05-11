package apcs.katechon.input;

/**
 * An event handler for mouse clicks (left or right click)
 * @author Matt
 *
 */
public interface MouseClickedListener 
{
	/**
	 * Called when the mouse is clicked
	 * @param x The x location in the screen
	 * @param y The y location in the screen
	 */
	public abstract void onClick(int x, int y);
}
