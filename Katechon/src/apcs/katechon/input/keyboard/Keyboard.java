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
	private final Set<KeyPressedListener> allKeyListeners;
	
	private final Map<Keys, Boolean> keyStates;
	
	private KeyPressedListener exclusiveKeyListener;
	
	/**
	 * Constructor
	 */
	private Keyboard()
	{
		keyListeners = new HashMap<Keys, Set<KeyPressedListener>>();
		allKeyListeners = new HashSet<KeyPressedListener>();
		keyStates = new HashMap<Keys, Boolean>();
		
		exclusiveKeyListener = null;
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
	}
	
	/**
	 * Sets an exclusive key listener. When an exclusive key listener is set, no other key events will be fired, and isKeyPressed() will return false every time.
	 * @param listener The listener to exclusively listen on
	 */
	public void setExclusiveKeyListener(KeyPressedListener listener)
	{
		exclusiveKeyListener = listener;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keys key = Keys.getKey(e.getKeyCode());
		
		keyStates.put(key, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Keys key = Keys.getKey(e.getKeyCode());
		
		if(exclusiveKeyListener != null)
		{
			exclusiveKeyListener.onKeyPressed(key);
		}
		else
		{
			for(KeyPressedListener listener : allKeyListeners)
			{
				listener.onKeyPressed(key);
			}
			
			if(keyListeners.containsKey(key))
			{
				for(KeyPressedListener listener : keyListeners.get(key))
				{
					listener.onKeyPressed(key);
				}
			}
		}
	}
	
	/**
	 * Checks if a key is pressed or not
	 * @param key The key to check
	 * @return True if the key is pressed, False if otherwise
	 */
	public boolean isKeyPressed(Keys key)
	{
		if(exclusiveKeyListener == null)
		{
			if(keyStates.containsKey(key) == false)
			{
				keyStates.put(key, false);
			}
			
			return keyStates.get(key);
		}
		else
		{
			return false;
		}
	}
}
