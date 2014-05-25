package apcs.katechon.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This takes a single spritesheet and turns it into animation sequences as BufferedImage arrays.
 * When specifying coordinates, it is important to note that this class is zero based.
 * A limitation to this loader is that images must all be in a straight line on the spritesheet, either veritcally or horizontally.
 * @author Matt
 *
 */
public class SpritesheetLoader 
{
	/**
	 * Constructs a SpritesheetLoader
	 * @param file The spritesheet file (preferrably a .png file)
	 * @param imagesWide How many sub-images wide the spritesheet is
	 * @param imagesTall How many sub-images wide the spritesheet is
	 * @throws IOException If there is a problem with the file (from ImageIO.read(file))
	 */
	public SpritesheetLoader(File file, int imagesWide, int imagesTall) throws IOException
	{
		spriteSheet = ImageIO.read(file);
		this.imagesWide = imagesWide;
		this.imagesTall = imagesTall;
	}
	
	/**
	 * Loads an internal resource. See the documentation for the other constructor({@link #SpritesheetLoader(File, int, int)})
	 */
	public SpritesheetLoader(Class<?> resource, String name, int imagesWide, int imagesTall)
	{
		spriteSheet = JarImageLoader.getInstance(resource).getImage(name);
		this.imagesWide = imagesWide;
		this.imagesTall = imagesTall;
	}
	
	private final BufferedImage spriteSheet;
	private final int imagesWide;
	private final int imagesTall;
	
	/**
	 * Loads up an animation from the specified coordinate range
	 * Column, start and end points are <b>not</b> specified by pixels, but rather by images. If you said that your spritesheet was 8 images wide, then you'd reference a start point between 0 and 7
	 * @param column The column the animation is on
	 * @param startX Where the animation starts
	 * @param endX Where the animation ends
	 * @return The BufferedImage array
	 */
	public BufferedImage[] loadWide(int column, int startX, int endX)
	{
		BufferedImage[] images = new BufferedImage[imagesWide * imagesTall];
		
		int width = spriteSheet.getWidth();
		int height = spriteSheet.getHeight();
		
		int imageWidth = width / imagesWide;
		int imageHeight = height / imagesTall;
		
		int index = 0;
		for (int x = 0; x < imagesWide; x++)
		{
			images[index] = spriteSheet.getSubimage(x * imageWidth, column * imageHeight, imageWidth, imageHeight);
			
			index++;
		}
		
		return images;
	}
	
	/**
	 * Loads up an animation from the specified coordinate range
	 * Row, start and end points are <b>not</b> specified by pixels, but rather by images. If you said that your spritesheet was 8 images wide, then you'd reference a start point between 0 and 7
	 * @param row THe row the animation is on
	 * @param startY Where the animation starts
	 * @param endY Where the animation ends
	 * @return The BufferedImage array
	 */
	public BufferedImage[] loadTall(int row, int startY, int endY)
	{
		BufferedImage[] images = new BufferedImage[imagesWide * imagesTall];
		
		int width = spriteSheet.getWidth();
		int height = spriteSheet.getHeight();
		
		int imageWidth = width / imagesWide;
		int imageHeight = height / imagesTall;
		
		int index = 0;
		for (int y = 0; y < imagesTall; y++)
		{
			images[index] = spriteSheet.getSubimage(row * imageWidth, y * imageHeight, imageWidth, imageHeight);
			
			index++;
		}
		
		return images;
	}
	
	/**
	 * Pulls a single image off of the spritesheet
	 * x and y are <b>not</b> specified by pixels, but rather by images. If you said that your spritesheet was 8 images wide, then you'd reference a start point between 0 and 7
	 * @param x The x coordinate for the image
	 * @param y The y coordinate for the image
	 * @return The BufferedImage
	 */
	public BufferedImage load(int x, int y)
	{
		BufferedImage image = null;
		
		int width = spriteSheet.getWidth();
		int height = spriteSheet.getHeight();
		
		int imageWidth = width / imagesWide;
		int imageHeight = height / imagesTall;
		
		image = spriteSheet.getSubimage(x * imageWidth, y * imageHeight, imageWidth, imageHeight);
		
		return image;
	}
}
