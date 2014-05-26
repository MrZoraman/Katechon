package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.Set;

import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.basicGameObjects.ControllableCollidable;
import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.rendering.IDrawable;

public class LeopardPack extends ControllableCollidable implements IDrawable
{
	private final SnowLeopard[] leopards;
	
	public LeopardPack(ControlScheme controlScheme, int x, int y, int speed, int size)
	{
		super(controlScheme, x, y, 0, 0, speed);
		
		leopards = new SnowLeopard[size];
		
		for(int ii = 0; ii < leopards.length; ii++)
		{
			leopards[ii] = new SnowLeopard(10, 10, speed);
		}
	}

	@Override
	public void draw(Graphics g)
	{
	}
	
	@Override
	public void onCollision(Set<Direction> collisions)
	{
		super.onCollision(collisions);
		
		//this is where we kill the target
	}
}
