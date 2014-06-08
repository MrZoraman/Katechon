package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import apcs.katechon.windowingtoolkit.Button;
import apcs.katechon.windowingtoolkit.Window;

/**
* A button used to play again.
* @author Sean
*/
public class PlayAgainButton extends Button
{
	private final String message = "Play Again?";
	private final int x, y;
	private Window window;
	
	public PlayAgainButton(int x, int y, int width, int height, Window window)
	{
		super(x, y, width, height);
		
		this.window = window;
		
		this.x = x;
		this.y = y;
		
		this.setVisible(true);
		
		this.setWindow(window);
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.setFont(new Font("Arial", Font.BOLD, 30));
	
		if (!this.isVisible())
		{
			return;
		}
		
		FontMetrics metrics = g.getFontMetrics();
		
//		this.setWidth(metrics.stringWidth(message));;
//		this.setHeight(metrics.getHeight());
		
		super.draw(g);	
		
		g.setColor(Color.BLACK);
		g.drawString(message, x, y + metrics.getHeight() - 9);
	}
	
	@Override
	public void onClick()
	{	
		this.setVisible(false);
//		shoppingMaul.hideFinishedMessage();
		
		this.window.onClose();
	}

}