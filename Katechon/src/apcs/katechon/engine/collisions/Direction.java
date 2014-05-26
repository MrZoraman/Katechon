package apcs.katechon.engine.collisions;

/**
 * Represents a type of collision
 * @author Matt
 *
 */
public enum Direction
{
	/**
	 * The top of the object is colliding with something
	 */
	TOP(0),
	
	/**
	 * The bottom of the object is colliding with something
	 */
	BOTTOM(Math.PI),
	
	/**
	 * The left of the object is colliding with something
	 */
	LEFT((Math.PI * 3) / 2),
	
	/**
	 * The right of the object is colliding with something
	 */
	RIGHT(Math.PI / 2),
	
	/**
	 * The object is not colliding with anything
	 */
	NONE(0);
	
	private final double radians;
	
	/**
	 * Ctor
	 * @param radians The direction represented in radians
	 */
	private Direction(double radians)
	{
		this.radians = radians;
	}
	
	/**
	 * @return The radians that this direction represents
	 */
	public double toRadians()
	{
		return radians;
	}
}
