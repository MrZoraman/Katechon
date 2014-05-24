package apcs.katechon.windowingtoolkit;

//TODO: make it actually do stuff
public class CloseButton extends Button
{
	private static final int SIZE = Window.WINDOW_BAR_HEIGHT - 4;
	
	public CloseButton(Window window) {
		super(
				SIZE, 
				SIZE, 
				window.getWidth() - SIZE, 
				window.getHeight() - Window.WINDOW_BAR_HEIGHT);
	}

	@Override
	public void onClick() {
		System.out.println("destroying!");
	}
}
