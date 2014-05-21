package apcs.katechon.engine.physics;

import java.util.Set;

import apcs.katechon.logging.Log;

public class SimplePhysicsEngine extends PhysicsEngineBase
{
	@Override
	protected void process(Set<IPhysicsObject> items)
	{
		Log.info("SimplePhysicsEngine processing " + items.size() + " items.");
	}
}
