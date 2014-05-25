package apcs.katechon.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


/**
 * Loads an image given a jar file. Images are cached for later retrieval, so they are only read from the disk once.
 * @author Matt
 *
 */
public class JarImageLoader
{
	private static Map<Class<?>, JarImageLoader> instances;
	
	/**
	 * Gets an instance of the JarImageLoader given a class to load the image from
	 * @param clazz The class that the jar image loader will get the resource from
	 * @return An instance of the JarImageLoader
	 */
	public static synchronized JarImageLoader getInstance(Class<?> clazz) 
	{
		if(instances == null)
			instances = new HashMap<Class<?>, JarImageLoader>();
		
		if(instances.containsKey(clazz) == false)
		{
			instances.put(clazz, new JarImageLoader(clazz));
		}
		
		return instances.get(clazz);
	}
	
	/**
	 * Constructor
	 * @param clazz The class to add to the index
	 */
	private JarImageLoader(Class<?> clazz) 
	{
		this.clazz = clazz;
		images = new HashMap<String, BufferedImage>();
	}
	
	private Map<String, BufferedImage> images;
	private final Class<?> clazz;
	
	/**
	 * Reads a buffered image
	 * @param imageName The path to the image
	 * @return The BufferedImage at the path given in imageName
	 */
	public BufferedImage getImage(String imageName) 
	{
		if(images.containsKey(imageName)) 
		{
			return images.get(imageName);
		}
		else 
		{
			BufferedImage image = null;
			try
			{
				image = ImageIO.read(clazz.getResource(imageName));
			}
			catch (IOException e)
			{
				System.out.println("Image not found: " + imageName);
			}
			finally
			{
				images.put(imageName, image);
			}
			
			return image;
		}
	}
}
