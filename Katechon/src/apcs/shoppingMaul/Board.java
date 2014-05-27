package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import apcs.katechon.SwingWindow;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.basicGameObjects.Controllable;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.CollisionEngineBase;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;

/**
 * This is it. The game board. It keeps the player in the middle and when you move up and down it scrolls the view around to keep your character in the middle.
 * @author Matt
 *
 */
public class Board extends Controllable implements ICollidable, IDrawable
{
	private static final int COLLISION_BOX_SIZE = 50;
	
	private final int collisionBoxX;
	private final int collisionBoxY;
	
	private final int speed;

	private final List<IControlledDrawable> drawables;
	private final LeopardPack pack;
	
	private int xOffset;
	private int yOffset;
	
	public Board(ControlScheme controlScheme, LeopardPack pack, SwingWindow window, int speed)
	{
		super(controlScheme);
		
		this.pack = pack;
		this.speed = speed;
		this.drawables = new ArrayList<IControlledDrawable>();
		
		int windowWidth = window.getWidth();
		int windowHeight = window.getHeight();
		
		this.collisionBoxX = (windowWidth / 2) - (COLLISION_BOX_SIZE / 2);
		this.collisionBoxY = (windowHeight / 2) - (COLLISION_BOX_SIZE / 2);
		
		this.xOffset = 0;
		this.yOffset = 0;
	}
	
	@Override
	public void doTask()
	{
		super.doTask();
		
		CollisionEngineBase ce = (CollisionEngineBase) EngineManager.getInstance().getEngine(ICollidable.class);
		
		Set<ICollidable> collidingObjects = ce.getCollidingObjects(this);
		
//		Log.debug("colliding objects is null: " + (collidingObjects == null));
		
		Log.debug("I have myself a " + ce.toString());
		
//		for(ICollidable ic : collidingObjects)
//		{
//			System.out.println(ic);
//		}
	}
	
	public void goTo(int x, int y)
	{
		this.xOffset = x;
		this.yOffset = y;
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawRect(collisionBoxX, collisionBoxY, COLLISION_BOX_SIZE, COLLISION_BOX_SIZE);
		
		for(int ii = drawables.size() - 1; ii >= 0; ii--)
		{
			if(drawables.get(ii).isFinished())
			{
				drawables.remove(ii);
			}
			else
			{
				IControlledDrawable icd = drawables.get(ii);
				int xCoord = icd.getRealX();
				int yCoord = icd.getRealY();
				
				int newX = xCoord + xOffset;
				int newY = yCoord + yOffset;
				
				icd.setX(newX);
				icd.setY(newY);
				
				icd.draw(g);
			}
		}
	}
	
	public void addDrawable(IControlledDrawable drawable)
	{
		if(drawable == this)
		{
			Log.error("WHAT HAVE YOU DONE?!");
		}
		else
		{
			drawables.add(drawable);
		}
	}

	@Override
	public void move(Set<Direction> directions)
	{
		for(Direction direction : directions)
		{
			switch(direction)
			{
			case TOP:
				yOffset += speed;
				break;
			case BOTTOM:
				yOffset -= speed;
				break;
			case LEFT:
				xOffset += speed;
				break;
			case RIGHT:
				xOffset -= speed;
				break;
			case NONE:
				break;
			}
		}
		
		if(!directions.isEmpty())
		{
			pack.setDirection(directions.iterator().next());
		}
	}
	
	@Override
	public void onCollision(Set<Direction> types)
	{
//		System.out.println(types);
	}

	@Override
	public int getTopFace()
	{
		return collisionBoxY;
	}

	@Override
	public int getBottomFace()
	{
		return collisionBoxY + COLLISION_BOX_SIZE;
	}

	@Override
	public int getLeftFace()
	{
		return collisionBoxX;
	}

	@Override
	public int getRightFace()
	{
		return collisionBoxX + COLLISION_BOX_SIZE;
	}

	@Override
	public int getSpeed()
	{
		return speed;
	}
}
