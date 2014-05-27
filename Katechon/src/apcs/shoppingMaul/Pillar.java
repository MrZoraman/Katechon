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
	
	public Pillar(int x, int y, int width, int height)
	{
		//No speed or control as these will not move
		super(x, y, width, height, 0, false);
		this.image = JarImageLoader.getInstance(ShoppingMaul.class).getImage("/apcs/shoppingMaul/assets/Pillar.png");
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
	public void onCollision(Set<Direction> types)
	{
		//This object will do nothing on collision.
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	@Override
	public int getX() {
		return super.x;
	}

	@Override
	public int getY() {
		return super.y;
	}

}
