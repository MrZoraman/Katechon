package apcs.katechon.test;

import apcs.katechon.KatechonEngine;
import apcs.katechon.engine.collisions.CollisionType;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.logging.Log;

public class Border implements ICollidable
{

	@Override
	public int getTopFace()
	{
		return KatechonEngine.getInstance().getSwingWindow().getHeight();
	}

	@Override
	public int getBottomFace()
	{
		return 0;
	}

	@Override
	public int getLeftFace()
	{
		return KatechonEngine.getInstance().getSwingWindow().getWidth();
	}

	@Override
	public int getRightFace()
	{
		return 0;
	}

	@Override
	public int getSpeed()
	{
		return 0;
	}

	@Override
	public void onCollision(CollisionType type)
	{
		Log.debug("Border collided on side: " + type.toString());
	}

}
