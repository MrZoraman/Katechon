package apcs.shoppingMaul.periodics;

import java.text.DecimalFormat;

import apcs.katechon.engine.scheduler.ISchedulerTask;

/**
 * Keeps score by way of time taken.
 * @author Sean
 */
public class TimeScore implements ISchedulerTask
{
	private final double conversionRate = 1000000000.0;
	
	public TimeScore()
	{
		this.time = 0;
		this.first = System.nanoTime();
		this.running = false;
	}
	
	private long time;
	private long first;
	
	private boolean running;

	@Override
	public void doTask()
	{
		if (this.running)
		{
			this.time = System.nanoTime() - first;
		}
	}
	
	/**
	 * Begins tracking time.
	 */
	public void start()
	{
		this.first = System.nanoTime();
		this.running = true;
	}
	
	/**
	 * Stops tracking time.
	 */
	public void stop()
	{
		this.running = false;
	}

	@Override
	public void setFinished(boolean finished)
	{
		this.isFinished = finished;
	}
	
	private boolean isFinished = false;
	
	@Override
	public boolean isFinished() {
		return isFinished;
	}
	
	@Override
	public String toString()
	{
		double temp = time / conversionRate;
		return (new DecimalFormat("#.##")).format(temp);
	}
	
	/**
	 * Resets the time.
	 */
	public void reset()
	{
		this.first = System.nanoTime();
	}
	
	/**
	 * Imposes a penalty.
	 * @param seconds The number of seconds to penalize the player.
	 */
	public void timePenalty(double seconds)
	{
		first -= (seconds * conversionRate);
	}
}
