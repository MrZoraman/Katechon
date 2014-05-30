package apcs.shoppingMaul;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.imgscalr.Scalr.Rotation;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.ImageUtils;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.resources.SpritesheetLoader;

/**
 * This is a snow leopard. It doesn't collide with anything. It is able to steadily move towards a given destination.
 * @author Matt
 *
 */
public class SnowLeopard implements IDrawable, ISchedulerTask
{
	/**
	 * This is a constant that affects how fast the leopard will go to it's destination relative to how far away it is
	 */
	private static final int SNAP_TO_CONSTANT = 15000;
	
	private final AnimatedSequence<BufferedImage> frames;
	
	private final int speed;
	
	private boolean finished;
	
	private Direction direction;
	
	private int x;
	private int y;
	
	private int destinationX;
	private int destinationY;
	
	private final AI ai;
	
	/**
	 * Constructor
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param speed The speed for the leopard
	 */
	public SnowLeopard(int x, int y, int speed)
	{
		this.ai = new AI(SNAP_TO_CONSTANT);
		this.speed = speed;
		
		this.finished = false;
		
		this.direction = Direction.TOP;
		
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
		BufferedImage copy = ImageUtils.deepCopy(frames.getCurrentFrame());
		
		Rotation rotation = direction.getRotation();
		
		if(rotation != null)
		{
			copy = ImageUtils.rotateImage(copy, direction.getRotation());
		}
		
		g.drawImage(copy, x, y, null);
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
	
//	/**
//	 * Causes the leopard to 'step' towards the direction that it's destination is at
//	 */
//	private void moveTowardsDestination()
//	{
//		int speed = this.speed;
//		
//		//find the difference in Cartesian coordinates
//		int preX = destinationX - x;
//		int preY = destinationY - y;
//		
//		//check if at destination or not
//		if(preX != 0 || preY != 0)
//		{
//			//find the hypotnuse (distance)
//			double hypotnuse = Math.sqrt((preX * preX) + (preY * preY));
//			
//			//the farther away the leopard is to it's destination, the faster it goes
//			speed += hypotnuse * hypotnuse / SNAP_TO_CONSTANT;
//			
//			if(Math.abs(hypotnuse) < speed)
//			{
//				//we are going to overshoot so we just go to our destination
//				this.x = destinationX;
//				this.y = destinationY;
//				return;
//			}
//			else if (preX == 0)
//			{
//				//moving only in the y direction
//				if (preY < 0)
//				{
//					//negative, need to go up
//					this.y -= speed;
//				}
//				else
//				{
//					//positive, need to go down
//					this.y += speed;
//				}
//			}
//			else
//			{
//				//Cartesian coordinates
//				double radius = speed;
//				double angle = Math.atan(preY / preX);
//				
//				if(preX < 0 && preY >= 0)
//				{
//					//quadrant II
//					angle += Math.PI;//180 degrees
//				}
//				else if (preX < 0 && preY < 0)
//				{
//					//quadrant III
//					angle += Math.PI;//180 degrees
//				}
//				else if (preX > 0 && preY < 0)
//				{
//					//quadrant IV
//					angle += Math.PI * 2;//360 degrees
//				}
//				
//				//back to Cartesian coordinates
//				int cX = (int) (radius * Math.cos(angle));
//				int cY = (int) (radius * Math.sin(angle));
//				
//				//add the difference to the coordinates
//				this.x += cX;
//				this.y += cY;
//			}
//		}
//	}

	@Override
	public void doTask()
	{
		Point p = ai.moveTowardsDestination(speed, x, y, destinationX, destinationY);
		this.x = (int) p.getX();
		this.y = (int) p.getY();
	}
	
	/**
	 * Sets the direction for the leopard to face
	 * @param direction The direction to face
	 */
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
}
