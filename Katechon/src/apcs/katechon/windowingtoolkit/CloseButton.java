package apcs.katechon.windowingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

//TODO: make it actually do stuff
public class CloseButton extends Button
{
	public CloseButton(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	@Override
	public void onClick()
	{
		System.out.println("destroying!");
	}
	
	@Override
	public void draw(Graphics g)
	{
		super.draw(g);
		
		g.setColor(Color.WHITE);
		
		System.out.println("getX(): " + getX() + " getY(): " + getY());
		g.drawString("X", getX(), getY());
	}
}
