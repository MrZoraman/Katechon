package apcs.katechon.rendering.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;

/**
 * Represents a drawable that is animated. It holds various animation sequences that can be switched to during runtime 
 * 		(ex, a stationary animation sequence and a running animation sequence, etc)
 * @author Zora
 *
 * @param <I> The type of index to be used to specify animation sequences
 */
public class AnimatedSprite<I> implements IDrawable
{
	private int x;
	private int y;
	
	private boolean finished;
	
	private final Map<I, AnimatedSequence<BufferedImage>> animationSequences;
	
	private AnimatedSequence<BufferedImage> currentSequence;
	
	/**
	 * Constructor. This class does nothing until sequences are added with {@link #addSequence(Object, AnimatedSequence) addSequence()}.
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public AnimatedSprite(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.animationSequences = new HashMap<I, AnimatedSequence<BufferedImage>>();
		
		this.currentSequence = null;
		
		this.finished = false;
	}
	
	/**
	 * Adds an animation sequence at a given key. The current animation sequence for this sprite will be set to the first sequence added (the first time this method is called).
	 * @param key The key to be able to reference this animation sequence in the future.
	 * @param sequence The sequence to add.
	 */
	public void addSequence(I key, AnimatedSequence<BufferedImage> sequence)
	{
		animationSequences.put(key, sequence);
		
		if(currentSequence == null)
		{
			currentSequence = sequence;
		}
	}
	
	/**
	 * Changes the current sequence that will be animated and drawn to the screen
	 * @param key The key that points to an animated sequence. If no sequence is found at a given key, the sequence will not change.
	 */
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

	/**
	 * @return The x coordinate
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return The y coordinate
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Sets the x coordinate
	 * @param x The x coordinate
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate
	 * @param y The y coordinate
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}
	
	/**
	 * Sets if this drawable is done being drawn or not. If finished is set to true, it will be removed from the renderer.
	 * @param finished Is this drawable done being drawn?
	 */
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
}
