package apcs.katechon.logging;

import java.awt.Graphics;

import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;

/**
 * Same as the {@link apcs.katechon.logging.WindowLogger WindowLogger} 
 * 		but it listens on a key that will toggle if the console is shown on the display or not.
 * @author Matt
 *
 */
public class ToggleableWindowLogger extends WindowLogger {

	public ToggleableWindowLogger(int linesShown) {
		super(linesShown);
		
		this.hidden = true;
	}
	
	private boolean hidden;
	
	/**
	 * Sets the key that this logger will listen on to toggle the visibility of the console
	 * @param key
	 */
	public void initHideKey(final Keys key)
	{
		Keyboard.getInstance().addListener(key, new KeyPressedListener() {
			@Override
			public void onKeyPressed(Keys pressedKey) {
				if(pressedKey.equals(key))
				{
					hidden = !hidden;
				}
			}
		});
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
