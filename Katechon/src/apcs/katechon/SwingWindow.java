package apcs.katechon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import apcs.katechon.logging.Log;
import apcs.katechon.rendering.Display;

/**
 * The link between the Java Swing framework and the engine
 * @author Matt
 */
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
	
	private final Timer timer;
	
	private Display display;
	
	/**
	 * Creates a new SwingWindow.
	 * @param width The width of this window
	 * @param height The height of this window
	 * @param title The title of the window (This cannot be changed)
	 * @param amountOfLayers The number of layers this window will support
	 * @param ticker The {@link java.awt.event.ActionListener ActionListener} that will manage the ticking of the game.
	 */
	public SwingWindow(int width, int height, final String title, int amountOfLayers, final ActionListener ticker)
	{
		frame = new JFrame();
		panel = new KatechonJPanel();
		
		//Frame holds the panel
		frame.add(panel);
		//Set the window size
		frame.setSize(width, height);
		//Set the title
		frame.setTitle(title);
		
		//Allows the game to handle the closing of the frame
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//Game logic regarding the window events implemented
		frame.addWindowListener(new KatechonWindowListener());
		
		//Put the window in the middle of the screen
		frame.setLocationRelativeTo(null);
		//Not resizeable! Let's avoid that headache...
		frame.setResizable(false);
		
		//Set background color

		frame.setBackground(Color.BLACK);
		
		display = new Display();
		for(int ii = 0; ii < amountOfLayers; ii++)
		{
			display.addLayer();
		}
		
//		//Testing code
//		for (int i = 0; i < 5; i++)
//		{
//			display.addLayer();
//			display.getLayer(i).addDrawable(new TestDrawable(100, 100, 10, 10, Color.GREEN));
//		}
		
		//TODO: tune the delay
		timer = new Timer(100, ticker);
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
			//super.paint() must come before any other changes to graphics as it seems to override all manual drawing.
			super.paint(graphics);
			//Yes, because super.paint is going to paint the background (a massive black box that covers everything)
			Log.debug("Painted.");
			display.drawLayers(frame.getGraphics());
		}
	}
	
	/**
	 * Makes the window visible.
	 */
	void show()
	{
		frame.setVisible(true);
		timer.start();
	}
	
	/**
	 * Adds a KeyListener
	 * @param keyListener The KeyListener to add
	 */
	void addKeyListener(final KeyListener keyListener)
	{
		frame.addKeyListener(keyListener);
	}
	
	/**
	 * Adds a MouseListener
	 * @param mouseListener The MouseListener to add
	 */
	void addMouseListener(final MouseListener mouseListener)
	{
		frame.addMouseListener(mouseListener);
	}
	
	/**
	 * Gets the {@link apcs.katechon.rendering.Display Display} used by this window
	 * @return The {@link apcs.katechon.rendering.Display Display} used by this window
	 */
	public Display getDisplay()
	{
		return display;
	}
	
	/**
	 * Changes the {@link apcs.katechon.rendering.Display Display} used by this window
	 * @param display The {@link apcs.katechon.rendering.Display Display} to change this window to
	 */
	public void setDisplay(Display display)
	{
		this.display = display;
	}
}