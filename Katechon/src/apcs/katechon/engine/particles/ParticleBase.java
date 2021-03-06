package apcs.katechon.engine.particles;

import java.awt.Point;

import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.utils.AI;

public abstract class ParticleBase implements IDrawable, ISchedulerTask
{
	private static final int SNAP_TO_CONSTANT = 15000;
	
	private final AI ai;
	
	private final int speed;
	
	private int x;
	private int y;
	
	private int destinationX;
	private int destinationY;
	
	public ParticleBase(int speed, int x, int y)
	{
		this.ai = new AI(SNAP_TO_CONSTANT);
		
		this.speed = speed;
		this.x = x;
		this.y = y;
		
		this.destinationX = x;
		this.destinationY = y;
	}
	
	public void setDestination(int destinationX, int destinationY)
	{
		this.destinationX = destinationX;
		this.destinationY = destinationY;
	}
	
	@Override
	public void doTask()
	{
		Point p = ai.moveTowardsDestination(speed, x, y, destinationX, destinationY);
		setX((int) p.getX());
		setY((int) p.getY());
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
