package apcs.shoppingMaul.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.utils.Utils;
import apcs.shoppingMaul.Board;
import apcs.shoppingMaul.IControlledDrawable;
import apcs.shoppingMaul.man.Man;
import apcs.shoppingMaul.man.ManFactory;

public class GameMap
{	
	private static final int SIZE = 600;
	
	private final char[][] tiles;
	private int x;
	private int y;
	
	private final int orig_x;
	private final int orig_y;
	
	private int amountOfFloorTiles;
	
	public GameMap(Class<?> clazz, String path, int x, int y) throws IOException
	{
		this.amountOfFloorTiles = 0;
		
		InputStream is = clazz.getResourceAsStream(path);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String strLine;
		List<String> lines = new ArrayList<String>();
		
		while((strLine = br.readLine()) != null)
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
			}
		}
		
		this.x = x;
		this.y = y;
		
		this.orig_x = x;
		this.orig_y = y;
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
					amountOfFloorTiles++;
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
			
			x = orig_x;
			
			
			y += SIZE;
		}
		
		y = orig_y;
	}
	
	public Set<Man> spawnMen(int amount, int speed)
	{
		int amountPerSquare = (amount / amountOfFloorTiles) < 1 ? 1 : amount / amountOfFloorTiles;
		Set<Man> men = new HashSet<Man>();
		
		int x = orig_x;
		int y = orig_y;
		
		for(int r = 0; r < tiles.length; r++)
		{
			for(int c = 0; c < tiles[r].length; c++)
			{
				if(tiles[r][c] == 'F')
				{
					for(int ii = 0; ii < amountPerSquare; ii++)
					{
						
						Random rand = new Random();
						Man man = ManFactory.makeMan(x + rand.nextInt(SIZE), y + rand.nextInt(SIZE), speed);
						men.add(man);
					}
				}
				
				x += SIZE;
			}
			
			x = orig_x;
			
			y += SIZE;
		}
		
		//Max number of men should be 150 to improve performance.
		while (men.size() > 150)
		{
			men.remove(Utils.getRandomItem(men));
		}
		
		return men;
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
