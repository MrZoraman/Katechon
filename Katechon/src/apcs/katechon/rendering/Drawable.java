package apcs.katechon.rendering;

import java.awt.Graphics;

public class Drawable implements IDrawable
{
	protected int x, y, width, height;
	protected Layer layer;
	
	public Drawable(int x, int y, int width, int height, int layer)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g)
	{
		g.draw3DRect(10, 10, 10, 10, true);
	}

	@Override
	public void changeSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	public void changePosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void update()
	{
		
	}
}
