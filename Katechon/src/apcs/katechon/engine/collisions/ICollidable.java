package apcs.katechon.engine.collisions;

import java.util.Set;

import apcs.katechon.engine.IEngineItem;

/**
 * Represents something that can collide
 * @author Matt
 *
 */
public interface ICollidable extends IEngineItem
{
	/**
	 * @return The upper most boundary of an object
	 */
	public int getTopFace();
	
	/**
	 * @return The lower most boundary of an object
	 */
	public int getBottomFace();
	
	/**
	 * @return The left most boundary of an object
	 */
	public int getLeftFace();
	
	/**
	 * @return The right most boundary of an object
	 */
	public int getRightFace();
	
	/**
	 * @return The speed of the collidable.
	 */
	public int getSpeed();
	
	/**
	 * What the collidable should do when a collision occurs.
	 * @param The type of collision that has occurred.
	 */
	public void onCollision(Set<Direction> types);
}
