package apcs.katechon.rendering.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;

public class AnimatedSprite<I> implements IDrawable
{
	private int x;
	private int y;
	
	private final Map<I, AnimatedSequence<BufferedImage>> animationSequences;
	
	private AnimatedSequence<BufferedImage> currentSequence;
	
	public AnimatedSprite(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.animationSequences = new HashMap<I, AnimatedSequence<BufferedImage>>();
		
		this.currentSequence = null;
	}
	
	public void addSequence(I key, AnimatedSequence<BufferedImage> sequence)
	{
		animationSequences.put(key, sequence);
		
		if(currentSequence == null)
		{
			currentSequence = sequence;
		}
	}
	
	public void setSequence(I key)
	{
		if(animationSequences.containsKey(key))
		{
			currentSequence = animationSequences.get(key);
		}
		else
		{
			Log.error("Failed to find an animation sequence at key '" + key + "'.");
		}
	}
	
	@Override
	public void draw(Graphics g) 
	{
		if(currentSequence != null)
		{
			g.drawImage(currentSequence.getCurrentFrame(), x, y, null);
		}
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
}
