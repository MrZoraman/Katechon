package apcs.katechon.test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.CollisionType;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.periodic.IPeriodic;
import apcs.katechon.rendering.IDrawable;

public class SimpleCollidable implements ICollidable, IDrawable, IPeriodic
{
	private int x, y, width, height, speed;
	private boolean control;
	private Set<CollisionType> collisions;
	
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
			if (Keyboard.getInstance().isKeyPressed(Keys.W) && !collisions.contains(CollisionType.TOP))
			{
				y -= speed;
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.S) && !collisions.contains(CollisionType.BOTTOM))
			{
				y += speed;
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.A) && !collisions.contains(CollisionType.LEFT))
			{
				x -= speed;
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.D) && !collisions.contains(CollisionType.RIGHT))
			{
				x += speed;
			}
		}
	}
	
	
	

	@Override
	public int getSpeed()
	{
		return this.speed;
	}

	@Override
	public void onCollision(Set<CollisionType> types)
	{
		this.collisions = types;
	}
}
