package apcs.katechon.logging;

import apcs.katechon.fileManagement.File;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * A logger that will save the log to a file.
 * @author Sean
 *
 */
public class FileLogger implements ILogger
{
	private boolean debugging;
	private File logFile;
	
	private ArrayList<String> lines;
	
	/**
	 * Create a new FileLogger
	 * @param fileName The file name including any parent directories. Not including the absolute path of the current working directory.
	 */
	public FileLogger(String fileName)
	{
		logFile = new File(fileName);
		lines = new ArrayList<String>();
		debugging = false;
	}

	@Override
	public void info(String message)
	{
		System.out.println("[INFO] " + message);
		
		Date date= new Date();
		lines.add(new Timestamp(date.getTime()) + ": [INFO] " + message);
		
	}

	@Override
	public void error(String message)
	{
		System.out.println("[ERROR] " + message);
		
		Date date= new Date();
		lines.add(new Timestamp(date.getTime()) + ": [ERROR] " + message);
		
	}

	@Override
	public void fatal(String message)
	{
		System.out.println("[FATAL] " + message);

		Date date= new Date();
		lines.add(new Timestamp(date.getTime()) + ": [FATAL] " + message);
		
	}

	@Override
	public void exception(Exception ex)
	{
		ex.printStackTrace();
		
		Date date= new Date();
		lines.add(new Timestamp(date.getTime()) + ": [FATAL] " + ex.getMessage());
		
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

			Date date= new Date();
			lines.add(new Timestamp(date.getTime()) + ": [DEBUG] " + message);
			
		}
		
	}
	
	@Override
	public void saveLog()
	{
		this.logFile.writeLines(lines);
	}
}
