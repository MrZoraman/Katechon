package apcs.katechon.windowingtoolkit;

import apcs.katechon.rendering.IDrawable;

/**
 * Represents an item that is drawn on the screen using the katechon windowing toolkit
 * @author Matt
 *
 */
public abstract class DisplayableBase implements IDrawable
{
	private boolean finished;
	
	private Window window;
	
	public DisplayableBase()
	{
		this.finished = false;
	}
	
	/**
	 * Sets the visibility status of this object.
	 * @param visible True if the object is to be shown on the screen, False if the object is to go invisible
	 */
	//TODO does this/is this expected to have an impact on captured inputs?
	public abstract void setVisible(boolean visible);
	
	/**
	 * Sets if this item is done being drawn and can be disposed by the GC or not.
	 * @param finished True if this object is done, False if otherwise.
	 */
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	
	@Override
	public boolean isFinished()
	{
		return finished;
	}
	
	void setWindow(Window window)
	{
		this.window = window;
	}
	
	protected Window getWindow()
	{
		return window;
	}
}
