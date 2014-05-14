package apcs.katechon.periodic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Holds IPeriodic items and controls their ticking.
 * @author Matt
 */
public class PeriodicTicker implements ActionListener
{
	/**
	 * Creates a new PeriodicTicker to hold IPeriodic objects.
	 */
	public PeriodicTicker()
	{
		periodicItems = new HashSet<IPeriodic>();
	}
	
	private Set<IPeriodic> periodicItems;
	
	/**
	 * Adds an IPeriodic object to this Ticker's agenda.
	 * @param item
	 */
	public void addItem(IPeriodic item)
	{
		periodicItems.add(item);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		for(IPeriodic item : periodicItems)
		{
			item.onTick();
		}
	}
}
