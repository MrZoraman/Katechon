package apcs.shoppingMaul;

import apcs.katechon.rendering.IDrawable;

public interface IControlledDrawable extends IDrawable
{
	public void setX(int x);
	public void setY(int y);
	
	public int getX();
	public int getY();
	
	public int getRealX();
	public int getRealY();
}
