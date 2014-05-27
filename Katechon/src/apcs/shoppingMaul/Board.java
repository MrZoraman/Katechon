package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import apcs.katechon.SwingWindow;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.basicGameObjects.Controllable;
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
	
	private final int topFace;
	private final int bottomFace;
	private final int leftFace;
	private final int rightFace;
	
	private final int speed;

	private final List<IControlledDrawable> drawables;
	
	private int xOffset;
	private int yOffset;
	
	public Board(ControlScheme controlScheme, SwingWindow window, int speed)
	{
		super(controlScheme);
		
		this.speed = speed;
		this.drawables = new ArrayList<IControlledDrawable>();
		
		int windowWidth = window.getWidth();
		int windowHeight = window.getHeight();
		
		this.topFace = (windowHeight / 2) - (COLLISION_BOX_SIZE / 2);
		this.bottomFace = (windowHeight / 2) + (COLLISION_BOX_SIZE / 2);
		this.leftFace = (windowWidth / 2) - (COLLISION_BOX_SIZE / 2);
		this.rightFace = (windowWidth / 2) + (COLLISION_BOX_SIZE / 2);
		
		this.xOffset = 0;
		this.yOffset = 0;
	}
	
	@Override
	public void draw(Graphics g)
	{
		for(int ii = drawables.size() - 1; ii >= 0; ii--)
		{
			if(drawables.get(ii).isFinished())
			{
				drawables.remove(ii);
			}
			else
			{
				IControlledDrawable icd = drawables.get(ii);
				int xCoord = icd.getX();
				int yCoord = icd.getY();
				
				int newX = xCoord + xOffset;
				int newY = yCoord + yOffset;
				icd.draw(g, newX, newY);
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
				yOffset -= speed;
				break;
			case BOTTOM:
				yOffset += speed;
				break;
			case LEFT:
				xOffset -= speed;
				break;
			case RIGHT:
				xOffset += speed;
				break;
			case NONE:
				break;
			}
		}
	}
	
	@Override
	public void onCollision(Set<Direction> types)
	{
		//TODO: this is where the kill logic goes
	}

	@Override
	public int getTopFace()
	{
		return bottomFace;
	}

	@Override
	public int getBottomFace()
	{
		return topFace;
	}

	@Override
	public int getLeftFace()
	{
		return leftFace;
	}

	@Override
	public int getRightFace()
	{
		return rightFace;
	}

	@Override
	public int getSpeed()
	{
		return speed;
	}
}
