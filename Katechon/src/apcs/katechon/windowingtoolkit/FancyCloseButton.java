package apcs.katechon.windowingtoolkit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;

import apcs.katechon.resources.JarImageLoader;

/**
 * A fancier version of the {@link apcs.katechon.windowingtoolkit.CloseButton CloseButton}
 * @author Matt
 */
//TODO: needs embellishment.
public class FancyCloseButton extends CloseButton
{
	public FancyCloseButton(Window window, int x, int y, int width, int height)
	{
		super(window, x, y, width, height);
		
		JarImageLoader jil = JarImageLoader.getInstance(KWT.class);
		buttonImage = Scalr.resize(jil.getImage("closeButton.png"), width, height);
	}
	
	private final BufferedImage buttonImage;

	@Override
	public void paint(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage(buttonImage, x, y, null);
	}
}
