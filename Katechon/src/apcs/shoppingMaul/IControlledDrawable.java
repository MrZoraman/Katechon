package apcs.shoppingMaul;

import java.awt.Graphics;

/**
 * Similar to the IDrawable interface, but the implementation is told where to draw it's stuff.
 * @author Matt
 *
 */
public interface IControlledDrawable
{
	/**
	 * Draw stuff to the graphics
	 * @param g The graphics object
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void draw(Graphics g, int x, int y);
}
