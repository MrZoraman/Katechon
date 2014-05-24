package apcs.katechon.windowingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

import apcs.katechon.rendering.IDrawable;

public class Window implements IDrawable
{
	private static final int BORDER_WIDTH = 6;
	private static final int WINDOW_BAR_HEIGHT = 30;
	
	public Window(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.backgroundColor = new Color(53, 53, 53);
		this.borderColor = new Color(51, 102, 153);
	}

	private final int width;
	private final int height;
	
	private int x;
	private int y;
	
	private Color backgroundColor;
	private Color borderColor;
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(borderColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(backgroundColor);
		g.fillRect(x + BORDER_WIDTH, y + WINDOW_BAR_HEIGHT, width - (BORDER_WIDTH * 2), height - (BORDER_WIDTH + WINDOW_BAR_HEIGHT));
	}
	
	public void setBackgroundColor(Color color)
	{
		this.backgroundColor = color;
	}
	
	public void setBorderColor(Color color)
	{
		this.borderColor = color;
	}
}
