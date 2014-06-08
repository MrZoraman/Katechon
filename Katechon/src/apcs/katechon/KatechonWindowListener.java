package apcs.katechon;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class will implement logic for window state change events (such as closing and resizing)
 * @author matt
 *
 */
public class KatechonWindowListener implements WindowListener
{
	@Override
	public void windowActivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{		
		KatechonEngine.getInstance().end();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
	}
}
