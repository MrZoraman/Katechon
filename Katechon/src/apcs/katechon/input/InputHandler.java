package apcs.katechon.input;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * All control of and information from keyboard and mouse
 * @author Floating-Imp
 */
public class InputHandler
{
	private static Keyboard keyboard;
	private static Mouse mouse;
	
	List<MouseListener> mouseListeners;
	
	private static InputHandler handler;
	
	private InputHandler()
	{
		this.keyboard = new Keyboard();
		this.mouse = new Mouse();
	}
	
	/**
	 * Get the instance of the InputHandler
	 * @return The instance of the InputHandler
	 */
	public synchronized static InputHandler getInstance()
	{
		if (handler != null)
		{
			throw new IllegalStateException();
		}
		handler = new InputHandler();
		return handler;
	}
}
