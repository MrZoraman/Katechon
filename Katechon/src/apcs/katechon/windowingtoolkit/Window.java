package apcs.katechon.windowingtoolkit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a simple window with a close button.
 * @author Matt
 *
 */
public class Window extends DisplayableBase
{
	private static final int BORDER_WIDTH = 6;
	private static final int WINDOW_BAR_HEIGHT = 30;
	
	private static final int TITLE_OFFSET_X = Window.getBorderWidth() + 5;
	private static final int TITLE_OFFSET_Y = Window.getWindowBarHeight() - 3;

	private static final String TITLE_FONT_NAME = "Arial";
	private static final int    TITLE_FONT_STYLE = Font.PLAIN;
	private static final int    TITLE_FONT_SIZE = 20;
	private static final Font   TITLE_FONT = new Font(TITLE_FONT_NAME, TITLE_FONT_STYLE, TITLE_FONT_SIZE);
	private static final Color  TITLE_FONT_COLOR = new Color(151, 203, 255);
	
	public static int getBorderWidth()
	{
		return BORDER_WIDTH;
	}
	
	public static int getWindowBarHeight()
	{
		return WINDOW_BAR_HEIGHT;
	}
	
	private static final int CLOSE_BUTTON_SIZE = WINDOW_BAR_HEIGHT - 12;
	
	/**
	 * Constructor
	 * @param x X coordinate of window
	 * @param y Y coordinate of window
	 * @param width Width of window
	 * @param height height of window
	 */
	public Window(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		
		this.displayables = new HashSet<DisplayableBase>();
		
		this.width = width;
		this.height = height;
		
		this.backgroundColor = new Color(53, 53, 53);
		this.borderColor = new Color(51, 102, 153);
		
		this.addDisplayable(new CloseButton(this, width - CLOSE_BUTTON_SIZE - BORDER_WIDTH, BORDER_WIDTH, CLOSE_BUTTON_SIZE, CLOSE_BUTTON_SIZE));
		
		this.visible = false;
		this.finished = false;
	}

	private final int width;
	private final int height;
	
	private final Set<DisplayableBase> displayables;
	
	private int x;
	private int y;
	
	private Color backgroundColor;
	private Color borderColor;
	
	private boolean visible;
	private boolean finished;
	
	private String title;
	
	@Override
	public void draw(Graphics g)
	{
		if(visible)
		{
			g.setColor(borderColor);
			g.fillRect(x, y, width, height);
			
			g.setColor(backgroundColor);
			g.fillRect(x + BORDER_WIDTH, y + WINDOW_BAR_HEIGHT, width - (BORDER_WIDTH * 2), height - (BORDER_WIDTH + WINDOW_BAR_HEIGHT));
			
			for(DisplayableBase displayable : displayables)
			{
				displayable.draw(g);
			}
			
			if(title != null)
			{
				g.setColor(TITLE_FONT_COLOR);
				g.setFont(TITLE_FONT);
				g.drawString(title, x + TITLE_OFFSET_X, y + TITLE_OFFSET_Y);
			}
		}
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
		
		for(DisplayableBase displayable : displayables)
		{
			displayable.setVisible(visible);
		}
	}
	
	public void addDisplayable(DisplayableBase displayable)
	{
		displayables.add(displayable);
		displayable.setWindow(this);
	}
	
	/**
	 * Checks if the window is visible or not
	 * @return True if the window is visible, false if otherwise
	 */
	public boolean isVisible()
	{
		return visible;
	}
	
	/**
	 * Sets the background color for the window
	 * @param color The background color
	 */
	public void setBackgroundColor(Color color)
	{
		this.backgroundColor = color;
	}
	
	/**
	 * Sets the border (and top bar) color for the window
	 * @param color The color for the border
	 */
	public void setBorderColor(Color color)
	{
		this.borderColor = color;
	}
	
	/**
	 * @return The x coordinate of the window relative to the game window display
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * @return The y coordinate of the window relative to the game window display
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * @return The window's width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * @return The window's height
	 */
	public int getHeight()
	{
		return height;
	}

	@Override
	public boolean isFinished()
	{
		return finished;
	}

	@Override
	public void setFinished(boolean finished)
	{
		this.finished = finished;
		
		for(DisplayableBase displayable : displayables)
		{
			displayable.setFinished(finished);
		}
	}
	
	/**
	 * Called when the close button is clicked
	 */
	public void onClose()
	{
		setFinished(true);
	}
	
	/**
	 * Sets the title for the window
	 * @param title The window title that will show up in the top bar
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
}
