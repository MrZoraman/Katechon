package apcs.katechon.engine.scheduler;

import java.util.Iterator;
import java.util.Set;

import apcs.katechon.engine.EngineModuleBase;

/**
 * This is the scheduler. It is used to schedule tasks to be done in the future.
 * @author Matt
 *
 */
public class SchedulerEngine extends EngineModuleBase<SchedulerTask>
{
	/**
	 * Constructor
	 */
	public SchedulerEngine()
	{
		super(SchedulerTask.class);
	}

	@Override
	protected void process(Set<SchedulerTask> items)
	{
		Iterator<SchedulerTask> it = items.iterator();
		
		while(it.hasNext())
		{
			SchedulerTask task = it.next();
			
			boolean done = task.doTask();
			
			if(done)
			{
				it.remove();
			}
		}
	}
}
