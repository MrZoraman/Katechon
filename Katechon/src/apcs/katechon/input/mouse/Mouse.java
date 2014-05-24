package apcs.katechon.input.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the computer mouse, ready to be plugged into the swing framework.
 * @author Sean
 *
 */
public class Mouse implements MouseListener, MouseMotionListener
{
	private static Mouse instance;
	
	/**
	 * Gets the instance of the Mouse.
	 * @return The instance of the Mouse.
	 */
	public static synchronized Mouse getInstance()
	{
		if(instance == null)
			instance = new Mouse();
		
		return instance;
	}
	
	private Set<MouseClickedListener> mouseListeners;
	private Point currentMousePosition;
	private boolean isPressed;
	
	/**
	 * Constructor
	 */
	private Mouse()
	{
		mouseListeners = new HashSet<MouseClickedListener>();
		currentMousePosition = new Point(0, 0);
		isPressed = false;
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
	
	//TODO: documentation
	public Point getPosition()
	{
		return currentMousePosition;
	}
	
	//TODO: documentation
	public boolean isPressed()
	{
		return isPressed;
	}
	
	@Override
	public void mouseMoved(MouseEvent event)
	{
		this.currentMousePosition = event.getPoint();
	}
	
	@Override
	public void mousePressed(MouseEvent event)
	{
		isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		isPressed = false;
	}

	//We probably don't need to use these
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
	}
}
