package apcs.katechon.engine.collisions;

import java.util.Map;

import apcs.katechon.engine.IEngine;

/**
 * Calculates collisions
 * @author Matt
 *
 */
public interface ICollisionEngine extends IEngine<ICollidable>
{
	public Map<ICollidable, CollisionType> getCollisions(final ICollidable collidable);
}
