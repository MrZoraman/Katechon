package apcs.shoppingMaul;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.scheduler.ISchedulerTask;

//TODO: documentation
public class LeopardPack implements ISchedulerTask
{
	private static final int TICKS_BETWEEN_UPDATE = 40;
	
	private final List<SnowLeopard> leopards;
	private final Random rand;
	private final int speed;
	private final int layer;
	
	private final int OFFSET_TOLERANCE = 300;
	
	private int ticksSinceTaskUpdate;
	private int x;
	private int y;
	
	public LeopardPack(ControlScheme controlScheme, int x, int y, int speed, int size, int layer)
	{
		this.rand = new Random();
		this.speed = speed;
		this.layer = layer;
		
		this.x = x;
		this.y = y;
		this.ticksSinceTaskUpdate = 0;
		
		leopards = new ArrayList<SnowLeopard>(size);
		
		addLeopards(size);
	}
	
	public void addLeopards(int amount)
	{
		for(int ii = 0; ii < amount; ii++)
		{
			SnowLeopard leopard = new SnowLeopard(x, y, speed / 4);
			leopards.add(leopard);
			KatechonEngine.getInstance().addDrawable(leopard, layer);
			EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(leopard);
		}
	}
	
	public int removeLeopards(int amount)
	{
		int amountRemoved = 0;
		
		for(int ii = 0; ii < amount; ii++)
		{
			if(size() <= 0)
			{
				return amountRemoved;
			}
			
			Iterator<SnowLeopard> it = leopards.iterator();
			SnowLeopard leopard = it.next();
			it.remove();
			leopard.setFinished(true);
			amountRemoved++;
		}
		
		return amountRemoved;
	}
	
	public int size()
	{
		return leopards.size();
	}
	
	public void setDirection(Direction direction)
	{
		for(SnowLeopard leopard : leopards)
		{
			leopard.setDirection(direction);
		}
	}
	
	public void setDestinationForLeopards()
	{
		if(ticksSinceTaskUpdate > TICKS_BETWEEN_UPDATE)
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
	public void doTask()
	{
		setDestinationForLeopards();
	}

	private boolean isFinished = false;
	
	@Override
	public boolean isFinished()
	{
		return isFinished;
	}

	@Override
	public void setFinished(boolean finished)
	{
		this.isFinished = finished;		
	}
}
