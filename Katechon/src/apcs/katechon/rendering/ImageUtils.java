package apcs.katechon.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Contains various utility methods for buffered image manipulation
 * @author Matt
 *
 */
public class ImageUtils
{
	private ImageUtils() {}
	
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
}
