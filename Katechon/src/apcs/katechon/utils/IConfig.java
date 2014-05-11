package apcs.katechon.utils;


/**
 * This represents something that will store and retrieve data.
 * @author Matt
 *
 */
public interface IConfig
{
	public void setInt			(String key, int value);
	
	public void setDouble		(String key, double value);
	
	public void setBoolean		(String key, boolean value);
	
	public void setString		(String key, String value);
	
	//Wrapper classes are returned, because it is a possibility that null values are returned if a key does not exist
	public int getInt			(String key, int defaultValue);
	
	public double getDouble			(String key, double defaultValue);
	
	public boolean getBoolean		(String key, boolean defaultValue);
	
	public String getString			(String key, String defaultValue);
}
