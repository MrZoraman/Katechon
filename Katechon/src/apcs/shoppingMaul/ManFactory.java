package apcs.shoppingMaul;

import java.awt.Color;
import java.util.Random;

public class ManFactory
{
	public static Man makeMan(int x, int y, int width, int height, int speed)
	{
		Random r = new Random();
		
		Color hairColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		Color shirtColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		Color handColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		Color shoeColor = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		
		return new Man(x, y, width, height, speed, hairColor, shirtColor, handColor, shoeColor);
	}
}
