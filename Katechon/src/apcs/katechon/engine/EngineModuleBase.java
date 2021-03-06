package apcs.katechon.engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents some module that can be plugged into Katechon to change his behavior
 * @author Matt
 *
 * @param <I> The type of object this engine will deal with
 */
public abstract class EngineModuleBase<I extends IEngineItem>
{
	private final Set<I> items;
	
	private final Class<I> type;
	
	public EngineModuleBase(Class<I> type)
	{
		this.items = new HashSet<I>();
		this.type = type;
	}
	
	public Class<I> getEngineType()
	{
		return this.type;
	}
	
	/**
	 * Adds an item to the engine for it to keep track of and process
	 * @param item The item to add
	 */
	public void addItem(final I item)
	{
		items.add(item);
	}
	
	/**
	 * Removes an item from the engine
	 * @param item The item to remove
	 */
	public final void removeItem(final I item)
	{
		if(items.contains(item))
		{
			items.remove(item);
		}
	}
	
	/**
	 * Processes the items
	 */
	public final void doWork()
	{
		process(items);
		
		Iterator<I> it = items.iterator();
		
		while(it.hasNext())
		{
			I item = it.next();
			if(item.isFinished())
			{
				it.remove();
			}
		}
	}
	
	/**
	 * This is where the magic happens
	 * @param items The items involved in the process
	 */
	protected abstract void process(final Set<I> items);
}
