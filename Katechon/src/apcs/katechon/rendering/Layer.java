package apcs.katechon.rendering;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class Layer
{
	private Set<IDrawable> drawables;
	
	public Layer()
	{
		drawables = new HashSet<IDrawable>();
	}
	
	public void addDrawable(Drawable drawable)
	{
		this.drawables.add(drawable);
	}
	
	public void removeDrawable(Drawable drawable)
	{
		this.drawables.remove(drawable);
	}
	
	public void drawAll(Graphics g)
	{
		for (IDrawable d : drawables)
		{
			d.draw(g);
		}
	}
	
	public void updateAll()
	{
		for (IDrawable d : drawables)
		{
			d.update();
		}
	}
	
	
	public Set<IDrawable> getDrawables()
	{
		return drawables;
	}
}
