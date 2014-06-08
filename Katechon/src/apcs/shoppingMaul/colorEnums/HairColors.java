package apcs.shoppingMaul.colorEnums;

import java.awt.Color;

/**
 * Represents the different color options for a {@link apcs.shoppingMaul.man.Man Man's} hair.
 * @author Sean
 */
public enum HairColors
{
	WHITE 			(new Color(254, 254, 254)),
	BLONDE 			(new Color(224, 211, 163)),
	ORANGE 			(new Color(254, 127, 39)),
	RED 			(new Color(254, 1, 1)),
	BROWN 			(new Color(135, 84, 56)),
	DARK_BROWN 		(new Color(37, 23, 16)),
	BLACK			(new Color(1, 1, 1));
	
	
	private Color color;
	HairColors(Color color)
	{
		this.color = color;
	}
	
	/**
	 * @return The {@link java.awt.Color Color} object that this represents.
	 */
	public Color toColor()
	{
		return this.color;
	}
}
