package apcs.shoppingMaul;

import apcs.katechon.rendering.IDrawable;

/**
 * Similar to the IDrawable interface, but the implementation is told where to draw it's stuff. It is expected to supplied coordinates so whatever it is plugged into can
 * @author Matt
 *
 */
public interface IControlledDrawable extends IDrawable
{
	public int setX(int x);
	public int setY(int y);
}
