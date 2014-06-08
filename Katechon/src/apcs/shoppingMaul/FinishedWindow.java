package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;

import apcs.katechon.KatechonEngine;
import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
import apcs.katechon.windowingtoolkit.Message;
import apcs.katechon.windowingtoolkit.Window;

public class FinishedWindow extends Window
{
	private static final int WIDTH = 375;
	private static final int HEIGHT = 100;
	
	private static final Font font = new Font("Arial", Font.PLAIN, 20);
	
	class KeyBlocker implements KeyPressedListener {
		private boolean finished = false;

		@Override
		public boolean isFinished()
		{
			return finished;
		}

		@Override
		public void onKeyPressed(Keys key, char keyChar)
		{
			//does nothin'
		}

		@Override
		public void setFinished(boolean finished)
		{
			this.finished = finished;
		}
	}
	
	private final KeyBlocker keyBlocker = new KeyBlocker();
	
	private final ShoppingMaul game;

	public FinishedWindow(ShoppingMaul game, int x, int y)
	{
		super(x, y, WIDTH, HEIGHT);
		
		this.game = game;
		
		this.setTitle("Finished!");
		Message msg = new Message("You found your target!", 10, 55, font, Color.GREEN);
		this.addDisplayable(msg);
		
		Message continueMsg = new Message("Click this button to play again ->", 10, 80, font, Color.GREEN);
		this.addDisplayable(continueMsg);
		
		PlayAgainButton button = new PlayAgainButton(300, 62, 50, 20, this);
		
		button.setPassiveColor(new Color(0, 204, 0));
		button.setMouseOverColor(new Color(0, 153, 0));
		button.setMouseHeldColor(Color.GREEN);
		
		this.addDisplayable(button);
		
		
		Keyboard.getInstance().setExclusiveKeyListener(keyBlocker);
	}

	@Override
	public void onClose()
	{
		super.onClose();
		Keyboard.getInstance().setExclusiveKeyListener(null);
		keyBlocker.setFinished(true);
		KatechonEngine.getInstance().nuke();
		game.start(KatechonEngine.getInstance());
	}
}
