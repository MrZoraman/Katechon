package apcs.katechon.logging;

import java.io.PrintStream;

/**
 * Logs info to the java console using System.out.println and System.err.println. Log file is not saved.
 * @author Matt
 *
 */
public class PrintstreamLogger implements ILogger
{
	private boolean debugging;
	
	/**
	 * Creates a new PrintlnLogger
	 */
	public PrintstreamLogger(PrintStream stream)
	{
		debugging = false;
		this.out = stream;
	}
	
	private final PrintStream out;
	
	@Override
	public void info(String message)
	{
		out.println("[INFO] " + message);
	}

	@Override
	public void error(String message)
	{
		out.println("[ERROR] " + message);
	}

	@Override
	public void fatal(String message)
	{
		out.println("[FATAL] " + message);
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
			out.println("[DEBUG] " + message);
		}
	}
}
