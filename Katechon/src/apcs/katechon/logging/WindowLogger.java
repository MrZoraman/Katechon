package apcs.katechon.logging;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.sprites.AnimatedSequence;

/**
 * This is a type of logger that logs things on the game window itself
 * @author Matt
 *
 */
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
		
		inputString = null;
		
		showKarat = new AnimatedSequence<Boolean>(new Boolean[]{true, false}, 5);
		karatPositionX = 7;
	}
	
	private static final int LINE_SPACING = 25;
	
	private final String[] messages;
	
	private String inputString;
	
	private AnimatedSequence<Boolean> showKarat;
	private int karatPositionX;
	
	@Override
	public void log(String message)
	{
		appendToTop(message);
	}
	
	@Override
	public void draw(Graphics g)
	{
		//The console overaly
		g.setColor(new Color(169, 169, 169, 200));
		g.fillRect(5, 5, 800, 400);
		
		//The input bocks
		g.setColor(new Color(175, 175, 175, 255));
		g.fillRect(5, 5, 800, 30);

		//Set the font
		g.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		
		//Draw the karat
		if(showKarat.getCurrentFrame())
		{
			g.setColor(Color.GREEN);
			g.fillRect(karatPositionX, 7, karatPositionX + 13, 26);
		}

		//Draw all of the log messages
		g.setColor(new Color(51, 102, 153));
		
		int x = 10;
		int y = 50;
		
		for(int ii = 0; ii < messages.length; ii++)
		{
			if(messages[ii].equals("")) continue;
			
//			System.out.println("printing " + messages[ii]);
			g.drawString(messages[ii], x, y);
			y += LINE_SPACING;
		}
	}
	
	/**
	 * Puts a message to the top of the log
	 * @param message The message to put on top
	 */
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

	@Override
	public String readLine()
	{
		return inputString;
	}
}
