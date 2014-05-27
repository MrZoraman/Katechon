package apcs.katechon.engine.collisions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import apcs.katechon.logging.Log;
import static apcs.katechon.engine.collisions.Direction.*;

public class MattsCollisionEngine extends CollisionEngineBase
{
	private final Map<ICollidable, Set<Direction>> collidableCollisions;
	
	public MattsCollisionEngine()
	{
		this.collidableCollisions = new HashMap<ICollidable, Set<Direction>>();
	}
	
	@Override
	public void addItem(ICollidable item)
	{
		super.addItem(item);
		
		collidableCollisions.put(item, new HashSet<Direction>());
	}
	
	@Override
	public Map<ICollidable, Set<Direction>> getCollisions(ICollidable collidable)
	{
		return collidableCollisions;
	}

	@Override
	public Set<ICollidable> getCollidingObjects(ICollidable collidable)
	{
		return null;
	}

	@Override
	protected void process(Set<ICollidable> collidables)
	{
		//Remove finished objects
		Iterator<ICollidable> it_remover = collidables.iterator();
		
		while(it_remover.hasNext())
		{
			if(it_remover.next().isFinished())
			{
				it_remover.remove();
			}
		}
		
		for(ICollidable collidable : collidables)
		{
			//The collisions for the collidable
			Set<Direction> collisions = new HashSet<Direction>();
			
			for(ICollidable otherCollidable : collidables)
			{
				//Don't compare if it's the same collidable. It does not collide with itself.
				if(collidable == otherCollidable)
				{
					continue;
				}
				
				Direction collision = getCollision(collidable, otherCollidable);
				if(collision != NONE)
				{
					collisions.add(collision);
				}
			}
			
			if(!collisions.isEmpty())
			{
				collidable.onCollision(collisions);
			}
			
			//overwrite old set of collisions with latest data
			collidableCollisions.put(collidable, collisions);
		}
	}
	
	private Direction getCollision(ICollidable collidableA, ICollidable collidableB)
	{
		int a_speed	 = collidableA.getSpeed();     //speed is added to account for the fact that the objects may overshoot next tick.
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
