package apcs.katechon.rendering;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

/**
 * A single stage of the game. (e.g. Splash screen, Menu, Main game)
 * @author sn300035
 */
public class Display
{
	private List<Layer> layers;
	
	private Graphics g;
	
	/**
	 * Creates a new Display.
	 * @param graphics The graphics that this Display will use for all drawing.
	 */
	public Display(Graphics graphics)
	{
		this.layers = new ArrayList<Layer>();
	}
	
	/**
	 * Adds a {@link apcs.katechon.rendering.Layer Layer} to this display.
	 */
	public void addLayer()
	{
		this.layers.add(new Layer());
	}
	
	/**
	 * Removes a {@link apcs.katechon.rendering.Layer Layer} from this display
	 * @param num The {@link apcs.katechon.rendering.Layer Layer} level to remove. (This is zero based)
	 */
	public void removeLayer(int num)
	{
		//Zero based
		this.layers.remove(num);
	}
	
	/**
	 * Gets the number of {@link apcs.katechon.rendering.Layer Layers} in this Display
	 * @return
	 */
	public int numLayers()
	{
		return this.layers.size();
	}
	
	/**
	 * Draws all of the layers in this Display
	 */
	public void drawLayers()
	{
		for (int i = 0; i < layers.size(); i++)
		{
			layers.get(i).drawAll(this.g);
		}
	}
	
	/**
	 * Gets a {@link apcs.katechon.rendering.Layer Layer} from this Display's {@link apcs.rendering.Layer Layers}
	 * @param num The level of the {@link apcs.katechon.rendering.Layer Layer} to get. (This is zero based)
	 * @return The {@link apcs.katechon.rendering.Layer Layer} at the given level.
	 */
	public Layer getLayer(int num)
	{
		return this.layers.get(num);
	}
}
