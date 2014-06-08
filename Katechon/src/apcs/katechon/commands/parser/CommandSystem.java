package apcs.katechon.commands.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apcs.katechon.logging.Log;

public class CommandSystem
{
	public CommandSystem()
	{
		commands = new HashMap<String[], Command>();
	}
	
	private final Map<String[], Command> commands;
	
	public void registerCommand(final String cmdString, final Command cmd)
	{
		//make sure the cmdString isn't empty ("")
		if(cmdString.length() <= 0)
		{
			throw new IllegalArgumentException("cmdString cannot be empty!");
		}
		
		String[][] possibilities = CommandParser.splitCmdString(cmdString);
		for(int ii = 0; ii < possibilities.length; ii++)
		{
			commands.put(possibilities[ii], cmd);
		}
	}
	
	public void runCommand(String cmdMessage)
	{
		String[] parts = cmdMessage.split(" ");
//		List<String> preArgs = new ArrayList<String>();
		
		//Not an empty command
		if(parts.length > 0)
		{
			int bestMatchAmount = 0;
			String[] bestMatchString = null;
			
			for(String[] possibility : commands.keySet())
			{
				int matches = CommandParser.getMatchingArgs(possibility, parts);
				if(matches > bestMatchAmount)
				{
					bestMatchAmount = matches;
					bestMatchString = possibility;
				}
			}
			
			if(bestMatchAmount > 0)
			{
				List<String> preArgs = new ArrayList<String>();
				List<String> args = new ArrayList<String>();
				for(int ii = 0; ii < parts.length; ii++)
				{
					if(ii < bestMatchString.length)
					{
						if(bestMatchString[ii].equals("*"))
						{
							preArgs.add(parts[ii]);
						}
					}
					else
					{
						args.add(parts[ii]);
					}
				}
				
				commands.get(bestMatchString).onCommand(
						preArgs.toArray(new String[preArgs.size()]), 
						args.toArray(new String[args.size()]));
			}
			else
			{
				//command not found!
				Log.info("command not found!!!");
			}
		}
		else
		{
			//command cannot be empty!
			Log.error("command cnnot be empty!!!");
		}
	}
}
