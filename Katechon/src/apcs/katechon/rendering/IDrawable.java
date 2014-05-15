package apcs.katechon.rendering;

import java.awt.Graphics;

import apcs.katechon.periodic.IPeriodic;

/**
 * An object that is to be drawn to the screen.
 * @author Sean
 */
public interface IDrawable extends IPeriodic
{
	/**
	 * What the {@link apcs.katechon.rendering.IDrawable IDrawable} should do when the {@link apcs.katechon.rendering.Display Display} is drawn.
	 * @param g The graphics used by the {@link apcs.katechon.rendering.Display Display}
	 */
	public void draw(Graphics g);
	
	/**
	 * Change the size of the {@link apcs.katechon.rendering.IDrawable IDrawable}
	 * @param width The new width of the {@link apcs.katechon.rendering.IDrawable IDrawable}
	 * @param height The new height of the {@link apcs.katechon.rendering.IDrawable IDrawable}
	 */
	public void changeSize(int width, int height);
	
	/**
	 * Change the position of the {@link apcs.katechon.rendering.IDrawable IDrawable}
	 * @param x The new x position of the {@link apcs.katechon.rendering.IDrawable IDrawable}
	 * @param y The new y position of the {@link apcs.katechon.rendering.IDrawable IDrawable}
	 */
	public void changePosition(int x, int y);
}
