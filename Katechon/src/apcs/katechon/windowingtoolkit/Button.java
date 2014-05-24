package apcs.katechon.windowingtoolkit;

import java.awt.Color;

import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.input.mouse.MouseClickedListener;
import apcs.katechon.periodic.IPeriodic;

public abstract class Button implements MouseClickedListener, IPeriodic
{
	public Button(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.passiveColor = new Color(192, 46, 46);
		this.mouseOverColor = new Color(210, 102, 102);
		this.mouseHeldColor = new Color(255, 0, 0);
	}
	
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	
	private Color passiveColor;
	private Color mouseOverColor;
	private Color mouseHeldColor;
	
	public abstract void onClick();
	
	public void onClick(int x, int y)
	{
		
	}
	
	public void onTick()
	{
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
