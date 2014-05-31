package apcs.katechon.windowingtoolkit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class WindowImage extends DisplayableBase
{
	private final BufferedImage image;
	
	private final int x;
	private final int y;
	
	private boolean isVisible;
	
	public WindowImage(BufferedImage image, int x, int y)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		this.isVisible = false;
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(isVisible)
		{
			g.drawImage(image, x + getWindow().getX(), y + getWindow().getY(), null);
		}
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.isVisible = visible;
	}
}
