package apcs.katechon.engine.scheduler;

import java.util.HashSet;
import java.util.Set;

import apcs.katechon.engine.EngineModuleBase;

/**
 * This is the scheduler. It is used to schedule tasks to be done in the future.
 * @author Matt
 *
 */
public class SchedulerEngine extends EngineModuleBase<ISchedulerTask>
{
	/**
	 * Constructor
	 */
	public SchedulerEngine()
	{
		super(ISchedulerTask.class);
	}

	@Override
	protected void process(Set<ISchedulerTask> items)
	{
		Set<ISchedulerTask> tasks = new HashSet<ISchedulerTask>(items);
		
		for(ISchedulerTask task : tasks)
		{
			task.doTask();
		}
	}
}
