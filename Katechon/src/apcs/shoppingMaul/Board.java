package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;

/**
 * This is it. The game board. It keeps the player in the middle and when you move up and down it scrolls the view around to keep your character in the middle.
 * @author Matt
 *
 */
public class Board extends LeopardPack implements IDrawable
{
	private static final int X_OFFSET = -75;
	private static final int Y_OFFSET = -75;
	
	private final List<IDrawable> drawables;
	
	public Board(ControlScheme controlScheme, int speed, int amountOfLeopards, int width, int height)
	{
		super(controlScheme, (width / 2) + X_OFFSET, (height / 2) + Y_OFFSET, speed, amountOfLeopards);
		
		drawables = new ArrayList<IDrawable>();
		
		
	}
	
	@Override
	public void move(Set<Direction> directions)
	{
		//for each direction the player wants to move in
		for(Direction direction : directions)
		{
			Log.info("Trying to move " + direction + "!");
		}
	}
	
	public void addDrawable(IDrawable drawable)
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
				drawables.get(ii).draw(g);
			}
		}
	}
}
