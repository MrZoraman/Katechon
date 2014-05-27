package apcs.shoppingMaul;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.basicGameObjects.ControllableCollidable;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.scheduler.ISchedulerTask;

//TODO: documentation
public class LeopardPack extends ControllableCollidable
{
	private static final int TICKS_BETWEEN_UPDATE = 40;
	
	private final List<SnowLeopard> leopards;
	private final Random rand;
	private final int speed;
	
	private final int OFFSET_TOLERANCE = 300;
	
	private int ticksSinceTaskUpdate;
	
	public LeopardPack(ControlScheme controlScheme, int x, int y, int speed, int size)
	{
		super(controlScheme, x, y, 10, 10, speed);
		
		this.rand = new Random();
		this.speed = speed;
		
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
			KatechonEngine.getInstance().addDrawable(leopard, 1);
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
	
	@Override
	public void move(Set<Direction> directions)
	{
		super.move(directions);
		
		setDestinationForLeopards();
		
		if(!directions.isEmpty())
		{
			Direction direction = directions.iterator().next();
			for(SnowLeopard leopard : leopards)
			{
				leopard.setDirection(direction);
			}
		}
	}
	
	private void setDestinationForLeopards()
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
		super.doTask();
		
		setDestinationForLeopards();
	}
	
	@Override
	public void onCollision(Set<Direction> collisions)
	{
		super.onCollision(collisions);
		
		//TODO: this is where we kill the target
	}
}
