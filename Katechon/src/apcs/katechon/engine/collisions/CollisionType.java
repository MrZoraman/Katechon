package apcs.katechon.engine.collisions;

/**
 * Represents a type of collision
 * @author Matt
 *
 */
public enum CollisionType
{
	/**
	 * The top of the object is colliding with something
	 */
	TOP,
	
	/**
	 * The bottom of the object is colliding with something
	 */
	BOTTOM,
	
	/**
	 * The left of the object is colliding with something
	 */
	LEFT,
	
	/**
	 * The right of the object is colliding with something
	 */
	RIGHT,
	
	/**
	 * The object is not colliding with anything
	 */
	NONE
}
