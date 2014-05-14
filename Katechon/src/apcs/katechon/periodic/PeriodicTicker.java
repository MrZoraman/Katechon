package apcs.katechon.periodic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class PeriodicTicker implements ActionListener
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
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		for(IPeriodic item : periodicItems)
		{
			item.onTick();
		}
	}
}
