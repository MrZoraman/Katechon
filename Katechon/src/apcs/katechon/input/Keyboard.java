package apcs.katechon.input;

import java.awt.event.KeyListener;
import java.util.List;

public class Keyboard
{	
	private List<KeyListener> keyListeners;
	
	public Keyboard()
	{
		
	}
	
	/**
	 * Adds a new listener to the keyboard
	 * @param listener A listener of type KeyListener
	 */
	public void addListener(KeyListener listener)
	{
		keyListeners.add(listener);
	}
}
