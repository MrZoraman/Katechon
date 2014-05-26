package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.basicGameObjects.ControllableCollidable;
import apcs.katechon.rendering.IDrawable;

public class Player extends ControllableCollidable implements IDrawable
{
	private final Set<SnowLeopard> leopards;
	
	public Player(ControlScheme controlScheme, int x, int y, int speed)
	{
		super(controlScheme, x, y, 0, 0, speed);
		
		leopards = new HashSet<SnowLeopard>();
	}

	@Override
	public void draw(Graphics g)
	{
	}
}
