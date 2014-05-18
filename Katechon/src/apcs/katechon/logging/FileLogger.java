package apcs.katechon.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints the log to a file.
 * @author Sean
 *
 */
public class FileLogger implements ILogger
{
	/**
	 * Constructor
	 * @param fileName The name of the file to save the log data to. If the file doesn't exist, the FileLogger will try to create it.
	 * @throws IOException If something goes wrong while writing to/creating the file
	 */
	public FileLogger(String fileName, boolean logToConsole) throws IOException
	{
		File logFile = new File(fileName);
		if(!logFile.exists())
		{
			logFile.createNewFile();
		}
		
		fileWriter = new FileWriter(logFile, true);
		bufferedWriter = new BufferedWriter(fileWriter);
		
		writer = new PrintWriter(bufferedWriter);
		this.logToConsole = logToConsole;
	}
	
	private final FileWriter fileWriter;
	private final BufferedWriter bufferedWriter;
	
	private final PrintWriter writer;
	private final boolean logToConsole;
	
	@Override
	public void saveLog()
	{
		System.out.println("saving log...");
		try {
			writer.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.err.println("Failed to save log file!");
			e.printStackTrace();
		}
	}

	@Override
	public void log(String message)
	{
		if(logToConsole)
		{
			System.out.println(message);
		}
		
		writer.println(message);
		writer.flush();
	}
}
