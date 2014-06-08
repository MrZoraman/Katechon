package apcs.katechon.engine.physics;

import apcs.katechon.engine.IEngineItem;

/**
 * This should be implemented for all objects that can be acted upon by the physics engine
 * @author Sean
 *
 */
public interface IPhysicsObject extends IEngineItem
{
	/**
	 * @return The magnitude at which the object is moving
	 */
	public int getMagnitude();
	
	/**
	 * @return The direction of the object in degrees. (0 degrees being to the right)
	 */
	public int getDirection();
}
