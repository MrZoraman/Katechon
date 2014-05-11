package apcs.katechon.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the computer keyboard, ready to be plugged into the swing framework.
 * @author Sean
 *
 */
public class Keyboard implements KeyListener
{
	//TODO: A global listener container so that it listens on all key presses?
	
	private static Keyboard instance;
	
	public static synchronized Keyboard getInstance()
	{
		if (instance == null)
			instance = new Keyboard();
		
		return instance;
	}
	
	private Map<Keys, Set<KeyPressedListener>> keyListeners;
	
	/**
	 * Constructor
	 */
	public Keyboard()
	{
		keyListeners = new HashMap<Keys, Set<KeyPressedListener>>();
	}
	
	/**
	 * Adds a new listener to the keyboard
	 * @param listener A listener of type KeyListener
	 */
	public void addListener(Keys key, KeyPressedListener listener)
	{
		if(keyListeners.containsKey(key) == false)
			keyListeners.put(key, new HashSet<KeyPressedListener>());
		
		keyListeners.get(key).add(listener);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Keys key = Keys.getKey(e.getKeyCode());
		
		if(keyListeners.containsKey(key))
		{
			for(KeyPressedListener listener : keyListeners.get(key))
			{
				listener.onKeyPressed(key);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
