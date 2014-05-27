package apcs.shoppingMaul;

import java.awt.Color;
import java.util.Random;

import apcs.shoppingMaul.colorEnums.HairColors;
import apcs.shoppingMaul.colorEnums.HandColors;
import apcs.shoppingMaul.colorEnums.ShoeColors;

public class ManFactory
{
	public static Man makeMan(int x, int y, int width, int height, int speed)
	{
		Random r = new Random();
		
		Color hairColor = HairColors.values()[r.nextInt(HairColors.values().length - 1)].toColor();
		Color shirtColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		Color handColor = HandColors.values()[r.nextInt(HandColors.values().length - 1)].toColor();
		Color shoeColor = ShoeColors.values()[r.nextInt(ShoeColors.values().length - 1)].toColor();
		
		return new Man(x, y, width, height, speed, hairColor, shirtColor, handColor, shoeColor);
	}
}
