package apcs.katechon.basicGameObjects;

import java.util.HashSet;
import java.util.Set;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;

/**
 * This is a class that a person controls and will collide with things
 * @author Matt
 *
 */
public class ControllableCollidable extends Controllable implements ICollidable
{
	private final int width;
	private final int height;
	
	private final int speed;
	
	private Set<Direction> collisions;
	
	private int x;
	private int y;
	
	/**
	 * Constructor
	 * @param controlScheme The button mapping for controlling this object
	 * @param x X coordinate for where the object will be drawn on the screen
	 * @param y Y coordinate for where the object will be drawn on the screen
	 * @param width The width of the object
	 * @param height The height of the object
	 * @param speed The speed at which the object will travel
	 */
	public ControllableCollidable(ControlScheme controlScheme, int x, int y, int width, int height, int speed)
	{
		super(controlScheme);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.speed = speed;
		
		collisions = new HashSet<Direction>();
	}

	@Override
	public void move(Set<Direction> directions)
	{
		//for each direction the player wants to move in
		for(Direction direction : directions)
		{
			//if that direction is not one that is in the direction of a collision
			if(!collisions.contains(direction))
			{
				//move
				switch(direction)
				{
				case BOTTOM:
					y += speed;
					break;
				case TOP:
					y -= speed;
					break;
				case LEFT:
					x -= speed;
					break;
				case RIGHT:
					x += speed;
					break;
				case NONE:
					break;
				}
			}
		}
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
	public int getSpeed()
	{
		return this.speed;
	}
	
	@Override
	public void onCollision(Set<Direction> collisions)
	{
		this.collisions = collisions;
	}
	
	protected int getX()
	{
		return x;
	}
	
	protected int getY()
	{
		return y;
	}
}
