package apcs.katechon.test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.rendering.IDrawable;

public class SimpleCollidable implements ICollidable, IDrawable, ISchedulerTask
{
	protected int x, y, width, height, speed;
	private boolean control;
	private Set<Direction> collisions;
	
	public SimpleCollidable(int x, int y, int width, int height, int speed, boolean control)
	{
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.control = control;
		
		collisions = new HashSet<Direction>();
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

	@Override
	public void doTask()
	{
		if (this.control)
		{
			if (Keyboard.getInstance().isKeyPressed(Keys.W) && !collisions.contains(Direction.TOP))
			{
				y -= speed;
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.S) && !collisions.contains(Direction.BOTTOM))
			{
				y += speed;
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.A) && !collisions.contains(Direction.LEFT))
			{
				x -= speed;
			}
			
			if (Keyboard.getInstance().isKeyPressed(Keys.D) && !collisions.contains(Direction.RIGHT))
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
	public void onCollision(Set<Direction> types)
	{
		this.collisions = types;
	}

	@Override
	public boolean isFinished() {
		//lifetime is infinite
		return false;
	}

	@Override
	public void setFinished(boolean finished)
	{
		// TODO Auto-generated method stub
		
	}
}
