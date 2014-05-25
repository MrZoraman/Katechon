package apcs.katechon.logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static apcs.katechon.logging.Log.*;

//TODO: documentation
public class ChainLogger
{
	private final List<String> messages;
	private final boolean debug;
	private final Set<ILogger> loggers;
	
	ChainLogger(Set<ILogger> loggers, boolean debug)
	{
		this.loggers = loggers;
		this.messages = new ArrayList<String>();
		this.debug = debug;
	}
	
	public void log()
	{
		for(ILogger logger : loggers)
		{
			logger.chainLog(messages.toArray(new String[messages.size()]));
		}
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
