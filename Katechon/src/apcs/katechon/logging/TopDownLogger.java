package apcs.katechon.logging;

/**
 * A logger that prints it's messages from the top down. It prints the first message, then the second message and so on
 * @author Matt
 *
 */
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
