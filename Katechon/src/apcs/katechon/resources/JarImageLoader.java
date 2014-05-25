package apcs.katechon.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class JarImageLoader
{
	private static Map<Class<?>, JarImageLoader> instances;
	
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
	
	private JarImageLoader(Class<?> clazz) 
	{
		this.clazz = clazz;
		images = new HashMap<String, BufferedImage>();
	}
	
	private Map<String, BufferedImage> images;
	private final Class<?> clazz;
	
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
