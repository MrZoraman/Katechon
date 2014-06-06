package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;

import apcs.katechon.windowingtoolkit.Button;
import apcs.katechon.windowingtoolkit.Message;
import apcs.katechon.windowingtoolkit.Window;

public class FinishedWindow extends Window
{
	private static final int WIDTH = 375;
	private static final int HEIGHT = 100;
	
	private static final Font font = new Font("Arial", Font.PLAIN, 20);

	public FinishedWindow(int x, int y)
	{
		super(x, y, WIDTH, HEIGHT);
		this.setTitle("Finished!");
		Message msg = new Message("You found your target!", 10, 55, font, Color.GREEN);
		this.addDisplayable(msg);
		
		Message continueMsg = new Message("Click this button to play again ->", 10, 80, font, Color.GREEN);
		this.addDisplayable(continueMsg);
		
		Button button = new Button(300, 62, 50, 20) {
			@Override
			public void onClick()
			{
				onClose();
			}
		};
		
		button.setPassiveColor(new Color(0, 204, 0));
		button.setMouseOverColor(new Color(0, 153, 0));
		button.setMouseHeldColor(Color.GREEN);
		
		this.addDisplayable(button);
	}

	@Override
	public void onClose()
	{
		super.onClose();
		
		
	}
}
