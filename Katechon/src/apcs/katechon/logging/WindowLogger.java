package apcs.katechon.logging;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.windowingtoolkit.Window;

/**
 * This is a type of logger that logs things on the game window itself
 * @author Matt
 *
 */
public class WindowLogger extends Window implements ILogger, KeyPressedListener, IConsole
{
	//CONSOLE OVERLAY
	private static final int   CONSOLE_OVERLAY_WIDTH = 800;
	private static final int   CONSOLE_OVERLAY_HEIGHT = 400 + Window.getWindowBarHeight() + 6;
	private static final Color INPUT_OVERLAY_COLOR = new Color(127, 127, 127, 200);
	private static final int   INPUT_OVERLAY_HEIGHT = 29;
	
	//FONT
	private static final String CONSOLE_FONT_NAME = "Lucida Console";
	private static final int    CONSOLE_FONT_STYLE = Font.PLAIN;
	private static final int    CONSOLE_FONT_SIZE = 20;
	private static final Font   CONSOLE_FONT = new Font(CONSOLE_FONT_NAME, CONSOLE_FONT_STYLE, CONSOLE_FONT_SIZE);
	private static final Color  CONSOLE_FONT_COLOR = new Color(198, 204, 213, 200);
	
	//KARAT
	private static final Color KARAT_COLOR = Color.GREEN;
	private static final int   KARAT_OFFSET_X = 4 + Window.getBorderWidth();
	private static final int   KARAT_OFFSET_Y = 2 + Window.getWindowBarHeight();
	private static final int   KARAT_WIDTH = 3;
	private static final int   KARAT_HEIGHT = 25;
	
	//TEXT OUTPUT CONSTANTS
	private static final int CONSOLE_TEXT_OFFSET_X = 5 + Window.getBorderWidth();
	private static final int CONSOLE_TEXT_OFFSET_Y = 20 + Window.getWindowBarHeight();
	private static final int LINE_SPACING = 25;
	
	//WINDOW COLORS
	private static final Color CONSOLE_BACKGROUND_COLOR = new Color(53, 53, 53, 200);
	private static final Color CONSOLE_BORDER_COLOR = new Color(51, 102, 153, 200);
	
	
	/**
	 * Constructor
	 * @param linesShown The amount of lines to show in the console
	 */
	public WindowLogger(int linesShown, int x, int y)
	{
		super(x, y, CONSOLE_OVERLAY_WIDTH, CONSOLE_OVERLAY_HEIGHT);
		
		this.setBackgroundColor(CONSOLE_BACKGROUND_COLOR);
		this.setBorderColor(CONSOLE_BORDER_COLOR);
		
		this.messages = new String[linesShown];
		
		this.showKarat = new AnimatedSequence<Boolean>(new Boolean[]{true, false}, 5);
		
		this.inputString = new StringBuilder();
		
		this.x = x;
		this.y = y;
		
		Keyboard.getInstance().addKeyPressedListener(Keys.ALL, this);
	}
	
	private final String[] messages;
	private final AnimatedSequence<Boolean> showKarat;
	private final StringBuilder inputString;

	private final int x;
	private final int y;
	
	private String outputString = null;
	
	@Override
	public void log(String message)
	{
		appendToTop(message);
	}
	
	@Override
	public void draw(Graphics g)
	{
		super.draw(g);
		
		//The input bocks
		g.setColor(INPUT_OVERLAY_COLOR);
		g.fillRect(x + Window.getBorderWidth(), y + Window.getWindowBarHeight(), CONSOLE_OVERLAY_WIDTH - (Window.getBorderWidth() * 2), INPUT_OVERLAY_HEIGHT);

		//Set the font
		g.setFont(CONSOLE_FONT);
		
		//Draw the karat
		if(showKarat.getCurrentFrame())
		{
			g.setColor(KARAT_COLOR);
			int xOffset = g.getFontMetrics().charsWidth(inputString.toString().toCharArray(), 0, inputString.length());
			g.fillRect(x + KARAT_OFFSET_X + xOffset, y + KARAT_OFFSET_Y, KARAT_WIDTH, KARAT_HEIGHT);
		}

		//Draw all of the log messages
		g.setColor(CONSOLE_FONT_COLOR);
		
		g.drawString(inputString.toString(), x + CONSOLE_TEXT_OFFSET_X, y + CONSOLE_TEXT_OFFSET_Y);
		
		for(int ii = 0; ii < messages.length; ii++)
		{
			if(messages[ii] != null)
			{
				g.drawString(messages[ii], x + CONSOLE_TEXT_OFFSET_X, y + CONSOLE_TEXT_OFFSET_Y + (LINE_SPACING * (ii + 1)));
			}
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
	public void onKeyPressed(Keys key, char keyTyped)
	{
		if(key.equals(Keys.ENTER))
		{
			outputString = inputString.toString();
			Log.info("> " + outputString);
			inputString.setLength(0);
		}
		else if (key.equals(Keys.BACKSPACE))
		{
			if(inputString.length() > 0)
			{
				inputString.setLength(inputString.length() - 1);
			}
		}
		else
		{
			if(Character.isDefined(keyTyped))
			{
				inputString.append(keyTyped);
				
				showKarat.reset();
			}
		}
	}
	
	@Override
	public String readLine()
	{
		String cmd = outputString;
		outputString = null;
		return cmd;
	}
	
	@Override
	public void saveLog()
	{
	}

	@Override
	public boolean isFinished() {
		//The logger is never finished.
		return false;
	}
	
	@Override
	public void chainLog(String[] messages)
	{
		for(int ii = messages.length - 1; ii >= 0; ii--)
		{
			this.log(messages[ii]);
		}
	}
}
