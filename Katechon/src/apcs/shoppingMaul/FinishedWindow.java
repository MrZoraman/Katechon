package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;

import apcs.katechon.windowingtoolkit.Button;
import apcs.katechon.windowingtoolkit.Message;
import apcs.katechon.windowingtoolkit.Window;

public class FinishedWindow extends Window
{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	
	private static final Font font = new Font("Arial", Font.PLAIN, 20);

	public FinishedWindow(int x, int y)
	{
		super(x, y, WIDTH, HEIGHT);
		this.setTitle("Finished!");
		Message msg = new Message("You found your target!", 10, 55, font, Color.GREEN);
		this.addDisplayable(msg);
		
		Button button = new Button(10, 100, 50, 20) {
			@Override
			public void onClick()
			{
				System.out.println("pop");
			}
		};
		
		this.addDisplayable(button);
	}

	@Override
	public void onClose()
	{
		super.onClose();
		
		
	}
}
