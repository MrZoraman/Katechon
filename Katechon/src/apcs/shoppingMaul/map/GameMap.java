package apcs.shoppingMaul.map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import apcs.katechon.logging.Log;

public class GameMap
{	
	private final char[][] tiles;
	
	public GameMap(FileInputStream inputStream) throws IOException
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
		
		this.tiles = tiles;
	}
	

//	private GameMap(char[][] tiles)
//	{
//		Log.info("Game map constructed!");
//		for(int r = 0; r < tiles.length; r++)
//		{
//			for(int c = 0; c < tiles[r].length; c++)
//			{
//				System.out.print(tiles[r][c] + " ");
//			}
//			
//			System.out.print("\n\n");
//		}
//	}
}
