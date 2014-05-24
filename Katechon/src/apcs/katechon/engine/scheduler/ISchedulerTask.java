package apcs.katechon.engine.scheduler;

import apcs.katechon.engine.IEngineItem;

/**
 * Represents a task to be plugged into the scheduler
 * @author Matt
 *
 */
//TODO: refactor to correct name
public interface ISchedulerTask extends IEngineItem
{
	/**
	 * This method will contain the work to do
	 */
	public void doTask();
}
