package apcs.katechon.windowingtoolkit;

//TODO: make it actually do stuff
public class CloseButton extends Button
{
	public CloseButton(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	@Override
	public void onClick()
	{
		System.out.println("destroying!");
	}
	
//	@Override
//	public void draw(Graphics g)
//	{
//		super.draw(g);
//		
//		g.setColor(Color.WHITE);
//		
//		g.drawString("X", getX() + getWindow().getX(), getY() + getWindow().getY());
//	}
}
