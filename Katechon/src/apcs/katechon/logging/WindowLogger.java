package apcs.katechon.logging;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import apcs.katechon.rendering.IDrawable;

public class WindowLogger implements ILogger, IDrawable
{
	public WindowLogger(int linesShown)
	{
		messages = new String[linesShown];
		
		for(int ii = 0; ii < linesShown; ii++)
		{
			//TODO: is this necessary?
			messages[ii] = "";
		}
	}
	
	private static final int LINE_SPACING = 25;
	
	private final String[] messages;
	
	@Override
	public void log(String message)
	{
		appendToTop(message);
	}
	
	@Override
	public void draw(Graphics g)
	{
		int x = 10;
		int y = 25;
		
		g.setColor(new Color(169, 169, 169, 200));
		g.fillRect(5, 5, 800, 375);

		g.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		g.setColor(new Color(51, 102, 153));
		
		for(int ii = 0; ii < messages.length; ii++)
		{
			if(messages[ii].equals("")) continue;
			
//			System.out.println("printing " + messages[ii]);
			g.drawString(messages[ii], x, y);
			y += LINE_SPACING;
		}
	}
	
	private void appendToTop(String message)
	{
		for(int ii = messages.length - 1; ii > 0; ii--)
		{
			messages[ii] = messages[ii - 1];
		}
		
		messages[0] = message;
	}

	@Override
	public void saveLog()
	{
	}
}
