package apcs.shoppingMaul.colorEnums;

import java.awt.Color;

public enum ShoeColors
{
	WHITE			(Color.WHITE),
	BROWN 			(new Color(135, 84, 56)),
	DARK_BROWN 		(new Color(37, 23, 16)),
	BLACK			(Color.BLACK);
	
	
	
	private Color color;
	
	ShoeColors(Color color)
	{
		this.color = color;
	}
	
	public Color toColor()
	{
		return color;
	}
}
