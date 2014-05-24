package apcs.katechon.engine.collisions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static apcs.katechon.engine.collisions.CollisionType.*;

/**
 * A simple implementation of the collision engine. Should serve our needs nicely.
 * @author Matt
 *
 */
public class SimpleCollisionEngine extends CollisionEngineBase
{
	/*Not sure about this as the map will limit each collidable to having only one collision type at a time
	* allowing the chance of a collision to fail.
	*/
	private Map<ICollidable, Set<CollisionType>> collisions;
	
	public SimpleCollisionEngine()
	{
		this.collisions = new HashMap<ICollidable, Set<CollisionType>>();
	}
	
	@Override
	public Map<ICollidable, Set<CollisionType>> getCollisions(ICollidable collidable)
	{
		return collisions;
	}

	@Override
	protected void process(Set<ICollidable> items)
	{
		for (ICollidable item : items)
		{
			for (ICollidable item2 : items)
			{
				if (!item.equals(item2) && item != null && item2 != null)
				{
					//For testing purposes.
					//TODO: The above method should replace this.
					CollisionType type = getCollisionType(item, item2);
					//item.onCollision(type);
					Set<CollisionType> types = collisions.get(item);
					if (types == null)
					{
						types = new HashSet<CollisionType>();
					}

					types.add(type);
					
					Set<CollisionType> types2 = collisions.get(item2);
					if (types2 == null)
					{
						types2 = new HashSet<CollisionType>();
					}
					if (type == BOTTOM)
					{
						//item2.onCollision(TOP);
						types2.add(TOP);
					}
					else if (type == TOP)
					{
						//item2.onCollision(BOTTOM);
						types2.add(BOTTOM);
					}
					else if (type == RIGHT)
					{
						//item2.onCollision(LEFT);
						types2.add(LEFT);
					}
					else if (type == LEFT)
					{
						//item2.onCollision(RIGHT);
						types2.add(RIGHT);
					}
					else
					{
						//item2.onCollision(NONE);
						types2.clear();
						//types2.add(NONE);
					}
					
					collisions.put(item2, types2);
					collisions.put(item, types);
					
					item.onCollision(collisions.get(item));
					item2.onCollision(collisions.get(item2));
				}
			}
		}
	}
	
	private CollisionType getCollisionType(ICollidable item1, ICollidable item2)
	{
		if ((item1.getRightFace() == item2.getLeftFace() ||
				(item1.getRightFace() < (item2.getLeftFace() + item1.getSpeed()) && 
				item1.getRightFace() > (item2.getLeftFace() - item1.getSpeed())))
				
            && item1.getBottomFace() > item2.getTopFace()
            && item1.getTopFace() < item2.getBottomFace()
            /*&& item1.getDirection() < 90 || item1.getDirection() > 270*/)
        {
            return RIGHT;
        }
        else if ((item1.getLeftFace() == item2.getRightFace() ||
        		(item1.getLeftFace() < (item2.getRightFace() + item1.getSpeed()) &&
        		item1.getLeftFace() > (item2.getLeftFace() - item1.getSpeed())))
        		
            && item1.getBottomFace() > item2.getTopFace()
            && item1.getTopFace() < item2.getBottomFace()
            /*&& item1.getDirection() < 270 && item1.getDirection() > 90*/)
        {
            return LEFT;
        }
        else if ((item1.getTopFace() == item2.getBottomFace() ||
        		(item1.getTopFace() < (item2.getBottomFace() + item1.getSpeed()) &&
        		item1.getTopFace() > (item2.getBottomFace() - item1.getSpeed())))
        				
            && item1.getRightFace() > item2.getLeftFace()
            && item1.getLeftFace() < item2.getRightFace()
            /*&& item1.getDirection() < 180*/)
        {
            return TOP;
        }
        else if ((item1.getBottomFace() == item2.getTopFace() ||
        		(item1.getBottomFace() < (item2.getTopFace() + item1.getSpeed()) &&
        		item1.getBottomFace() > (item2.getTopFace() - item1.getSpeed())))
        		
            && item1.getRightFace() > item2.getLeftFace()
            && item1.getLeftFace() < item2.getRightFace()
            /*&& item1.getDirection() > 180*/)
        {
            return BOTTOM;
        }
        else
        {
            return NONE;
        }
	}
}
