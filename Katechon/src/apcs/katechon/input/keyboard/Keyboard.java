package apcs.katechon.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Keyboard implements KeyListener
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

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
