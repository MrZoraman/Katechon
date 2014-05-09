package apcs.katechon.input;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

public class InputHandler
{
	private static Keyboard keyboard;
	private static Mouse mouse;
	
	List<MouseListener> mouseListeners;
	
	private static InputHandler handler;
	
	private InputHandler()
	{
		
	}
	
	public synchronized static InputHandler getInstance()
	{
		if (handler == null)
		{
			handler = new InputHandler();
		}
		return handler;
	}
	
	public void addListener(MouseListener listener)
	{
		this.mouseListeners.add(listener);
	}
}
