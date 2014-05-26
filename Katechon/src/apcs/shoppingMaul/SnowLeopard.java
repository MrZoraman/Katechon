package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import apcs.katechon.rendering.ImageUtils;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.resources.SpritesheetLoader;
import apcs.katechon.test.SimpleCollidable;

public class SnowLeopard extends SimpleCollidable
{
	private final boolean control;
	
	private int xOffset;
	private int yOffset;
	
	public SnowLeopard(int x, int y, int width, int height, int speed, boolean control)
	{
		super(x, y, width, height, speed, control);
		
		this.control = control;
		
		if (!control)
		{
			Random r = new Random();
			xOffset = (r.nextInt(20) * (r.nextBoolean() ? 1 : -1));
			yOffset = (r.nextInt(20) * (r.nextBoolean() ? 1 : -1));
		}
		
		SpritesheetLoader loader = new SpritesheetLoader(ShoppingMaul.class, "spritesheet_bigger.png", 8, 1);
		BufferedImage[] imageFrames = loader.loadWide(0, 0, 7);
		
		for(BufferedImage image : imageFrames)
		{
			image = ImageUtils.changeColor(image, new Color(183, 183, 183), Color.RED);
		}
		
		this.frames = new AnimatedSequence<BufferedImage>(imageFrames, 1);
	}
	
	private final AnimatedSequence<BufferedImage> frames;
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(frames.getCurrentFrame(), x, y, null);
	}
	
	@Override
	public void doTask()
	{
		if (!control)
		{
			if ((Math.abs(this.x - ShoppingMaul.getMainLeopard().x) < 50) && (Math.abs(this.y - ShoppingMaul.getMainLeopard().y) < 50))
			{
				this.x = ShoppingMaul.getMainLeopard().x + xOffset;
				this.y = ShoppingMaul.getMainLeopard().y + yOffset;
			}
		}
		super.doTask();
	}
}
