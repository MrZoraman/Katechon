package apcs.shoppingMaul.colorEnums;

import java.awt.Color;

/**
 * Represents the different color options for a {@link apcs.shoppingMaul.Man Man's} hair.
 * @author Sean
 */
public enum HairColors
{
	WHITE 			(Color.WHITE),
	BLONDE 			(new Color(224, 211, 163)),
	ORANGE 			(Color.ORANGE),
	RED 			(Color.RED),
	BROWN 			(new Color(135, 84, 56)),
	DARK_BROWN 		(new Color(37, 23, 16)),
	BLACK			(Color.BLACK);
	
	
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
