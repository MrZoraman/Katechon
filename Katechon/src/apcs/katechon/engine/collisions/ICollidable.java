package apcs.katechon.engine.collisions;

/**
 * Represents something that can collide
 * @author Matt
 *
 */
public interface ICollidable
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
}
