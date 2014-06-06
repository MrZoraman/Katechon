package apcs.katechon.test;

import apcs.katechon.engine.physics.IPhysicsObject;

public class SimplePhysicsObject implements IPhysicsObject
{

	@Override
	public int getMagnitude()
	{
		return 0;
	}

	@Override
	public int getDirection()
	{
		return 0;
	}

	@Override
	public boolean isFinished() {
		//lifetime is infinite
		return false;
	}

	@Override
	public void setFinished(boolean finished)
	{
		// TODO Auto-generated method stub
		
	}

}
