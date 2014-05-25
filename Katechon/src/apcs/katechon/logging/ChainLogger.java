package apcs.katechon.logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static apcs.katechon.logging.Log.*;

public class ChainLogger
{
	private final List<String> messages;
	private final boolean debug;
	
	ChainLogger(boolean debug)
	{
		this.messages = new ArrayList<String>();
		this.debug = debug;
	}
	
	void log(ILogger logger)
	{
		logger.chainLog(messages.toArray(new String[messages.size()]));
	}
	
	public void info(String message)
	{
		messages.add(toInfoMessage(message));
	}
	
	public void error(String message)
	{
		messages.add(toErrorMessage(message));
	}
	
	public void fatal(String message)
	{
		messages.add(toFatalMessage(message));
	}
	
	public void exception(Exception ex)
	{
		messages.addAll(Arrays.asList(toExceptionMessage(ex)));
	}
	
	public void debug(String message)
	{
		if(debug)
		{
			messages.add(toDebugMessage(message));
		}
	}
}
