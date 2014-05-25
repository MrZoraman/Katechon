package apcs.shoppingMaul;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

import apcs.katechon.engine.collisions.CollisionType;
import apcs.katechon.resources.JarImageLoader;
import apcs.katechon.test.SimpleCollidable;

public class Pillar extends SimpleCollidable
{
	private BufferedImage image;
	
	public Pillar(int x, int y, int width, int height)
	{
		//No speed or control as these will not move
		super(x, y, width, height, 0, false);
		this.image = JarImageLoader.getInstance(ShoppingMaul.class).getImage("Pillar.png");
	}

	@Override
	public boolean isFinished()
	{
		return false;
	}

	@Override
	public int getTopFace()
	{
		return super.y;
	}

	@Override
	public int getBottomFace()
	{
		return super.y + super.height;
	}

	@Override
	public int getLeftFace()
	{
		return super.x;
	}

	@Override
	public int getRightFace()
	{
		return super.x + super.width;
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, super.x, super.y, super.width, super.height, null);
	}

	@Override
	public int getSpeed()
	{
		//These objects do not move
		return 0;
	}

	@Override
	public void onCollision(Set<CollisionType> types)
	{
		//This object will do nothing on collision.
	}

}