package apcs.katechon.windowingtoolkit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

import apcs.katechon.resources.JarImageLoader;

public class CloseButton extends Button
{
	public CloseButton(Window window, int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.window = window;
		
		JarImageLoader jil = JarImageLoader.getInstance(KWT.class);
		buttonImage = Scalr.resize(jil.getImage("closeButton.png"), width, height);
	}
	
	private final Window window;
	private final BufferedImage buttonImage;
	
	@Override
	public void onClick()
	{
		window.onClose();
	}

	@Override
	public void paint(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage(buttonImage, x, y, null);
	}
}
