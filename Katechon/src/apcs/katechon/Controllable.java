package apcs.katechon;

import java.util.HashSet;
import java.util.Set;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;

import static apcs.katechon.engine.collisions.Direction.*;

/**
 * Represents a simple object that can be controlled by the user and moved around the screen
 * @author Matt
 *
 */
public abstract class Controllable implements ISchedulerTask
{
	private final Keys leftKey, rightKey, upKey, downKey;
	
	private final Keyboard keyboard;
	
	public Controllable(final Keys leftKey, final Keys rightKey, final Keys upKey, final Keys downKey)
	{
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.upKey = upKey;
		this.downKey = downKey;
		
		this.keyboard = Keyboard.getInstance();
	}
	
	/**
	 * Called when the user requests to go up, down, left and/or right
	 * @param directions The directions that the user wants to go in
	 */
	public abstract void move(Set<Direction> directions);
	
	@Override
	public void doTask()
	{
		Set<Direction> directions = new HashSet<Direction>();
		
		if(keyboard.isKeyPressed(upKey))
		{
			directions.add(TOP);
		}
		
		if(keyboard.isKeyPressed(downKey))
		{
			directions.add(BOTTOM);
		}
		
		if(keyboard.isKeyPressed(leftKey))
		{
			directions.add(LEFT);
		}
		
		if(keyboard.isKeyPressed(rightKey))
		{
			directions.add(RIGHT);
		}
		
		if(!directions.isEmpty())
		{
			move(directions);
		}
	}
	
	//Because this is a controllable object, it should never be disposed of.
	@Override
	public boolean isFinished()
	{
		return false;
	}
}
