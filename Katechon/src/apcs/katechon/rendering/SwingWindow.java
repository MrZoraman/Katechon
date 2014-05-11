package apcs.katechon.rendering;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingWindow 
{
	/**
	 * This is the window itself. It holds components like JPanels, which do the actual work. 
	 * 		This is the closest we get to the operating system as this is the class that handles all of the input events.
	 */
	private final JFrame frame;
	
	/**
	 * This is the single panel that will go in the frame. This will do the drawing.
	 */
	private final KatechonJPanel panel;
	
	public SwingWindow(int width, int height, final String title)
	{
		frame = new JFrame();
		panel = new KatechonJPanel();
		
		//Frame holds the panel
		frame.add(panel);
		//Set the window size
		frame.setSize(width, height);
		//Set the title
		frame.setTitle(title);
		//TODO: If any post-game logic needs to be executed, we'll have to change this
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Put the window in the middle of the screen
		frame.setLocationRelativeTo(null);
		//Not resizeable! Let's avoid that headache...
		frame.setResizable(false);
	}
	
	/**
	 * This nested class exists so that the the draw() method can be overridden. The draw() method contains the logic for drawing objects to the screen.
	 * @author Matt
	 *
	 */
	private class KatechonJPanel extends JPanel
	{
		//This isn't necessary, but it keeps eclipse happy (and ignore warnings are never pretty)
		private static final long serialVersionUID = -8597272245779603712L;
		
		//We extend this class for the paint method.
		@Override
		public void paint(Graphics graphics)
		{
			super.paint(graphics);
		}
	}
	
	/**
	 * Makes the window visible.
	 */
	public void show()
	{
		frame.setVisible(true);
	}
	
	/**
	 * Adds a keyListener
	 * @param keyListener The keylistener to add
	 */
	public void addKeyListener(final KeyListener keyListener)
	{
		frame.addKeyListener(keyListener);
	}
}
