package apcs.shoppingMaul.map;

import java.awt.Color;
import java.awt.Graphics;

import apcs.shoppingMaul.SimpleBoardCollidable;

/**
 * A simple non-moving collidable.
 * @author Matt
 */
public class Wall extends SimpleBoardCollidable
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	
	private static final int SPEED = 0;
	
	public Wall(int x, int y) {
		super(x, y, WIDTH, HEIGHT, SPEED, false);
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		
		g.fillRect(x, y, width, height);
	}
	
}
