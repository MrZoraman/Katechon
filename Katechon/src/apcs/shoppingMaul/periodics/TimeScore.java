package apcs.shoppingMaul.periodics;

import java.text.DecimalFormat;

import apcs.katechon.engine.scheduler.ISchedulerTask;

public class TimeScore implements ISchedulerTask
{
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
	
	public void start()
	{
		this.running = true;
	}
	
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
		double temp = time / 1000000000.0;
		return (new DecimalFormat("#.##")).format(temp);
	}
	
	public void reset()
	{
		this.first = System.nanoTime();
	}
}
