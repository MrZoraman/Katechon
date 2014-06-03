package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;

public class SimpleBoardCollidable implements ICollidable, ISchedulerTask, IControlledDrawable
{
	protected int x, y, width, height, speed;
	private boolean control;
	private Set<Direction> collisions;
	
	private int realX, realY;
	
	public SimpleBoardCollidable(int x, int y, int width, int height, int speed, boolean control)
	{
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.control = control;
		
		this.realX = x;
		this.realY = y;
		
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
		g.drawRect(x, y, width, height);
	}

//	@Override
//	public void draw(Graphics g)
//	{
//		g.setColor(Color.GRAY);
//		g.fillRect(x, y, width, height);
//		g.setColor(Color.WHITE);
//	}

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
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x)
	{
		this.x = x;
	}

	@Override
	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public int getRealX()
	{
		return realX;
	}

	@Override
	public int getRealY()
	{
		return realY;
	}
	
	protected void setRealX(int x)
	{
		this.realX = x;
	}
	
	protected void setRealY(int realY)
	{
		this.realY = realY;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public void setFinished(boolean finished)
	{
		//lifetime is infinite!!!!!!		
	}
}
