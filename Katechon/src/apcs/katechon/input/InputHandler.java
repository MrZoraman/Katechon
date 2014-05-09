package apcs.katechon.input;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

public class InputHandler
{
	private static KeyListener keyListener;
	private static MouseListener mouseListener;
	
	List<KeyListener> keyListeners;
	List<MouseListener> mouseListeners;
	
	public InputHandler()
	{
		
	}
	
	public void addListener(KeyListener listener)
	{
		this.keyListeners.add(listener);
	}
	
	public void addListener(MouseListener listener)
	{
		this.mouseListeners.add(listener);
	}
}
