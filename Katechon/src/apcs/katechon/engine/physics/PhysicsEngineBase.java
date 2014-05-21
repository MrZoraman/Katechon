package apcs.katechon.engine.physics;

import apcs.katechon.engine.EngineModuleBase;

/**
 * Does physics stuff
 * @author Matt
 *
 */
public abstract class PhysicsEngineBase extends EngineModuleBase<IPhysicsObject>
{
	public PhysicsEngineBase()
	{
		super(IPhysicsObject.class);
	}
	
	// physics engine specific methods go here
}
