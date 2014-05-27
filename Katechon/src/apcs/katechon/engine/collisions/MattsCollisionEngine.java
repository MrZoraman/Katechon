package apcs.katechon.engine.collisions;

import java.util.Map;
import java.util.Set;

public class MattsCollisionEngine extends CollisionEngineBase
{
	@Override
	public Map<ICollidable, Set<Direction>> getCollisions(ICollidable collidable)
	{
		return null;
	}

	@Override
	public Set<ICollidable> getCollidingObjects(ICollidable collidable)
	{
		return null;
	}

	@Override
	protected void process(Set<ICollidable> items)
	{
		
	}
	
	private Direction isColliding(ICollidable collidableA, ICollidable collidableB)
	{
		int a_speed	 = collidableA.getSpeed();
		int a_top    = collidableA.getTopFace()    + a_speed;
		int a_bottom = collidableA.getBottomFace() + a_speed;
		int a_right  = collidableA.getRightFace()  + a_speed;
		int a_left   = collidableA.getLeftFace()   + a_speed;

		int b_speed  = collidableB.getSpeed();
		int b_top    = collidableB.getTopFace()    + b_speed;
		int b_bottom = collidableB.getBottomFace() + b_speed;
		int b_right  = collidableB.getRightFace()  + b_speed;
		int b_left   = collidableB.getLeftFace()   + b_speed;
		
		
		
		
		return Direction.NONE;
	}
}
