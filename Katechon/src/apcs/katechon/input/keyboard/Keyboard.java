package apcs.katechon.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
	
	private KeyPressedListener exclusiveKeyListener;
	
	/**
	 * Constructor
	 */
	private Keyboard()
	{
		keyListeners = new HashMap<Keys, Set<KeyPressedListener>>();
		
		keyStates = new HashMap<Keys, Boolean>();
		
		exclusiveKeyListener = null;
	}
	
	/**
	 * Adds a new listener to the keyboard
	 * @param listener A listener of type KeyListener
	 */
	public void addKeyPressedListener(Keys key, KeyPressedListener listener)
	{	
		if(keyListeners.containsKey(key) == false)
		{
			keyListeners.put(key, new HashSet<KeyPressedListener>());
		}
		
		keyListeners.get(key).add(listener);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		Keys key = Keys.getKey(e.getKeyCode());
		
		keyStates.put(key, true);
		
		if(keyListeners.containsKey(key))
		{
			if(exclusiveKeyListener != null)
			{
				fireExclusiveKeyListener(key, e.getKeyChar());
			}
			else
			{
				fireKeyEvents(key, key, e.getKeyChar());
			}
		}
		
		if(keyListeners.containsKey(Keys.ALL))
		{
			if(exclusiveKeyListener == null)
			{
				fireKeyEvents(Keys.ALL, key, e.getKeyChar());
			}
			else
			{
				fireExclusiveKeyListener(key, e.getKeyChar());
			}
		}
	}
	
	/**
	 * Fires an event that only fires for the exclusive key listener. The listener will be set to null if it is finished (isFinished())
	 * @param key The key to listen on
	 * @param keyChar They key in char form
	 */
	private void fireExclusiveKeyListener(Keys key, char keyChar)
	{
		if(exclusiveKeyListener.isFinished())
		{
			//TODO: removed next time key is pressed...
			exclusiveKeyListener = null;
		}
		else
		{
			exclusiveKeyListener.onKeyPressed(key, keyChar);
		}
	}
	
	/**
	 * Fires key events for a given event
	 * @param key The key that the listeners are listening for (May be Keys.ALL)
	 * @param keyPressed The key that was pressed 
	 * @param keyChar They key that was pressed in char form
	 */
	private void fireKeyEvents(Keys key, Keys keyPressed, char keyChar)
	{
		Iterator<KeyPressedListener> it = keyListeners.get(key).iterator();
		while(it.hasNext())
		{
			KeyPressedListener listener = it.next();
			if(listener.isFinished())
			{
				it.remove();
			}
			else
			{
				//TODO: removed next time key is pressed...
				listener.onKeyPressed(keyPressed, keyChar);
			}
		}
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
	public void keyReleased(KeyEvent e)
	{
		Keys key = Keys.getKey(e.getKeyCode());
		
		keyStates.put(key, false);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
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
	
	/**
	 * Clears all listeners from the keyboard
	 */
	public void clearListeners()
	{
		this.keyListeners.clear();
		this.exclusiveKeyListener = null;
	}
}
