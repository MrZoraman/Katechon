package apcs.katechon.windowingtoolkit;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import apcs.katechon.KatechonEngine;
import apcs.katechon.input.mouse.Mouse;
import apcs.katechon.rendering.IDrawable;

public class Window implements IDrawable
{
	private static final int BORDER_WIDTH = 6;
	private static final int WINDOW_BAR_HEIGHT = 30;
	
	private static final int CLOSE_BUTTON_SIZE = WINDOW_BAR_HEIGHT - 12;
	
	public Window(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		
		this.buttons = new HashSet<Button>();
		
		this.width = width;
		this.height = height;
		
		this.backgroundColor = new Color(53, 53, 53);
		this.borderColor = new Color(51, 102, 153);
		
		this.addButton(new CloseButton(width - CLOSE_BUTTON_SIZE - BORDER_WIDTH, BORDER_WIDTH, CLOSE_BUTTON_SIZE, CLOSE_BUTTON_SIZE));
	}

	private final int width;
	private final int height;
	
	private final Set<Button> buttons;
	
	private int x;
	private int y;
	
	private Color backgroundColor;
	private Color borderColor;
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(borderColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(backgroundColor);
		g.fillRect(x + BORDER_WIDTH, y + WINDOW_BAR_HEIGHT, width - (BORDER_WIDTH * 2), height - (BORDER_WIDTH + WINDOW_BAR_HEIGHT));
		
		for(Button button : buttons)
		{
			button.draw(g);
		}
	}
	
	public void addButton(Button button)
	{
		button.setWindow(this);
		buttons.add(button);
		Mouse.getInstance().addListener(button);
		KatechonEngine.getInstance().scheduleTask(button);
		//TODO: layer!
		KatechonEngine.getInstance().addDrawable(button, 1);
	}
	
	public void setBackgroundColor(Color color)
	{
		this.backgroundColor = color;
	}
	
	public void setBorderColor(Color color)
	{
		this.borderColor = color;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
