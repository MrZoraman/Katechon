package apcs.katechon.engine.collisions;

import java.util.Map;

import apcs.katechon.engine.EngineModuleBase;

/**
 * Calculates collisions
 * @author Matt
 *
 */
public abstract class CollisionEngineBase extends EngineModuleBase<ICollidable>
{
	public abstract Map<ICollidable, CollisionType> getCollisions(final ICollidable collidable);
}
