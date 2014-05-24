package apcs.katechon.windowingtoolkit;

import apcs.katechon.rendering.IDrawable;

//TODO: documentation
public interface IDisplayable extends IDrawable
{
	public void setVisible(boolean visible);
	
	public void setFinished(boolean finished);
}
