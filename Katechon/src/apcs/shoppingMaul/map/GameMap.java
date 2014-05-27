package apcs.shoppingMaul.map;

import java.util.List;

import apcs.katechon.fileManagement.File;
import apcs.katechon.logging.Log;

public class GameMap
{	
	public static GameMap createGameMap(File file)
	{
		List<String> lines = file.readLines();
		
		//find widest line
		int width = 0;
		for(String line : lines)
		{
			if(width < line.length())
			{
				width = line.length();
			}
		}
		
		char[][] tiles = new char[lines.size()][width];
		
		//populate tiles
		//rows
		for(int r = 0; r < 0; r++)
		{
			char[] chars = lines.get(r).toCharArray();
			//columns
			for(int c = 0; c < tiles[r].length; c++)
			{
				if(chars.length < c)
				{
					tiles[r][c] = chars[c];
				}
				else
				{
					tiles[r][c] = ' ';
				}
			}
		}
		
		return new GameMap(tiles);
	}
	

	private GameMap(char[][] tiles)
	{
		Log.info("Game map constructed!");
		for(int r = 0; r < tiles.length; r++)
		{
			for(int c = 0; c < tiles[r].length; c++)
			{
				System.out.print(tiles[r][c] + " ");
			}
			
			System.out.print("\n\n");
		}
	}
}
