package apcs.katechon.input;

import java.awt.event.MouseListener;
import java.util.List;

public class Mouse
{
	private List<MouseListener> mouseListeners;
	
	public Mouse()
	{
		
	}
	
	public void addListener(MouseListener listener)
	{
		mouseListeners.add(listener);
	}
}
