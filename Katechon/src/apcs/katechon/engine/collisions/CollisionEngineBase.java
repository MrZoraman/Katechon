package apcs.katechon.engine.collisions;

import java.util.Map;
import java.util.Set;

import apcs.katechon.engine.EngineModuleBase;

/**
 * Calculates collisions
 * @author Matt
 *
 */
public abstract class CollisionEngineBase extends EngineModuleBase<ICollidable>
{
	public CollisionEngineBase()
	{
		super(ICollidable.class);
	}
	
	/**
	 * Returns a set of colliding objects given a collidable object
	 * @param collidable The object to test if things are colliding with it or not
	 * @return A map of the objects that are colliding, and their {@link apcs.katechon.engine.collisions.Direction collision state}
	 */
	public abstract Map<ICollidable, Set<Direction>> getCollisions(final ICollidable collidable);
}
