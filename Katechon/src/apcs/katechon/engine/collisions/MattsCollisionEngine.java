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
		collidingCollidables.put(item, new HashSet<ICollidable>());
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
				
				Set<Direction> collisionsFound = getCollision(collidable, otherCollidable);
//				Log.debug("Collision retrieved: " + collision);
				
				if(!collisionsFound.isEmpty())
				{
					collisions.addAll(collisionsFound);
					//Add this collidable to the set of collidables colliding with the target collidable
					collidingCollidables.get(collidable).add(otherCollidable);
				}
			}
			
			
			collidable.onCollision(collisions);
			
			//overwrite old set of collisions with latest data
			collidableCollisions.put(collidable, collisions);
		}
	}
	
	private Set<Direction> getCollision(ICollidable collidable, ICollidable other)
	{
		int speed  = collidable.getSpeed();     //speed is added to account for the fact that the objects may overshoot next tick.
		int top    = collidable.getTopFace()    - speed;
		int bottom = collidable.getBottomFace() + speed;
		int right  = collidable.getRightFace()  + speed;
		int left   = collidable.getLeftFace()   - speed;

		int o_speed  = other.getSpeed();
		int o_top    = other.getTopFace()    - o_speed;
		int o_bottom = other.getBottomFace() + o_speed;
		int o_right  = other.getRightFace()  + o_speed;
		int o_left   = other.getLeftFace()   - o_speed;
		
		final Set<Direction> collisionsFound = new HashSet<Direction>();
		
		//The top of collidable is colliding
		
		//is the top side higher than o's bottom
		// and is top side lower than o's top?
		if(isHigherOrEqual(top, o_bottom) && isLowerOrEqual(top, o_bottom - (speed * 5)))
		{
			//is the left side further right than o's left side
			// and is left side further left than o's right side?
			if(isRighterOrEqual(left, o_left) && isLefterOrEqual(left, o_right))
			{
				collisionsFound.add(TOP);
			}
			//is the right side further left than o's right side
			// and is right side further right than o's left side?
			if(isLefterOrEqual(right, o_right) && isRighterOrEqual(right, o_left))
			{
				collisionsFound.add(TOP);
			}
		}
		
		//The bottom of collidable is colliding
		
		//is the bottom side lower than o's top
		// and is bottom side higher than o's bottom?
		if(isLowerOrEqual(bottom, o_top) && isHigherOrEqual(bottom, o_top + (speed * 5)))
		{
			//is the left side further right than o's left side
			// and is left side further left than o's right side?
			if(isRighterOrEqual(left, o_left) && isLefterOrEqual(left, o_right))
			{
				collisionsFound.add(BOTTOM);
			}
			//is the right side further left than o's right side
			// and is right side further right than o's left side?
			if(isLefterOrEqual(right, o_right) && isRighterOrEqual(right, o_left))
			{
				collisionsFound.add(BOTTOM);
			}
		}
		
		//The right of collidable is colliding
		
		//is the right side righter than o's left
		// and is right side lefter than o's right?
		if(isRighterOrEqual(right, o_left) && isLefterOrEqual(right, o_left + (speed * 5)))
		{
			//is the top side lower than o's top
			// and is top side higher than o's bottom?
			if(isLowerOrEqual(top, o_top) && isHigherOrEqual(top, o_bottom))
			{
				collisionsFound.add(RIGHT);
			}
			//is the bottom side higher than o's bottom
			// and is bottom side lower than o's top?
			if(isHigherOrEqual(bottom, o_bottom) && isLowerOrEqual(bottom, o_top))
			{
				collisionsFound.add(RIGHT);
			}
		}
		
		//The left of collidable is colliding
		
		//is the left side lefter than o's right
		// and is left side righter than o's left?
		if(isLefterOrEqual(left, o_right) && isRighterOrEqual(left, o_right - (speed * 5)))
		{
			//is the top side lower than o's top
			// and is top side higher than o's bottom?
			if(isLowerOrEqual(top, o_top) && isHigherOrEqual(top, o_bottom))
			{
				collisionsFound.add(LEFT);
			}
			//is the bottom side higher than o's bottom
			// and is bottom side lower than o's top?
			if(isHigherOrEqual(bottom, o_bottom) && isLowerOrEqual(bottom, o_top))
			{
				collisionsFound.add(LEFT);
			}
		}
		
		return collisionsFound;
	}
	
//	private Set<Direction> getCollision(ICollidable item1, ICollidable item2)
//	{
//		Set<Direction> collisionsFound = new HashSet<Direction>();
//		if ((item1.getRightFace() == item2.getLeftFace() ||
//				(item1.getRightFace() < (item2.getLeftFace() + item1.getSpeed()) && 
//				item1.getRightFace() > (item2.getLeftFace() - item1.getSpeed()))))
//        {
//			if (item1.getBottomFace() > item2.getTopFace() && item1.getTopFace() < item2.getBottomFace())
//            collisionsFound.add(RIGHT);
//        }
//        if ((item1.getLeftFace() == item2.getRightFace() ||
//        		(item1.getLeftFace() < (item2.getRightFace() + item1.getSpeed()) &&
//        		item1.getLeftFace() > (item2.getLeftFace() - item1.getSpeed())))
//        		
//            && item1.getBottomFace() > item2.getTopFace()
//            && item1.getTopFace() < item2.getBottomFace())
//        {
//            collisionsFound.add(LEFT);
//        }
//        if ((item1.getTopFace() == item2.getBottomFace() ||
//        		(item1.getTopFace() < (item2.getBottomFace() + item1.getSpeed()) &&
//        		item1.getTopFace() > (item2.getBottomFace() - item1.getSpeed()))))
//        {
//        	if (item1.getRightFace() > item2.getLeftFace() && item1.getLeftFace() < item2.getRightFace())
//        		collisionsFound.add(TOP);
//        }
//        if ((item1.getBottomFace() == item2.getTopFace() ||
//        		(item1.getBottomFace() < (item2.getTopFace() + item1.getSpeed()) &&
//        		item1.getBottomFace() > (item2.getTopFace() - item1.getSpeed()))))
//        {
//    		if (item1.getRightFace() > item2.getLeftFace() && item1.getLeftFace() < item2.getRightFace())
//            collisionsFound.add(BOTTOM);
//        }
//		
//		return collisionsFound;
//	}
	
	private boolean isHigherOrEqual(int side, int other)
	{
		return side <= other;
	}
	
	private boolean isLowerOrEqual(int side, int other)
	{
		return side >= other;
	}
	
	private boolean isRighterOrEqual(int side, int other)
	{
		return side >= other;
	}
	
	private boolean isLefterOrEqual(int side, int other)
	{
		return side <= other;
	}
}
