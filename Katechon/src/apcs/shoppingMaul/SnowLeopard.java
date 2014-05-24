package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Graphics;

import apcs.katechon.KatechonEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.rendering.IDrawable;

public class SnowLeopard implements IDrawable, ISchedulerTask
{
	private final int size;
	
	private Color color;
	
	private int x;
	private int y;
	
	private final int speed;
	
	public SnowLeopard(int size, int speed)
	{
		this.color = Color.DARK_GRAY;
		this.size = size;
		x = KatechonEngine.getInstance().getSwingWindow().getWidth() / 2;
		y = KatechonEngine.getInstance().getSwingWindow().getHeight() / 2;
		
		this.speed = speed;
	}
	
	@Override
	public void doTask()
	{
		if (Keyboard.getInstance().isKeyPressed(Keys.W))
		{
			y -= speed;
		}
		
		if (Keyboard.getInstance().isKeyPressed(Keys.S))
		{
			y += speed;
		}
		
		if (Keyboard.getInstance().isKeyPressed(Keys.A))
		{
			x -= speed;
		}
		
		if (Keyboard.getInstance().isKeyPressed(Keys.D))
		{
			x += speed;
		}
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y,/*Default width*/size, /*Default Height*/size);
		g.setColor(Color.WHITE);
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public void changePosition(int x, int y)
//	{
//		this.x = x;
//		this.y = y;
//	}
	
//	@Override
//	public int getX()
//	{
//		return this.x;
//	}
//	
//	@Override
//	public int getY()
//	{
//		return this.y;
//	}
//
//	@Override
//	public int getWidth()
//	{
//		return size;
//	}
//
//	@Override
//	public int getHeight()
//	{
//		return size;
//	}
}
