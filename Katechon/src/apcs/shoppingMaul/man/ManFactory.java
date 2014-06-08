package apcs.shoppingMaul.man;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import apcs.shoppingMaul.colorEnums.HairColors;
import apcs.shoppingMaul.colorEnums.HandColors;
import apcs.shoppingMaul.colorEnums.ShoeColors;

public class ManFactory
{
	public static Man makeMan(int x, int y, int speed)
	{
		Random r = new Random();
		
		Color hairColor = HairColors.values()[r.nextInt(HairColors.values().length - 1)].toColor();
		Color shirtColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		Color handColor = HandColors.values()[r.nextInt(HandColors.values().length - 1)].toColor();
		Color shoeColor = ShoeColors.values()[r.nextInt(ShoeColors.values().length - 1)].toColor();
		
		return new Man(x, y, speed, hairColor, shirtColor, handColor, shoeColor);
	}
	
	public static Set<Man> makeSomeMen(int maxX, int maxY, int num, int speed)
	{
		Random r = new Random();
		Set<Man> men = new HashSet<Man>();
		
		for (int i = 0; i < num; i++)
		{
			men.add(makeMan(r.nextInt(maxX), r.nextInt(maxY), speed));
		}
		
		return men;
	}
}
