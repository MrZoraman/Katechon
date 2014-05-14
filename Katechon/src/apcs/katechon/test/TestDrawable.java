package apcs.katechon.test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.Layer;

public class TestDrawable implements IDrawable
{
	protected int x, y, width, height;
	protected Layer layer;
	protected Color color;
	private Random rand;
	
	public TestDrawable(int x, int y, int width, int height, Color color)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		rand = new Random();
	}
	
	public void draw(Graphics g)
	{
		Log.debug("Drawing");
		g.setColor(this.color);
		g.fill3DRect(rand.nextInt(1000), rand.nextInt(1000), rand.nextInt(100), rand.nextInt(100), true);
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
}
