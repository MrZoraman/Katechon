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
	
	private static InputHandler handler;
	
	private InputHandler()
	{
		
	}
	
	public static InputHandler getInstance()
	{
		if (handler == null)
		{
			handler = new InputHandler();
		}
		return handler;
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
