package apcs.shoppingMaul.map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.shoppingMaul.Board;
import apcs.shoppingMaul.IControlledDrawable;

public class GameMap
{	
	private static final int SIZE = 600;
	
	private final char[][] tiles;
	private int x;
	private int y;
	
	public GameMap(FileInputStream inputStream, int x, int y) throws IOException
	{
		DataInputStream in = new DataInputStream(inputStream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String strLine;
		List<String> lines = new ArrayList<String>();
		
		while((strLine = reader.readLine()) != null)
		{
			lines.add(strLine);
		}
		
		//find widest line
		int width = 0;
		for(String line : lines)
		{
			if(width < line.length())
			{
				width = line.length();
			}
		}
		System.out.println("widest line: " + width);
		
		tiles = new char[lines.size()][width];
		
		//populate tiles
		//rows
		for(int r = 0; r < lines.size(); r++)
		{
			char[] chars = lines.get(r).toCharArray();
			//columns
			for(int c = 0; c < chars.length; c++)
			{
				tiles[r][c] = chars[c];
				System.out.println("added " + chars[c]);
			}
		}
		
		this.x = x;
		this.y = y;
	}
	
	public void insertMap(Board board)
	{
		for(int r = 0; r < tiles.length; r++)
		{
			
			for(int c = 0; c < tiles[r].length; c++)
			{
				IControlledDrawable icd = null;
				
				switch (tiles[r][c])
				{
				case 'F':
					icd = new FloorTile(x, y);
					break;
				default:
					Wall wall = new Wall(x, y);
					icd = wall;
					EngineManager.getInstance().getEngine(ICollidable.class).addItem(wall);
					break;
				}
				
				board.addDrawable(icd);
				
				x += SIZE;
			}
			
			x = 0;
			
			
			y += SIZE;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(int r = 0; r < tiles.length; r++)
		{
			for(int c = 0; c < tiles[r].length; c++)
			{
				builder.append(tiles[r][c]).append(" ");
			}
			
			builder.append("\n\n");
		}
		
		return builder.toString();
	}
}
