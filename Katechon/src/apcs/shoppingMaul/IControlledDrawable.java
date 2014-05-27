package apcs.shoppingMaul;

import java.awt.Graphics;

import apcs.katechon.engine.IEngineItem;

public interface IControlledDrawable extends IEngineItem
{
	public void draw(Graphics g, int x, int y);
	
	public int getX();
	public int getY();
}
