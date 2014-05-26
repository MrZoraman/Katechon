package apcs.shoppingMaul;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.resources.SpritesheetLoader;

public class SnowLeopard implements IDrawable, ISchedulerTask
{
	private final AnimatedSequence<BufferedImage> frames;
	
	private final int speed;
	
	private boolean finished;
	
	private int x;
	private int y;
	
	private int destinationX;
	private int destinationY;
	
	public SnowLeopard(int x, int y, int speed)
	{
		this.speed = speed;
		
		this.finished = false;
		
		this.x = x;
		this.y = y;
		
		this.destinationX = x;
		this.destinationY = y;
		
		//load the frames
		SpritesheetLoader loader = new SpritesheetLoader(ShoppingMaul.class, "/apcs/shoppingMaul/assets/snowleopard.png", 8, 1);
		BufferedImage[] imageFrames = loader.loadWide(0, 0, 7);
		this.frames = new AnimatedSequence<BufferedImage>(imageFrames, 1);
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(frames.getCurrentFrame(), x, y, null);
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}
	
	/**
	 * Sets the state of this snow leopard.
	 * @param finished True if the leopard is done and can be removed from the game engine. False if not done yet.
	 */
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	
	/**
	 * Sets the destination for this snow leopard to move towards
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void setDestination(int x, int y)
	{
		this.destinationX = x;
		this.destinationY = y;
	}
	
	private void moveTowardsDestination()
	{
		//find the difference in Cartesian coordinates
		int preX = destinationX - x;
		int preY = destinationY - y;
		
		//check if at destination or not
		if(preX != 0 && preY != 0)
		{
			//to polar coordinates
			double radius = Math.sqrt((preX * preX) + (preY * preY));
			double angle = Math.atan(preY / preX);
			
			if(preX < 0 && preY > 0)
			{
				//quadrant II
				radius += Math.PI;//180 degrees
			}
			else if (preX < 0 && preY < 0)
			{
				//quadrant III
				radius += Math.PI;//180 degrees
			}
			else if (preX > 0 && preY < 0)
			{
				//quadrant IV
				radius += Math.PI * 2;//360 degrees
			}
			
			//add our distance traveled
			radius += speed;
			
			//back to Cartesian coordinates
			int cX = (int) (radius * Math.cos(angle));
			int cY = (int) (radius * Math.sin(angle));
			
			//add the difference to the coordinates
			this.x += cX;
			this.y += cY;
		}
	}

	@Override
	public void doTask()
	{
		moveTowardsDestination();
	}
}
