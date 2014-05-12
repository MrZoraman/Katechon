package apcs.katechon.logging;

/**
 * Logs info to the java console using System.out.println and System.err.println. Log file is not saved.
 * @author Matt
 *
 */
public class PrintlnLogger implements ILogger
{
	private boolean debugging;
	
	public PrintlnLogger()
	{
		debugging = false;
	}
	
	@Override
	public void info(String message)
	{
		System.out.println("[INFO] " + message);
	}

	@Override
	public void error(String message)
	{
		System.out.println("[ERROR] " + message);
	}

	@Override
	public void fatal(String message)
	{
		System.out.println("[FATAL] " + message);
	}

	@Override
	public void exception(Exception ex)
	{
		//TODO: there is a better, cleaner way to do this that doesn't involve forcing the jvm to juggle multiple streams (doesn't really matter for the println logger though)
		ex.printStackTrace();
	}

	@Override
	public void setDebugging(boolean debugging)
	{
		this.debugging = debugging;
	}

	@Override
	public void debug(String message)
	{
		if(debugging)
		{
			System.out.println("[DEBUG] " + message);
		}
	}
}
