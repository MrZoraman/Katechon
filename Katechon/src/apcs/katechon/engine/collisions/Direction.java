package apcs.katechon.engine.collisions;

import org.imgscalr.Scalr.Rotation;

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
	TOP(0, null),
	
	/**
	 * The bottom of the object is colliding with something
	 */
	BOTTOM(Math.PI, Rotation.CW_180),
	
	/**
	 * The left of the object is colliding with something
	 */
	LEFT((Math.PI * 3) / 2, Rotation.CW_90),
	
	/**
	 * The right of the object is colliding with something
	 */
	RIGHT(Math.PI / 2, Rotation.CW_270),
	
	/**
	 * The object is not colliding with anything
	 */
	NONE(0, null);
	
	private final double radians;
	private final Rotation rotation;
	
	/**
	 * Ctor
	 * @param radians The direction represented in radians
	 * @param rotation for imgscalr uses
	 */
	private Direction(double radians, Rotation rotation)
	{
		this.radians = radians;
		this.rotation = rotation;
	}
	
	/**
	 * @return The radians that this direction represents
	 */
	public double toRadians()
	{
		return radians;
	}
	
	/**
	 * @return The rotation for imgscalr uses
	 */
	public Rotation getRotation()
	{
		return rotation;
	}
}
