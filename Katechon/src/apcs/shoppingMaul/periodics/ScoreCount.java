package apcs.shoppingMaul.periodics;

import apcs.katechon.engine.scheduler.ISchedulerTask;

public class ScoreCount implements ISchedulerTask
{
	public ScoreCount()
	{
		this.score = 9000;
	}
	
	private int score;

	@Override
	public void doTask()
	{
		this.score--;
	}
	
	public int getScore()
	{
		return score;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
