package apcs.katechon.utils;

import java.util.Random;
import java.util.Set;

/**
 * For extremely miscellaneous methods
 * @author Matt
 *
 */
public class Utils
{
	/**
	 * Static class
	 */
	private Utils()
	{
	}
	
	public static <T> T getRandomItem(Set<T> items)
	{
		int ii = 0;
		int num = new Random().nextInt(items.size());
		
		for(T item : items)
		{
			if(ii == num)
			{
				return item;
			}
			else
			{
				ii++;
			}
		}
		
		return items.iterator().next();
	}
}
