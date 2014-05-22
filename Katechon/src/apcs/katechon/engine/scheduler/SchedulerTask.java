package apcs.katechon.engine.scheduler;

/**
 * Represents a task to be plugged into the scheduler
 * @author Matt
 *
 */
public interface SchedulerTask
{
	/**
	 * This method will contain the work to do
	 * @return True if the task is finished, and therefore can be removed from the scheduler, or false if the task needs to run again on the next game tick.
	 */
	public boolean doTask();
}
