package apcs.katechon.logging;

import apcs.katechon.fileManagement.File;
import java.util.ArrayList;

public class FileLogger implements ILogger
{
	private boolean debugging;
	private File logFile;
	
	public FileLogger(String fileName)
	{
		logFile = new File(fileName);
		debugging = false;
	}

	@Override
	public void info(String message)
	{
		System.out.println("[INFO] " + message);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("[INFO] " + message);
		
		logFile.writeLines(lines);
	}

	@Override
	public void error(String message)
	{
		System.out.println("[ERROR] " + message);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("[ERROR] " + message);
		
		logFile.writeLines(lines);
	}

	@Override
	public void fatal(String message)
	{
		System.out.println("[FATAL] " + message);
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("[FATAL] " + message);
		
		logFile.writeLines(lines);
	}

	@Override
	public void exception(Exception ex)
	{
		ex.printStackTrace();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("[FATAL] " + ex.getMessage());
		
		logFile.writeLines(lines);
	}

	@Override
	public void setDebugging(boolean debugging)
	{
		this.debugging = debugging;
		
	}

	@Override
	public void debug(String message)
	{
		if (debugging)
		{
			System.out.println("[DEBUG] " + message);
			ArrayList<String> lines = new ArrayList<String>();
			lines.add("[DEBUG] " + message);
			
			logFile.writeLines(lines);
		}
		
	}
	
}
