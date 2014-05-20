package apcs.katechon.engine.physics;

import java.util.Set;

import apcs.katechon.engine.IEngine;

/**
 * Does physics stuff
 * @author Matt
 *
 */
public interface IPhysicsEngine extends IEngine<IPhysicsObject>
{
	public void doPhysicsStuff(final Set<IPhysicsObject> physicsObjects);
}
