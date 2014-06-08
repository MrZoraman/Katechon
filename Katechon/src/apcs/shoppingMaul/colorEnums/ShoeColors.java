package apcs.shoppingMaul.colorEnums;

import java.awt.Color;

/**
 * Represents the different color options for a {@link apcs.shoppingMaul.man.Man Man's} shoes.
 * @author Sean
 */
public enum ShoeColors
{
	WHITE			(new Color(254, 254, 254)),
	BROWN 			(new Color(135, 84, 56)),
	DARK_BROWN 		(new Color(37, 23, 16)),
	BLACK			(new Color(1, 1, 1));
	
	
	
	private Color color;
	
	ShoeColors(Color color)
	{
		this.color = color;
	}
	
	/**
	 * @return The {@link java.awt.Color Color} object that this represents.
	 */
	public Color toColor()
	{
		return color;
	}
}
