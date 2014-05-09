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
	public Integer getInt			(String key);
	
	public Double getDouble			(String key);
	
	public Boolean getBoolean		(String key);
	
	public String getString			(String key);
}
