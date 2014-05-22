package apcs.katechon.rendering.sprites;

public class AnimatedSequence<T>
{
	private final T[] frames;
	private final int ticksPerFrameUpdate;
	
	private int currentFrameIndex;
	private int ticksSinceLastFrameUpdate;
	
	public AnimatedSequence(final T[] frames, int ticksPerFrameUpdate)
	{
		this.frames = frames;
		this.ticksPerFrameUpdate = ticksPerFrameUpdate;
		
		//This is set to -1 because when the updateFrameIndex() is called, it increments the index and then reads the index from the array.
		//Therefore, When getCurrentFrame() is called for the first time, currentFrameIndex will be set to 0 and all will be good.
		this.currentFrameIndex = -1;
		this.ticksSinceLastFrameUpdate = 0;
	}
	
	public T getCurrentFrame()
	{
		updateFrameIndex();
		return frames[currentFrameIndex];
	}
	
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
