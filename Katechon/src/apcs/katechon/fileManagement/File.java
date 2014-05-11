package apcs.katechon.fileManagement;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;

public class File
{
	private Path path;
	private Charset cs;
	
	/**
	 * Creates a new file in the current working directory.
	 * @param fileName The name of the file.
	 */
	public File(String fileName) //throws FileAlreadyExistsException, IOException, UnsupportedOperationException, SecurityException
	{
		this.path = Paths.get(FileSystems.getDefault().toString(), fileName);
				
		try
		{
			path = Files.createFile(path, PosixFilePermissions.asFileAttribute(Files.getPosixFilePermissions(path, LinkOption.values())));
		} catch (IOException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
		}
		
		return lines;
	}
	
	/**
	 * Writes all lines to this file.
	 * @param lines A List<String> to write to this file
	 */
	public void writeLines(List<String> lines)
	{
		try
		{
			path = Files.write(path, lines, cs, StandardOpenOption.values());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
