package apcs.katechon.test;

import java.awt.Color;
import java.awt.Graphics;

import apcs.katechon.logging.Log;
import apcs.katechon.periodic.IPeriodic;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.Layer;

//TODO: beware all ye who enter
public class TestDrawable implements IDrawable, IPeriodic
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
		Log.debug("Color: " + this.color);
		g.setColor(this.color);
		g.fill3DRect(x, y, width, height, true);
		g.setColor(Color.BLACK);
	}

//	@Override
//	public void changeSize(int width, int height)
//	{
//		this.width = width;
//		this.height = height;
//	}
//
//	@Override
//	public void changePosition(int x, int y)
//	{
//		this.x = x;
//		this.y = y;
//	}

	@Override
	public void onTick()
	{
//		this.changePosition(this.x + 1, this.y + 1);
	}

//	@Override
//	public int getX()
//	{
//		return 0;
//	}
//
//	@Override
//	public int getY()
//	{
//		return 0;
//	}
//
//	@Override
//	public int getWidth()
//	{
//		return 0;
//	}
//
//	@Override
//	public int getHeight()
//	{
//		return 0;
//	}
}
