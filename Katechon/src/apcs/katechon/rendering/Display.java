package apcs.katechon.rendering;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import apcs.katechon.windowingtoolkit.KWT;

/**
 * A single stage of the game. (e.g. Splash screen, Menu, Main game)
 * @author Sean
 */
public class Display
{
	private final List<Layer> layers;
	
	private Layer kwtLayer;
	
	/**
	 * Creates a new Display.
	 */
	public Display(int amountOfLayers)
	{
		this.layers = new ArrayList<Layer>();
		kwtLayer = KWT.getInstance();
		
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
		
		if(kwtLayer != null)
		{
			kwtLayer.drawAll(g);
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
	
	public void initKWTLayer(KWT kwt)
	{
		this.kwtLayer = kwt;
	}
	
	public void clearAllLayers()
	{
		for(Layer l : layers)
		{
			l.clearAll();
		}
	}
}
