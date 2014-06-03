package apcs.katechon.test;

import java.util.Set;

import apcs.katechon.KatechonEngine;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;

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
	public void onCollision(Set<Direction> types)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFinished() {
		//lifetime is infinite
		return false;
	}

	@Override
	public void setFinished(boolean finished)
	{
		//lifetime is infinite
	}
}
