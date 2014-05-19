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
	private static Keyboard instance;
	
	/**
	 * Get the instance of the Keyboard.
	 * @return The Keyboard instance
	 */
	public static synchronized Keyboard getInstance()
	{
		if (instance == null)
			instance = new Keyboard();
		
		return instance;
	}
	
	private final Map<Keys, Set<KeyPressedListener>> keyListeners;
	
	private final Map<Keys, Boolean> keyStates;
	
	/**
	 * Constructor
	 */
	private Keyboard()
	{
		keyListeners = new HashMap<Keys, Set<KeyPressedListener>>();
		keyStates = new HashMap<Keys, Boolean>();
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
		
		keyStates.put(key, true);
		
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
		Keys key = Keys.getKey(e.getKeyCode());
		
		keyStates.put(key, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public boolean isKeyPressed(Keys key)
	{
		if(keyStates.containsKey(key) == false)
		{
			keyStates.put(key, false);
		}
		
		return keyStates.get(key);
	}
}
