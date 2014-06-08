package apcs.katechon.fileManagement;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import apcs.katechon.logging.Log;

/**
 * A way to store information as text in a file.
 * @author Sean
 *
 */
public class File
{
	private Path path;
	private Path dir;
	private Charset cs;
	
	/**
	 * Creates a new file, and any required directories, in the current working directory.
	 * @param filePath The path to the file, including the file name. (Not including the path to the current working directory)
	 */
	public File(String filePath)
	{
		this.path = Paths.get(System.getProperty("user.dir"), filePath);
		this.dir = path.subpath(path.getNameCount() - 2, path.getNameCount() - 1);
		
		
		this.cs = Charset.defaultCharset();
		try
		{
			if (Files.notExists(dir))
			{
				Files.createDirectories(dir);
			}
			if (Files.notExists(path))
				path = Files.createFile(path);
		} catch (IOException e)
		{
			Log.exception(e);
		}
	}
	
	/**
	 * Reads all lines from this file.
	 * @return A List<String> of the lines in the file. Null if there are no lines present.
	 */
	public List<String> readLines()
	{
		List<String> lines = null;
		try
		{
			lines = Files.readAllLines(path, cs);
		} catch (IOException e)
		{
			Log.exception(e);
		}
		
		return lines;
	}
	
	/**
	 * Writes all lines to this file.
	 * @param lines A List(String) to write to this file
	 */
	public void writeLines(List<String> lines)
	{
		ArrayList<String> temp = new ArrayList<String>();
		try
		{
			temp.addAll(this.readLines());
			temp.addAll(lines);
			path = Files.write(path, temp, cs);
		} catch (IOException e)
		{
			Log.exception(e);
		}
	}
}
