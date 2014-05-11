package apcs.katechon.fileManagement;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

public class Directory
{
	private Path path;
	
	/**
	 * Creates a new directory in the current working directory
	 * @param filePath The path of the directory
	 */
	public Directory(String filePath)
	{
		this.path = Paths.get(FileSystems.getDefault().toString(), filePath);
		
		try
		{
			path = Files.createDirectories(path, PosixFilePermissions.asFileAttribute(Files.getPosixFilePermissions(path, LinkOption.values())));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
