package apcs.katechon.rendering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Rotation;

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
		int width = image.getWidth();
		int height = image.getHeight();
		
		WritableRaster raster = image.getRaster();
		
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				int[] pixels = raster.getPixel(x, y, (int[]) null);
				int r = pixels[0];
				int g = pixels[1];
				int b = pixels[2];
				if(r == from.getRed() && g == from.getGreen() && b == from.getBlue())
				{
					pixels[0] = to.getRed();
					pixels[1] = to.getGreen();
					pixels[2] = to.getBlue();
					raster.setPixel(x, y, pixels);
				}
			}
		}
		
		return image;
	}
	
	/**
	 * Copies an image. This is a deep copy so you won't just be copying an image,
	 * 		but rather creating an entirely new image instance that can be modified without affecting the origional.
	 * @param source
	 * @return
	 */
	public static BufferedImage deepCopy(BufferedImage source)
	{
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	/**
	 * Rotates an image. Doesn't work well for degrees other than 90 and 270
	 * @param bi The image to rotate
	 * @param radians The amount of radians the image should be rotated
	 * @return The rotated image
	 */
	//TODO: get a better rotateImage method
	public static BufferedImage rotateImage(BufferedImage bi, Rotation rotation)
	{
//		AffineTransform transform = new AffineTransform();
//		transform.rotate(radians, bi.getWidth() / 2, bi.getHeight() / 2);
//		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
//		return op.filter(bi, null);
		return Scalr.rotate(bi, rotation);
	}
}
