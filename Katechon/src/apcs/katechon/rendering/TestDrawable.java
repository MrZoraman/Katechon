package apcs.katechon.rendering;

import java.awt.Color;
import java.awt.Graphics;

import apcs.katechon.logging.Log;

public class TestDrawable implements IDrawable
{
	protected int x, y, width, height;
	protected Layer layer;
	protected Color color;
	
	public TestDrawable(int x, int y, int width, int height, Color color)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void draw(Graphics g)
	{
		Log.debug("Drawing");
		g.setColor(this.color);
		g.drawRect(10, 10, 10, 10);
		g.draw3DRect(10, 10, 10, 10, true);
		g.setColor(Color.BLACK);
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
