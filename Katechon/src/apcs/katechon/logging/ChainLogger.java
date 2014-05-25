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
	
	public ChainLogger info(String message)
	{
		messages.add(toInfoMessage(message));
		return this;
	}
	
	public ChainLogger error(String message)
	{
		messages.add(toErrorMessage(message));
		return this;
	}
	
	public ChainLogger fatal(String message)
	{
		messages.add(toFatalMessage(message));
		return this;
	}
	
	public ChainLogger exception(Exception ex)
	{
		messages.addAll(Arrays.asList(toExceptionMessage(ex)));
		return this;
	}
	
	public ChainLogger debug(String message)
	{
		if(debug)
		{
			messages.add(toDebugMessage(message));
		}
		
		return this;
	}
}
