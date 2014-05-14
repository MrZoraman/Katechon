package apcs.katechon.rendering;

import java.awt.Graphics;

public interface IDrawable
{
	public void draw(Graphics g);
	
	public void changeSize(int width, int height);
	
	public void changePosition(int x, int y);
	
	public void update();
}
