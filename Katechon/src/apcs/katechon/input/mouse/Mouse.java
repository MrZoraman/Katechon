package apcs.katechon.input.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the computer mouse, ready to be plugged into the swing framework.
 * @author Sean
 *
 */
public class Mouse implements MouseListener
{
	//TODO: The mouse listener isn't very responsive. 
	//If you click too fast it'll stop receiving the inputs.
	//You then need to wait a little while for it to start working again.
	//Then again, I don't think will be a too mouse-intensive game, so it may not matter much.
	//For gui inputs it should be fine, but idk...
	
	private static Mouse instance;
	
	public static synchronized Mouse getInstance()
	{
		if(instance == null)
			instance = new Mouse();
		
		return instance;
	}
	
	private Set<MouseClickedListener> mouseListeners;
	
	/**
	 * Constructor
	 */
	private Mouse()
	{
		mouseListeners = new HashSet<MouseClickedListener>();
	}
	
	/**
	 * Adds a new listener to the mouse
	 * @param listener A listener of type MouseListener
	 */
	public void addListener(MouseClickedListener listener)
	{
		mouseListeners.add(listener);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		for(MouseClickedListener listener : mouseListeners)
		{
			listener.onClick(event.getX(), event.getY());
		}
	}

	//We probably don't need to use these
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
