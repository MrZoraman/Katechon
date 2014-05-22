package apcs.katechon.logging;

import java.awt.Graphics;

import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;

public class ToggleableWindowLogger extends WindowLogger {

	public ToggleableWindowLogger(int linesShown) {
		super(linesShown);
		
		this.hidden = true;
	}
	
	private boolean hidden;
	
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
