package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import apcs.katechon.rendering.IDrawable;

public class FinishedMessage implements IDrawable
{
	private boolean finished;
	
	private final Font font;
	
	public FinishedMessage()
	{
		this.finished = false;
		this.font = new Font("Arial", Font.PLAIN, 40);
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
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("YOU FOUND HIM!", 450, 300);
	}
}
