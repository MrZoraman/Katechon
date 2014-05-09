package apcs.katechon.input;

import java.awt.event.KeyListener;
import java.util.List;

public class Keyboard
{	
	private List<KeyListener> keyListeners;
	
	public Keyboard()
	{
		
	}
	
	public void addListener(KeyListener listener)
	{
		keyListeners.add(listener);
	}
}
