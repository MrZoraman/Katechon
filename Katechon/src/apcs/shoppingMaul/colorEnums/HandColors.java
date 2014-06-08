package apcs.shoppingMaul.colorEnums;

import java.awt.Color;

/**
 * Represents the different color options for a {@link apcs.shoppingMaul.man.Man Man's} hand.
 * @author Sean
 */
public enum HandColors
{
	TAN				(new Color(224, 169, 133)),
	BROWN 			(new Color(135, 84, 56)),
	DARK_BROWN 		(new Color(37, 23, 16));
	
	private Color color;
	HandColors(Color color)
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
