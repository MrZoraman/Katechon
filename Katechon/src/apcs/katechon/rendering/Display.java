package apcs.katechon.rendering;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

/**
 * A single stage of the game. (e.g. Splash screen, Menu, Main game)
 * @author Sean
 */
public class Display
{
	private final List<Layer> layers;
	
	/**
	 * Creates a new Display.
	 */
	public Display(int amountOfLayers)
	{
		this.layers = new ArrayList<Layer>();
		
		for(int ii = 0; ii < amountOfLayers; ii++)
		{
			this.addLayer();
		}
	}
	
	/**
	 * Adds a {@link apcs.katechon.rendering.Layer Layer} to this display.
	 */
	private void addLayer()
	{
		this.layers.add(new Layer());
	}
	
//	/**
//	 * Removes a {@link apcs.katechon.rendering.Layer Layer} from this display
//	 * @param num The {@link apcs.katechon.rendering.Layer Layer} level to remove. (This is zero based)
//	 */
//	public void removeLayer(int num)
//	{
//		//Zero based
//		this.layers.remove(num);
//	}
	
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
	 * @param The Graphics to be used by this display
	 */
	public void drawLayers(Graphics g)
	{
		for (int i = 0; i < layers.size(); i++)
		{
			layers.get(i).drawAll(g);
		}
	}
	
	/**
	 * Gets a {@link apcs.katechon.rendering.Layer Layer} from this Display's {@link apcs.rendering.Layer Layers}
	 * @param num The level of the {@link apcs.katechon.rendering.Layer Layer} to get. (This is zero based)
	 * @return The {@link apcs.katechon.rendering.Layer Layer} at the given level.
	 */
	public Layer getLayer(int num)
	{
//		if(numLayers() > num || num < 0)
//			throw new IndexOutOfBoundsException("There is no level " + num + " layer!");
		
		return this.layers.get(num);
	}
}
