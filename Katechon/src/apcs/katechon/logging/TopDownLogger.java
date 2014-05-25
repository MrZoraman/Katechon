package apcs.katechon.logging;

public abstract class TopDownLogger implements ILogger
{
	@Override
	public void chainLog(String[] messages)
	{
		for(int ii = 0; ii < messages.length; ii++)
		{
			this.log(messages[ii]);
		}
	}
}
