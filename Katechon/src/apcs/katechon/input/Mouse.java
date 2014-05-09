package apcs.katechon.input;

import java.awt.event.MouseListener;

public class Mouse
{
	private InputHandler handler;
	
	public Mouse()
	{
		this.handler = InputHandler.getInstance();
	}
	
	public void addListener(MouseListener listener)
	{
		handler.addListener(listener);
	}
}
