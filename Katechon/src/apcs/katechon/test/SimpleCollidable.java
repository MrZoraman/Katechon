package apcs.katechon.test;

import apcs.katechon.engine.collisions.ICollidable;

public class SimpleCollidable implements ICollidable
{

	@Override
	public int getTopFace()
	{
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getBottomFace()
	{
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getLeftFace()
	{
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRightFace()
	{
		// TODO Auto-generated method stub
		return 4;
	}

}
