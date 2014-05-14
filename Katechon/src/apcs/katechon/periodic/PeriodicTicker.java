package apcs.katechon.periodic;

import java.util.HashSet;
import java.util.Set;

public class PeriodicTicker
{
	public PeriodicTicker()
	{
		periodicItems = new HashSet<IPeriodic>();
	}
	
	private Set<IPeriodic> periodicItems;
	
	public void addItem(IPeriodic item)
	{
		periodicItems.add(item);
	}
	
	public void tick()
	{
		for(IPeriodic item : periodicItems)
		{
			item.onTick();
		}
	}
}
