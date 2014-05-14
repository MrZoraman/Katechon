package apcs.katechon.rendering;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Holds {@link apcs.katechon.rendering.IDrawable IDrawables} in a non-ordered fashion. 
 * </p>
 * 
 * <p>
 * This is called in an ordered fashion to simulate having different layers to what is being drawn to the screen.
 * </p>
 * @author Sean
 */
public class Layer
{
	private Set<IDrawable> drawables;
	
	/**
	 * Create a new Layer.
	 */
	Layer()
	{
		drawables = new HashSet<IDrawable>();
	}
	
	/**
	 * Add an {@link apcs.katechon.rendering.IDrawable IDrawable} to this layer to be drawn with the layer.
	 * @param drawable The {@link apcs.katechon.rendering.IDrawable IDrawable} to be added to this layer.
	 */
	public void addDrawable(IDrawable drawable)
	{
		this.drawables.add(drawable);
	}
	
	/**
	 * Remove an {@link apcs.katechon.rendering.IDrawable IDrawable} from this layer.
	 * @param drawable The {@link apcs.katechon.rendering.IDrawable IDrawable} to remove from this layer.
	 */
	public void removeDrawable(IDrawable drawable)
	{
		this.drawables.remove(drawable);
	}
	
	/**
	 * Draws all of the {@link apcs.katechon.rendering.IDrawable IDrawables} in this Layer at once.
	 * @param g The graphics that are used by the containing Display.
	 */
	void drawAll(Graphics g)
	{
		for (IDrawable d : drawables)
		{
			d.draw(g);
		}
	}
	
	/**
	 * Gets all of the {@link apcs.katechon.rendering.IDrawable IDrawables} held by this {@link apcs.katechon.rendering.Layer Layer}
	 * @return All of the {@link apcs.katechon.rendering.IDrawable IDrawable} held by this {@link apcs.katechon.rendering.Layer Layer}
	 */
	public Set<IDrawable> getDrawables()
	{
		return drawables;
	}
}
