package apcs.katechon.input;

import java.awt.event.MouseListener;
import java.util.List;

public class Mouse
{
	private List<MouseListener> mouseListeners;
	
	public Mouse()
	{
		
	}
	
	/**
	 * Adds a new listener to the mouse
	 * @param listener A listener of type MouseListener
	 */
	public void addListener(MouseListener listener)
	{
		mouseListeners.add(listener);
	}
}
