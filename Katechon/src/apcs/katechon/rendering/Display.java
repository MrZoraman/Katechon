package apcs.katechon.rendering;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class Display
{
	private List<Layer> layers;
	
	//TODO: Should this hold its own instance of Graphics?
	
	public Display()
	{
		this.layers = new ArrayList<Layer>();
	}
	
	public void addLayer()
	{
		this.layers.add(new Layer());
	}
	
	public void removeLayer(int num)
	{
		//Zero based
		this.layers.remove(num);
	}
	
	public int getSize()
	{
		return this.layers.size();
	}
	
	public void drawLayers(Graphics g)
	{
		for (int i = 0; i < layers.size(); i++)
		{
			layers.get(i).drawAll(g);
		}
	}
	
	public Layer getLayer(int num)
	{
		return this.layers.get(num);
	}
}
