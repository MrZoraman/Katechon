package apcs.shoppingMaul;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.resources.SpritesheetLoader;

public class SnowLeopard implements IDrawable
{
	private final AnimatedSequence<BufferedImage> frames;
	
	private boolean finished;
	
	private int x;
	private int y;
	
	public SnowLeopard(int x, int y)
	{
		this.finished = false;
		
		this.x = x;
		this.y = y;
		
		//load the frames
		SpritesheetLoader loader = new SpritesheetLoader(ShoppingMaul.class, "/apcs/shoppingMaul/assets/snowleopard.png", 8, 1);
		BufferedImage[] imageFrames = loader.loadWide(0, 0, 7);
		this.frames = new AnimatedSequence<BufferedImage>(imageFrames, 1);
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(frames.getCurrentFrame(), x, y, null);
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}
	
	/**
	 * Sets the state of this snow leopard.
	 * @param finished True if the leopard is done and can be removed from the game engine. False if not done yet.
	 */
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
}
