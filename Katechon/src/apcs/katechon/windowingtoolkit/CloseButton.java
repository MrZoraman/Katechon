package apcs.katechon.windowingtoolkit;

//TODO: make it actually do stuff
public class CloseButton extends Button
{
	public CloseButton(Window window, int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		this.window = window;
	}
	
	private final Window window;
	
	@Override
	public void onClick()
	{
		window.onClose();
	}
}
