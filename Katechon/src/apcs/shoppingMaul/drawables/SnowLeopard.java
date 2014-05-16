package apcs.shoppingMaul.drawables;

import java.awt.Color;
import java.awt.Graphics;

import apcs.katechon.KatechonEngine;
import apcs.katechon.rendering.IDrawable;

public class SnowLeopard implements IDrawable
{
	private final int size;
	
	private Color color;
	
	private int x;
	private int y;
	
	public SnowLeopard(int size)
	{
		this.color = Color.DARK_GRAY;
		this.size = size;
		x = KatechonEngine.getInstance().getSwingWindow().getWidth() / 2;
		y = KatechonEngine.getInstance().getSwingWindow().getHeight() / 2;
	}
	
	@Override
	public void onTick()
	{
		
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y,/*Default width*/20 * size, /*Default Height*/20 * size);
		g.setColor(Color.WHITE);
	}

	@Override
	public void changeSize(int width, int height)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void changePosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
}
