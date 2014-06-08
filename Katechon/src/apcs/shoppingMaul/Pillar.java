package apcs.shoppingMaul;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.resources.JarImageLoader;
import apcs.katechon.test.SimpleCollidable;

/**
 * This is an object that is placed on the map that cannot be passed through
 * @author Sean
 *
 */
public class Pillar extends SimpleCollidable implements IControlledDrawable
{
	private BufferedImage image;
	
	private int realX;
	private int realY;
	
	private final String id;
	
	public Pillar(int x, int y, int width, int height, String id)
	{
		//No speed or control as these will not move
		super(x, y, width, height, 0, false);
		this.image = JarImageLoader.getInstance(ShoppingMaul.class).getImage("/apcs/shoppingMaul/assets/Pillar.png");
		
		this.realX = x;
		this.realY = y;
		
		this.id = id;
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
		g.drawImage(image, super.x, super.y, null);
	}

	@Override
	public int getSpeed()
	{
		//These objects do not move
		return 0;
	}

	@Override
	public void onCollision(Set<Direction> types)
	{
		//This object will do nothing on collision.
	}

	@Override
	public int getX() {
		return super.x;
	}

	@Override
	public int getY() {
		return super.y;
	}

	@Override
	public void setX(int x)
	{
		this.x = x;
	}

	@Override
	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public int getRealX()
	{
		return realX;
	}

	@Override
	public int getRealY()
	{
		return realY;
	}

	@Override
	public String toString()
	{
		return id;
	}
}
