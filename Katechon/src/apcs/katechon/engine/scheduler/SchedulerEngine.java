package apcs.katechon.engine.scheduler;

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
		for(SchedulerTask task : items)
		{
			task.doTask();
		}
	}
}
