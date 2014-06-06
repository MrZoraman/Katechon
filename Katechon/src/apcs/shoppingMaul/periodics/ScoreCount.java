package apcs.shoppingMaul.periodics;

import apcs.katechon.engine.scheduler.ISchedulerTask;

public class ScoreCount implements ISchedulerTask
{
	public ScoreCount()
	{
		this.score = 9000;
		this.running = false;
	}
	
	private int score;
	
	private boolean running;

	@Override
	public void doTask()
	{
		if (this.running)
		{
			this.score--;
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
	
	public int getScore()
	{
		return score;
	}

	@Override
	public boolean isFinished() {
		return false;
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(score);
	}

	@Override
	public void setFinished(boolean finished)
	{
		// TODO Auto-generated method stub
		
	}
}
