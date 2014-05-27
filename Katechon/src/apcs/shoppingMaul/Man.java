package apcs.shoppingMaul;

import java.awt.Color;
import java.util.Set;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;

/**
 * A person who will walk around the shopping mall.
 * @author Sean
 *
 */
public class Man implements ICollidable
{
	@SuppressWarnings("unused")
	private final Color hairColor;
	private final Color shirtColor;
	private final Color handColor;
	private final Color shoeColor;
	
	private int x, y, width, height, speed;
	
	public Man(int x, int y, int width, int height, int speed, Color hairColor, Color shirtColor, Color handColor, Color shoeColor)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hairColor = hairColor;
		this.shirtColor = shirtColor;
		this.handColor = handColor;
		this.shoeColor = shoeColor;
	}

	@Override
	public int getTopFace() {
		return this.y;
	}

	@Override
	public int getBottomFace() {
		return this.y + this.height;
	}

	@Override
	public int getLeftFace() {
		return this.x;
	}

	@Override
	public int getRightFace() {
		return this.x + this.width;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public void onCollision(Set<Direction> types)
	{
		/*
		 * This could be problematic as you cannot tell what has collided with this.
		 */
	}
	
	@Override
	public boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
