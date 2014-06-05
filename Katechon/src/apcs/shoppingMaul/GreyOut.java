package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Graphics;

import apcs.katechon.KatechonEngine;
import apcs.katechon.rendering.IDrawable;

/**
 * Greys out the screen with an alpha value of 125
 * @author Sean
 *
 */
public class GreyOut implements IDrawable
{	
	public GreyOut()
	{
		
	}

	private boolean isFinished = false;
	@Override
	public boolean isFinished()
	{
		return isFinished;
	}

	@Override
	public void setFinished(boolean finished)
	{
		this.isFinished = finished;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(new Color(0, 0, 0, 125));
		g.fillRect(0, 0, KatechonEngine.getInstance().getSwingWindow().getWidth(), KatechonEngine.getInstance().getSwingWindow().getHeight());
	}

}
