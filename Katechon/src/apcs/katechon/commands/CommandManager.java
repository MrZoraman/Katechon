package apcs.katechon.commands;

import apcs.katechon.commands.parser.Command;
import apcs.katechon.commands.parser.CommandSystem;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.IConsole;

public class CommandManager implements ISchedulerTask
{
	private static CommandManager instance = null;
	
	public static synchronized CommandManager getInstance()
	{
		if(instance == null)
		{
			instance = new CommandManager();
		}
		
		return instance;
	}
	
	private final CommandSystem commandSystem;
	
	private IConsole console;
	
	private CommandManager()
	{
		this.commandSystem = new CommandSystem();
		
		this.console = new EmptyConsole();
	}
	
	public void registerCommand(String cmdString, Command cmd)
	{
		commandSystem.registerCommand(cmdString, cmd);
	}
	
	public void initConsole(IConsole console)
	{
		this.console = console;
	}
	
	@Override
	public void doTask()
	{
		                 //I didn't mean to mimic C#!
		String command = console.readLine();
		
		if(command != null)
		{
			commandSystem.runCommand(command);
		}
	}
	
	@Override
	public boolean isFinished()
	{
		//console is never done
		return false;
	}
}
