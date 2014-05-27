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
	private static final int X_CENTER_OFFSET = -75;
	private static final int Y_CENTER_OFFSET = -75;
	
	private final List<IControlledDrawable> drawables;
	private final int width;
	private final int height;
	private final int xOffset;
	private final int yOffset;
	
	public Board(ControlScheme controlScheme, int speed, int amountOfLeopards, int width, int height)
	{
		super(controlScheme, (width / 2) + X_CENTER_OFFSET, (height / 2) + Y_CENTER_OFFSET, speed, amountOfLeopards);
		
		this.drawables = new ArrayList<IControlledDrawable>();
		this.width = width;
		this.height = height;
		xOffset = 0;
		yOffset = 0;
	}
	
	
	
	@Override
	public void move(Set<Direction> directions)
	{
		super.move(directions);
		Log.info("moved");
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
	
	@Override
	protected int getX()
	{
		Log.info("width: " + width);
		return width / 2;
	}
	
	@Override
	protected int getY()
	{
		return height / 2;
	}
}
