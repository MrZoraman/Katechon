package apcs.katechon.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

/**
 * Prints the log to a file.
 * @author Sean
 *
 */
public class FileLogger extends TopDownLogger
{
	/**
	 * Constructor
	 * @param fileName The name of the file to save the log data to. If the file doesn't exist, the FileLogger will try to create it.
	 * @throws IOException If something goes wrong while writing to/creating the file
	 */
	public FileLogger(String fileName) throws IOException
	{
		File logFile = new File(fileName);
		if(!logFile.exists())
		{
			//Creates any directories needed
			Files.createDirectories(logFile.getAbsoluteFile().toPath().getParent());
			
			logFile.createNewFile();
		}
		
		fileWriter = new FileWriter(logFile, true);
		bufferedWriter = new BufferedWriter(fileWriter);
		
		writer = new PrintWriter(bufferedWriter);
	}
	
	private final FileWriter fileWriter;
	private final BufferedWriter bufferedWriter;
	
	private final PrintWriter writer;
	
	@Override
	public void saveLog()
	{
		Log.info("Saving log...");
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
		writer.println(message);
		writer.flush();
	}
	
	@Override
	protected void finalize() throws Throwable
	{
		try
		{
			writer.close();
			bufferedWriter.close();
			fileWriter.close();
		}
		finally
		{
			super.finalize();
		}
	}
}
