package apcs.shoppingMaul.colorEnums;

import java.awt.Color;

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
	
	public Color toColor()
	{
		return this.color;
	}
}
