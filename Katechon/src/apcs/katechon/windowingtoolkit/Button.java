package apcs.katechon.windowingtoolkit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import apcs.katechon.KatechonEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.input.mouse.MouseClickedListener;

/**
 * A visible button that can be shown on the screen and added to the scheduler.
 * @author Matt
 */
public abstract class Button extends DisplayableBase implements MouseClickedListener, ISchedulerTask
{
	//TODO: explain these constants
	private static final int X_OFFSET = -3;
	private static final int Y_OFFSET = -26;
	
	public Button(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.passiveColor = new Color(192, 46, 46);
		this.mouseOverColor = new Color(210, 102, 102);
		this.mouseHeldColor = new Color(255, 0, 0);
		
		this.currentColor = passiveColor;
		
		this.visible = false;
		this.finished = false;
		
		Mouse.getInstance().addListener(this);
		KatechonEngine.getInstance().scheduleTask(this);
		//TODO: layer!
		KatechonEngine.getInstance().addDrawable(this, 1);
	}
	
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	
	private Window window;
	
	private Color passiveColor;
	private Color mouseOverColor;
	private Color mouseHeldColor;
	
	private Color currentColor;
	
	private boolean visible;
	private boolean finished;
	
	public abstract void onClick();
	
	@Override
	public void draw(Graphics g)
	{
		if(visible)
		{
			int xRelativeToScreen = x + window.getX();
			int yRelativeToScreen = y + window.getY();
			
			g.setColor(currentColor);
			paint(g, xRelativeToScreen, yRelativeToScreen, width, height);
		}
	}

	public void paint(Graphics g, int x, int y, int width, int height)
	{
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public void onClick(int x, int y)
	{
		if(visible)
		{
			if(isMouseOnButton(getPointRelativeToWindow(x, y)))
			{
				onClick();
			}
		}
	}
	
	@Override
	public void doTask()
	{
		if(visible)
		{
			Point mouseLocRelativeToWindow = getPointRelativeToWindow(Mouse.getInstance().getPosition());
			
			if(isMouseOnButton(mouseLocRelativeToWindow))
			{
				if(Mouse.getInstance().isPressed())
				{
					currentColor = mouseHeldColor;
				}
				else
				{
					currentColor = mouseOverColor;
				}
			}
			else
			{
				currentColor = passiveColor;
			}
		}
	}
	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
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
	}
	
	void setWindow(Window window)
	{
		this.window = window;
	}
	
	protected Window getWindow()
	{
		return window;
	}
	
	private Point getPointRelativeToWindow(Point point)
	{
		return getPointRelativeToWindow(point.x, point.y);
	}
	
	private Point getPointRelativeToWindow(int x, int y)
	{
		return new Point(x - window.getX() + X_OFFSET, y - window.getY() + Y_OFFSET);
	}
	
	//panel x and y!
	private boolean isMouseOnButton(int x, int y)
	{
		return (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height);
	}
	
	private boolean isMouseOnButton(Point mousePoint)
	{
		return isMouseOnButton(mousePoint.x, mousePoint.y);
	}
	
	/**
	 * Sets the color of the button when nothing is affecting it.
	 * @param color The color to set to.
	 */
	public void setPassiveColor(Color color)
	{
		this.passiveColor = color;
	}
	
	/**
	 * Sets the color of the button when a mouse is hovering over it.
	 * @param color The color the button should be when a mouse is hovering.
	 */
	public void setMouseOverColor(Color color)
	{
		this.mouseOverColor = color;
	}
	
	/**
	 * Sets the color of the button when the mouse is held down on it.
	 * @param color The color the button should be when the mouse is held.
	 */
	public void setMouseHeldColor(Color color)
	{
		this.mouseHeldColor = color;
	}
	
	protected int getX()
	{
		return x;
	}
	
	protected int getY()
	{
		return y;
	}
	
	protected int getWidth()
	{
		return width;
	}
	
	protected int getHeight()
	{
		return height;
	}
}
