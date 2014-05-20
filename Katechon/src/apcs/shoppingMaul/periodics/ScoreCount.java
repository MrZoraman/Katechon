package apcs.shoppingMaul.periodics;

import apcs.katechon.periodic.IPeriodic;

public class ScoreCount implements IPeriodic
{
	public ScoreCount()
	{
		this.score = 9000;
	}
	
	private int score;

	@Override
	public void onTick()
	{
		this.score--;
	}
	
	public int getScore()
	{
		return score;
	}
}
