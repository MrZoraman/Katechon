package apcs.katechon.windowingtoolkit;

/**
 * A button that is used to close a window.
 * @author Matt
 */
public class CloseButton extends Button
{
	public CloseButton(Window window, int x, int y, int width, int height)
	{
		super(x, y, width, height, 1);
		
		this.window = window;
	}
	
	private final Window window;
	
	@Override
	public void onClick()
	{
		window.onClose();
	}
}
