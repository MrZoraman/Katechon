package apcs.katechon.rendering.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import apcs.katechon.rendering.IDrawable;

public class AnimatedSprite implements IDrawable
{
	public AnimatedSprite(final BufferedImage[] frames, int ticksPerFrameUpdate, int x, int y)
	{
		this.frames = frames;
		this.ticksPerFrameUpdate = ticksPerFrameUpdate;
		
		this.currentFrameIndex = 0;
		this.ticksSinceLastFrameupdate = 0;
		
		this.x = x;
		this.y = y;
	}
	
	private final BufferedImage[] frames;
	private final int ticksPerFrameUpdate;
	
	private int currentFrameIndex = 0;
	private int ticksSinceLastFrameupdate;
	
	private int x;
	private int y;
	
	@Override
	public void draw(Graphics g) 
	{
		if(ticksPerFrameUpdate >= ticksPerFrameUpdate)
		{
			//TODO: know when to reset to 0
//			if(currentFrameIndex < frames.length - 1)
//			{
//				
//			}
		}
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public int getWidth()
	{
		return frames[currentFrameIndex].getWidth();
	}

	@Override
	public int getHeight()
	{
		return frames[currentFrameIndex].getHeight();
	}
}
