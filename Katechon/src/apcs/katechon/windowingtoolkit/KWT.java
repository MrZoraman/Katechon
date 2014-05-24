package apcs.katechon.windowingtoolkit;

import apcs.katechon.rendering.Layer;

public class KWT extends Layer
{
	private static KWT instance = null;
	
	public static synchronized KWT getInstance()
	{
		if (instance == null)
		{
			instance = new KWT();
		}
		
		return instance;
	}
	
	private KWT()
	{
		super();
	}
}
