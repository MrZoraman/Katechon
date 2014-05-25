package apcs.shoppingMaul;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.resources.SpritesheetLoader;
import apcs.katechon.test.SimpleCollidable;

public class SnowLeopard extends SimpleCollidable
{
	public SnowLeopard(int x, int y, int width, int height, int speed, boolean control)
	{
		super(x, y, width, height, speed, control);
		
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
}
