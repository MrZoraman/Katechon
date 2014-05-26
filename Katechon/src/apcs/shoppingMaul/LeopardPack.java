package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.Random;
import java.util.Set;

import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.basicGameObjects.ControllableCollidable;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.rendering.IDrawable;

public class LeopardPack extends ControllableCollidable implements IDrawable
{
	private final SnowLeopard[] leopards;
	private final Random rand;
	
	private final int OFFSET_TOLERANCE = 200;
	
	private int ticksSinceTaskUpdate;
	
	public LeopardPack(ControlScheme controlScheme, int x, int y, int speed, int size)
	{
		super(controlScheme, x, y, 10, 10, speed);
		
		this.rand = new Random();
		this.ticksSinceTaskUpdate = 0;
		
		leopards = new SnowLeopard[size];
		
		for(int ii = 0; ii < leopards.length; ii++)
		{
			leopards[ii] = new SnowLeopard(x, y, speed);
			KatechonEngine.getInstance().addDrawable(leopards[ii], 1);
			EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(leopards[ii]);
		}
	}

	@Override
	public void draw(Graphics g)
	{
		g.drawRect(x, y, width, height);
	}
	
	@Override
	public void doTask()
	{
		super.doTask();
		
		if(ticksSinceTaskUpdate > 10)
		{
			for(SnowLeopard leopard : leopards)
			{
				int xOffset = (-OFFSET_TOLERANCE / 2) + rand.nextInt(OFFSET_TOLERANCE);
				int yOffset = (-OFFSET_TOLERANCE / 2) + rand.nextInt(OFFSET_TOLERANCE);
				
				leopard.setDestination(x + xOffset, y + yOffset);
			}
			
			ticksSinceTaskUpdate = 0;
		}
		else
		{
			ticksSinceTaskUpdate++;
		}
	}
	
	@Override
	public void onCollision(Set<Direction> collisions)
	{
		super.onCollision(collisions);
		
		//this is where we kill the target
	}
}
