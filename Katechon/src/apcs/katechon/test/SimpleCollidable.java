package apcs.katechon.test;

import java.awt.Color;
import java.awt.Graphics;

import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.logging.Log;
import apcs.katechon.periodic.IPeriodic;
import apcs.katechon.rendering.IDrawable;

public class SimpleCollidable implements ICollidable, IDrawable, IPeriodic
{
	private int x, y, width, height, speed;
	private boolean control;
	
	public SimpleCollidable(int x, int y, int width, int height, int speed, boolean control)
	{
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.control = control;
	}

	@Override
	public int getTopFace()
	{
		return this.y;
	}

	@Override
	public int getBottomFace()
	{
		return this.y + this.height;
	}

	@Override
	public int getLeftFace()
	{
		return this.x;
	}

	@Override
	public int getRightFace()
	{
		return this.x + this.width;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
	}

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
//		return this.width;
//	}
//
//	@Override
//	public int getHeight()
//	{
//		return this.height;
//	}

	@Override
	public void onTick()
	{
		if (this.control)
		{
			if (Keyboard.getInstance().isKeyPressed(Keys.W))
			{
				y -= speed;
				Log.debug("W key pressed");
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.S))
			{
				y += speed;
				Log.debug("S key pressed");
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.A))
			{
				x -= speed;
				Log.debug("A key pressed");
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.D))
			{
				x += speed;
				Log.debug("D key pressed");
			}
		}
	}
}
