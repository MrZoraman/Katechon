package apcs.katechon.engine.scheduler;

import java.util.Iterator;
import java.util.Set;

import apcs.katechon.engine.EngineModuleBase;

public class SchedulerEngine extends EngineModuleBase<SchedulerTask>
{
	public SchedulerEngine(Class<SchedulerTask> type) {
		super(type);
	}

	@Override
	protected void process(Set<SchedulerTask> items) {
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
