package apcs.katechon.engine.collisions;

import java.util.HashMap;
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
					if (type == BOTTOM)
					{
						item2.onCollision(TOP);
					}
					else if (type == TOP)
					{
						item2.onCollision(BOTTOM);
					}
					else if (type == RIGHT)
					{
						item2.onCollision(LEFT);
					}
					else if (type == LEFT)
					{
						item2.onCollision(RIGHT);
					}
					else
					{
						item2.onCollision(NONE);
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
