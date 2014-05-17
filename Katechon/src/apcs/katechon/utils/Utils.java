package apcs.katechon.utils;

import java.sql.Timestamp;
import java.util.Date;

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
	
	public static String getTimeStamp()
	{
		Date date = new Date();
		Timestamp stamp = new Timestamp(date.getTime());
		return stamp.toString();
	}
}
