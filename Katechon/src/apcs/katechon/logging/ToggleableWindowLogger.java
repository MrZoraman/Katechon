package apcs.katechon.logging;

import java.awt.Graphics;

import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;

/**
 * Same as the {@link apcs.katechon.logging.WindowLogger WindowLogger} 
 * 		but it listens on a key that will toggle if the console is shown on the display or not.
 * @author Matt
 *
 */
public class ToggleableWindowLogger extends WindowLogger {

	/**
	 * Constructor
	 * @param linesShown The amount of lines to show in the console
	 */
	public ToggleableWindowLogger(int linesShown, int x, int y) {
		super(linesShown, x, y);
		
		this.hidden = true;
	}
	
	private boolean hidden;
	private Keys toggleKey;
	
	/**
	 * Sets the key that this logger will listen on to toggle the visibility of the console
	 * @param key
	 */
	public void initHideKey(final Keys key)
	{
		this.toggleKey = key;
	}
	
	@Override
	public void onKeyPressed(Keys pressedKey, char keyTyped)
	{
		if(pressedKey.equals(toggleKey))
		{
			hidden = !hidden;
			
			if(!hidden)
			{
				Keyboard.getInstance().setExclusiveKeyListener(this);
			}
			else
			{
				Keyboard.getInstance().setExclusiveKeyListener(null);
			}
		}
		else
		{
			if(!hidden)
			{
				super.onKeyPressed(pressedKey, keyTyped);
			}
		}
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(hidden == false)
		{
			super.draw(g);
		}
	}
}
