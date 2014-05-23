package apcs.katechon.rendering.sprites;

/**
 * Represents a sequence of something (T) that will update in a loop each time getCurrentFrame() is called. 
 * 		The frame can be specified to update once eveyr x amount of calls.
 * @author Matt
 *
 * @param <T> The type of object that is being updated
 */
public class AnimatedSequence<T>
{
	private final T[] frames;
	private final int ticksPerFrameUpdate;
	
	private int currentFrameIndex;
	private int ticksSinceLastFrameUpdate;
	
	/**
	 * Constructor
	 * @param frames The array of frames that the class cycles through when getCurrentFrame() is called
	 * @param ticksPerFrameUpdate How many times the getCurrentFrame() method should be called before the current frame changes.
	 * 		Set this to <b>0</b> to have the frame update every time the method is called.
	 */
	public AnimatedSequence(final T[] frames, int ticksPerFrameUpdate)
	{
		this.frames = frames;
		this.ticksPerFrameUpdate = ticksPerFrameUpdate;
		
		//This is set to -1 because when the updateFrameIndex() is called, it increments the index and then reads the index from the array.
		//Therefore, When getCurrentFrame() is called for the first time, currentFrameIndex will be set to 0 and all will be good.
		
		//The above comment is a lie.
		this.currentFrameIndex = 0;
		this.ticksSinceLastFrameUpdate = 0;
	}
	
	/**
	 * Updates the current frame and returns it
	 * @return The current frame in the cycle
	 */
	public T getCurrentFrame()
	{
		updateFrameIndex();
		return frames[currentFrameIndex];
	}
	
	/**
	 * Updates the current frame in the cycle. Either incrementing the index or resetting the index to 0
	 */
	private void updateFrameIndex()
	{
		//Is it time to update the sprite?
		if(ticksSinceLastFrameUpdate >= ticksPerFrameUpdate)
		{
			ticksSinceLastFrameUpdate = 0;
			
			currentFrameIndex++;
			
			//know when to reset to 0
			if(currentFrameIndex >= frames.length)
			{
				currentFrameIndex = 0;
			}
		}
		else
		{
			ticksSinceLastFrameUpdate++;
		}
	}
}
