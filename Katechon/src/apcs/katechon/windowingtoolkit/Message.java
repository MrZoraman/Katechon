package apcs.katechon.windowingtoolkit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Message extends DisplayableBase
{
	private final String message;
	private final Font font;
	private final Color color;
	
	private final int x;
	private final int y;
	
	private boolean isVisible;
	
	public Message(String message, int x, int y, Font font, Color color)
	{
		super();
		
		this.isVisible = false;
		
		this.message = message;
		this.font = font;
		this.color = color;
		
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(isVisible)
		{
			g.setColor(color);
			g.setFont(font);
			
			g.drawString(message, getWindow().getX() + x, getWindow().getY() + y);
		}
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.isVisible = visible;
	}
}
