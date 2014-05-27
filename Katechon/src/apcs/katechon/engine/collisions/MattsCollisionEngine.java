package apcs.katechon.engine.collisions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static apcs.katechon.engine.collisions.Direction.*;

public class MattsCollisionEngine extends CollisionEngineBase
{
	private final Map<ICollidable, Set<Direction>> collidableCollisions;
	private final Map<ICollidable, Set<ICollidable>> collidingCollidables;
	
	public MattsCollisionEngine()
	{
		this.collidableCollisions = new HashMap<ICollidable, Set<Direction>>();
		this.collidingCollidables = new HashMap<ICollidable, Set<ICollidable>>();
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
		return collidingCollidables.get(collidable);
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
			
			//Clear old data for collidables colliding with the collidable
			collidingCollidables.get(collidable).clear();
			
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
					//Add this collidable to the set of collidables colliding with the target collidable
					collidingCollidables.get(collidable).add(otherCollidable);
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
	
	private Direction getCollision(ICollidable collidable, ICollidable other)
	{
		int speed  = collidable.getSpeed();     //speed is added to account for the fact that the objects may overshoot next tick.
		int top    = collidable.getTopFace()    + speed;
		int bottom = collidable.getBottomFace() + speed;
		int right  = collidable.getRightFace()  + speed;
		int left   = collidable.getLeftFace()   + speed;

		int o_speed  = other.getSpeed();
		int o_top    = other.getTopFace()    + o_speed;
		int o_bottom = other.getBottomFace() + o_speed;
		int o_right  = other.getRightFace()  + o_speed;
		int o_left   = other.getLeftFace()   + o_speed;
		
		
		
		
		return Direction.NONE;
	}
}