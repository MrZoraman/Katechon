package apcs.katechon.rendering;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Rotation;

import apcs.katechon.logging.Log;

/**
 * Contains various utility methods for buffered image manipulation
 * @author Matt
 *
 */
public class ImageUtils
{
	/**
	 * Private ctor
	 */
	private ImageUtils() {}
	
	/**
	 * Changes all of the colors of a buffered image from one color to antoher
	 * @param image The image to have it's colors altered
	 * @param from The color that will be changed
	 * @param to The color that the color specified in 'from' will be changed to
	 * @return The newly colored buffered image
	 */
	public static BufferedImage changeColor(BufferedImage image, Color from, Color to)
	{
		Log.info("going from " + from + " to " + to);
		int colorToChange = (from.getAlpha() << 24) | (from.getRed() << 16) | (from.getGreen() << 8) | from.getBlue();
		System.out.println("alpha: " + to.getAlpha());
		System.out.println("red: " + to.getRed());
		System.out.println("green: " + to.getGreen());
		System.out.println("blue: " + to.getBlue());
		int newColor = (to.getAlpha() << 24) | (to.getRed() << 16) | (to.getGreen() << 8) | to.getBlue();
		
		for(int x = 0; x < image.getWidth(); x++)
		{
			for(int y = 0; y < image.getHeight(); y++)
			{
				int currentColor = image.getRGB(x, y);
				if(currentColor == colorToChange)
				{
					System.out.println("changing color from " + currentColor);
					System.out.println("(should be going to " + newColor);
					image.setRGB(x, y, newColor);
					System.out.println("change!");
//					System.out.println("color changed.");
					System.out.println("... to " + image.getRGB(x, y));
				}
			}
		}
		
		System.out.println(Color.RED.getRGB());
		
		return image;
		
//		Log.info("chanign colfaso");
//		BufferedImage newVersion = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = (Graphics2D) newVersion.getGraphics();
//		g.setColor(to);
//		g.fillRect(0, 0, image.getWidth(), image.getHeight());
//		
//		g.setComposite(AlphaComposite.DstIn);
//		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
//		g.dispose();
//		return newVersion;
		
//		int width = image.getWidth();
//		int height = image.getHeight();
//		
//		WritableRaster raster = image.getRaster();
//		
//		for(int x = 0; x < width; x++)
//		{
//			for(int y = 0; y < height; y++)
//			{
//				int[] pixels = raster.getPixel(x, y, (int[]) null);
//				int r = pixels[0];
//				int g = pixels[1];
//				int b = pixels[2];
//				if(r == from.getRed() && g == from.getGreen() && b == from.getBlue())
//				{
//					pixels[0] = to.getRed();
//					pixels[1] = to.getGreen();
//					pixels[2] = to.getBlue();
//					raster.setPixel(x, y, pixels);
//				}
//			}
//		}
//		
//		return image;
	}
	
	/**
	 * Copies an image. This is a deep copy so you won't just be copying an image,
	 * 		but rather creating an entirely new image instance that can be modified without affecting the origional.
	 * @param source
	 * @return
	 */
	public static BufferedImage deepCopy(BufferedImage source)
	{
		BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = copy.createGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		
		return copy;
	}
	
	/**
	 * Rotates an image. Doesn't work well for degrees other than 90 and 270
	 * @param bi The image to rotate
	 * @param radians The amount of radians the image should be rotated
	 * @return The rotated image
	 */
	public static BufferedImage rotateImage(BufferedImage bi, Rotation rotation)
	{
		return Scalr.rotate(bi, rotation);
	}
}
