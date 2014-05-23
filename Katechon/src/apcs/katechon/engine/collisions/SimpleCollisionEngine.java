package apcs.katechon.engine.collisions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import apcs.katechon.logging.Log;

/**
 * A simple implementation of the collision engine. Should serve our needs nicely.
 * @author Matt
 *
 */
public class SimpleCollisionEngine extends CollisionEngineBase
{
	private Map<ICollidable, CollisionType> collisions;
	
	public SimpleCollisionEngine()
	{
		this.collisions = new HashMap<ICollidable, CollisionType>();
	}
	
	@Override
	public Map<ICollidable, CollisionType> getCollisions(ICollidable collidable)
	{
		
		return null;
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
					item.onCollision(type);
					if (type == CollisionType.BOTTOM)
					{
						item2.onCollision(CollisionType.TOP);
					}
					else if (type == CollisionType.TOP)
					{
						item2.onCollision(CollisionType.BOTTOM);
					}
					else if (type == CollisionType.RIGHT)
					{
						item2.onCollision(CollisionType.LEFT);
					}
					else
					{
						item2.onCollision(CollisionType.RIGHT);
					}
					
					collisions.put(item, type);
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
            return CollisionType.RIGHT;
        }
        else if ((item1.getLeftFace() == item2.getRightFace() ||
        		(item1.getLeftFace() < (item2.getRightFace() + item1.getSpeed()) &&
        		item1.getLeftFace() > (item2.getLeftFace() - item1.getSpeed())))
        		
            && item1.getBottomFace() > item2.getTopFace()
            && item1.getTopFace() < item2.getBottomFace()
            /*&& item1.getDirection() < 270 && item1.getDirection() > 90*/)
        {
            return CollisionType.LEFT;
        }
        else if ((item1.getTopFace() == item2.getBottomFace() ||
        		(item1.getTopFace() < (item2.getBottomFace() + item1.getSpeed()) &&
        		item1.getTopFace() > (item2.getBottomFace() - item1.getSpeed())))
        				
            && item1.getRightFace() > item2.getLeftFace()
            && item1.getLeftFace() < item2.getRightFace()
            /*&& item1.getDirection() < 180*/)
        {
            return CollisionType.TOP;
        }
        else if ((item1.getBottomFace() == item2.getTopFace() ||
        		(item1.getBottomFace() < (item2.getTopFace() + item1.getSpeed()) &&
        		item1.getBottomFace() > (item2.getTopFace() - item1.getSpeed())))
        		
            && item1.getRightFace() > item2.getLeftFace()
            && item1.getLeftFace() < item2.getRightFace()
            /*&& item1.getDirection() > 180*/)
        {
            return CollisionType.BOTTOM;
        }
        else
        {
            return CollisionType.NONE;
        }
	}
}
