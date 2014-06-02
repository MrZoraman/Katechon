package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import apcs.katechon.KatechonEngine;
import apcs.katechon.SwingWindow;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.basicGameObjects.Controllable;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.CollisionEngineBase;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;
import apcs.shoppingMaul.man.Man;

/**
 * This is it. The game board. It keeps the player in the middle and when you move up and down it scrolls the view around to keep your character in the middle.
 * @author Matt
 *
 */
public class Board extends Controllable implements ICollidable, IDrawable
{
	private static final int COLLISION_BOX_SIZE = 100;
	
	private final int collisionBoxX;
	private final int collisionBoxY;
	
	private final int speed;

	private final List<IControlledDrawable> drawables;
	private final LeopardPack pack;
	
	private int xOffset;
	private int yOffset;
	
	private Man target;
	
	private Set<Direction> collisions;
	
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
		
		this.collisions = new HashSet<Direction>();
	}
	
	@Override
	public void doTask()
	{
		super.doTask();
		
		CollisionEngineBase ce = (CollisionEngineBase) EngineManager.getInstance().getEngine(ICollidable.class);
		
		Set<ICollidable> collidingObjects = ce.getCollidingObjects(this);
		for(ICollidable ic : collidingObjects)
		{		
			if(ic == target)
			{
				Log.info("you found him!");
				final FinishedMessage message = new FinishedMessage();
				Keyboard.getInstance().setExclusiveKeyListener(new KeyPressedListener(){

					@Override
					public boolean isFinished()
					{
						return false;
					}

					@Override
					public void onKeyPressed(Keys key, char keyChar)
					{
						//do nothing
					}
					
				});
				KatechonEngine.getInstance().addDrawable(message, 4);
			}
			
			if(ic instanceof Man)
			{
				Man man = (Man) ic;
				man.setDead(true);
			}
		}
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
		
//		for(int ii = drawables.size() - 1; ii >= 0; ii--)
		for(int ii = 0; ii < drawables.size(); ii++)
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
				
				
//				if (xCoord + icd.getWidth() < 0 || xCoord > KatechonEngine.getInstance().getSwingWindow().getWidth())
//				{
//					continue;
//				}
//				
//				if (yCoord + icd.getHeight() < 0 || yCoord > KatechonEngine.getInstance().getSwingWindow().getHeight())
//				{
//					continue;
//				}
				
				System.out.println("Drawing: " + icd);
				
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
				if (!collisions.contains(Direction.TOP))
				{
					yOffset += speed;
				}
				break;
			case BOTTOM:
				if (!collisions.contains(Direction.BOTTOM))
				{
					yOffset -= speed;
				}
				break;
			case LEFT:
				if (!collisions.contains(Direction.LEFT))
				{
					xOffset += speed;
				}
				break;
			case RIGHT:
				if (!collisions.contains(Direction.RIGHT))
				{
					xOffset -= speed;
				}
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
		this.collisions = types;
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

	public ICollidable getTarget() {
		return target;
	}

	public void setTarget(Man target) {
		this.target = target;
	}
	
	@Override
	public String toString()
	{
		return "Board";
	}
	
	public int getXPosition()
	{
		return this.xOffset;
	}
	
	public int getYPosition()
	{
		return this.yOffset;
	}
}
