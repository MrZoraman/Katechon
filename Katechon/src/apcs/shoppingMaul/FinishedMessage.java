package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import apcs.katechon.KatechonEngine;
import apcs.katechon.rendering.IDrawable;

public class FinishedMessage implements IDrawable
{
	private boolean finished;
	
	private String message;
	
	private boolean visible;
	
	private final Font font;
	
	public FinishedMessage()
	{
		this.finished = false;
		this.font = new Font("Arial", Font.PLAIN, 40);
		this.message = "YOU FOUND HIM!";
	}
	
	@Override
	public boolean isFinished()
	{
		return finished;
	}
	
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}

	@Override
	public void draw(Graphics g)
	{
		if (this.visible)
		{
			g.setFont(font);
			g.setColor(Color.YELLOW);
			g.drawString(message, (KatechonEngine.getInstance().getSwingWindow().getWidth() / 2) - (g.getFontMetrics().stringWidth(message) / 2), (KatechonEngine.getInstance().getSwingWindow().getHeight() / 2) - g.getFontMetrics().getHeight());
		}
	}
	
	public void show()
	{
		this.visible = true;
	}
	
	public void hide()
	{
		this.visible = false;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
}
