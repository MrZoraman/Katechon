package apcs.katechon.test;

import java.util.Set;

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
	public void onCollision(Set<CollisionType> types)
	{
		// TODO Auto-generated method stub
		
	}
}
