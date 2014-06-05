package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import apcs.katechon.input.keyboard.KeyPressedListener;
import apcs.katechon.input.keyboard.Keyboard;
import apcs.katechon.input.keyboard.Keys;
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
	private KeyPressedListener listener;
	private ShoppingMaul shoppingMaul;
	
	public PlayAgainButton(int x, int y, int width, int height, ShoppingMaul shoppingMaul)
	{
		super(x, y, width, height, 5);
		
		
		this.shoppingMaul = shoppingMaul;
		listener = new KeyPressedListener(){

			private boolean isFinished = false;
			@Override
			public boolean isFinished()
			{
				return isFinished;
			}

			@Override
			public void onKeyPressed(Keys key, char keyChar)
			{
				//do nothing
			}

			@Override
			public void setFinished(boolean finished)
			{
				this.isFinished = finished;
			}
			
		};
		
		Keyboard.getInstance().setExclusiveKeyListener(listener);
		
		this.x = x;
		this.y = y;
		
		this.setVisible(true);
		
		this.setWindow(new Window(0, 0, width, height, false));
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
		
		this.setWindow(new Window(0, 0, metrics.stringWidth(message), metrics.getHeight(), false));
		
		this.setWidth(metrics.stringWidth(message));;
		this.setHeight(metrics.getHeight());
		
		super.draw(g);	
		
		g.setColor(Color.BLACK);
		g.drawString(message, x, y + metrics.getHeight() - 9);
	}

	@Override
	public void onClick()
	{		
		this.setVisible(false);
		shoppingMaul.hideFinishedMessage();
		
		Keyboard.getInstance().setExclusiveKeyListener(null);
	}

}
