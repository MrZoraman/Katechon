package apcs.shoppingMaul.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import apcs.katechon.resources.JarImageLoader;
import apcs.shoppingMaul.IControlledDrawable;
import apcs.shoppingMaul.ShoppingMaul;

/**
 * A tile that can be drawn to the screen.
 * @author Matt
 */
public class FloorTile implements IControlledDrawable
{
	private final int realX;
	private final int realY;
	
	private final BufferedImage image;
	
	private int x;
	private int y;
	
	private boolean finished;
	
	public FloorTile(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.image = JarImageLoader.getInstance(ShoppingMaul.class).getImage("/apcs/shoppingMaul/assets/tiles.jpg");
		
		this.realX = x;
		this.realY = y;
		
		this.finished = false;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	@Override
	public boolean isFinished() {
		return finished;
	}
	@Override
	public void setX(int x) {
		this.x = x;
	}
	@Override
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public int getRealX() {
		return realX;
	}
	@Override
	public int getRealY() {
		return realY;
	}

	@Override
	public int getWidth()
	{
		return 600;
	}

	@Override
	public int getHeight()
	{
		return 600;
	}

	@Override
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
}
