package apcs.katechon.windowingtoolkit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.input.mouse.MouseClickedListener;
import apcs.katechon.periodic.IPeriodic;
import apcs.katechon.rendering.IDrawable;

public abstract class Button implements MouseClickedListener, IPeriodic, IDrawable
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
	
	public abstract void onClick();
	
	@Override
	public void draw(Graphics g)
	{
		int xRelativeToScreen = x + window.getX();
		int yRelativeToScreen = y + window.getY();
		
		g.setColor(currentColor);
		g.fillRect(xRelativeToScreen, yRelativeToScreen, width, height);
	}
	
	public void onClick(int x, int y)
	{
		if(isMouseOnButton(getPointRelativeToWindow(x, y)))
		{
			onClick();
		}
	}
	
	public void onTick()
	{
		Point mouseLocRelativeToWindow = getPointRelativeToWindow(Mouse.getInstance().getPosition());
		System.out.println(mouseLocRelativeToWindow);
		
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
	
	void setWindow(Window window)
	{
		this.window = window;
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
	
	public void setPassiveColor(Color color)
	{
		this.passiveColor = color;
	}
	
	public void setMouseOverColor(Color color)
	{
		this.mouseOverColor = color;
	}
	
	public void setMouseHeldColor(Color color)
	{
		this.mouseHeldColor = color;
	}
}
