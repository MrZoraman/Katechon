package apcs.katechon.input;

import java.awt.event.KeyListener;

public class Keyboard
{
	private InputHandler handler;
	
	public Keyboard()
	{
		this.handler = InputHandler.getInstance();
	}
	
	public void addListener(KeyListener listener)
	{
		handler.addListener(listener);
	}
}
