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
		
		//load the frames
		SpritesheetLoader loader = new SpritesheetLoader(ShoppingMaul.class, "/apcs/shoppingMaul/assets/snowleopard.png", 8, 1);
		BufferedImage[] imageFrames = loader.loadWide(0, 0, 7);
		this.frames = new AnimatedSequence<BufferedImage>(imageFrames, 1);
	}
	
	private final AnimatedSequence<BufferedImage> frames;
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(frames.getCurrentFrame(), x, y, null);
	}
	
	public void relocate(SnowLeopard leader)
	{
		if (!control)
		{
			if ((Math.abs(this.x - leader.x) < 50) && (Math.abs(this.y - leader.y) < 50))
			{
				this.x = leader.x + xOffset;
				this.y = leader.y + yOffset;
			}
		}
	}
}
