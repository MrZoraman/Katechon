package apcs.shoppingMaul;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

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
		
		SpritesheetLoader loader = new SpritesheetLoader(ShoppingMaul.class, "snowleopard.png", 8, 1);
		BufferedImage[] imageFrames = loader.loadWide(0, 0, 7);
		
		this.frames = new AnimatedSequence<BufferedImage>(imageFrames, 2);
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
			this.x = ShoppingMaul.getMainLeopard().x + xOffset;
			this.y = ShoppingMaul.getMainLeopard().y + yOffset;
		}
		super.doTask();
	}
}
