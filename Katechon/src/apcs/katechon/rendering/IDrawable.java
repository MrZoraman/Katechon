package apcs.katechon.rendering;

import java.awt.Graphics;

import apcs.katechon.engine.IEngineItem;

/**
 * An object that is to be drawn to the screen.
 * @author Sean
 */
public interface IDrawable extends IEngineItem
{
	/**
	 * What the {@link apcs.katechon.rendering.IDrawable IDrawable} should do when the {@link apcs.katechon.rendering.Display Display} is drawn.
	 * @param g The graphics used by the {@link apcs.katechon.rendering.Display Display}
	 */
	public void draw(Graphics g);
}
