package apcs.katechon.utils;

/**
 * This represents something that will store and retrieve data.
 * @author Matt
 *
 */
public interface IConfig
{
	public void setByte			(String key, byte value);
	public void setShort		(String key, short value);
	public void setInt			(String key, int value);
	public void setLong			(String key, long value);
	
	public void setFloat		(String key, float value);
	public void setDouble		(String key, double value);
	
	public void setBoolean		(String key, boolean value);
	
	public void setCharacter	(String key, char value);
	public void setString		(String key, String value);
	
	//Wrapper classes are returned, because it is a possibility that null values are returned if a key does not exist
	public Byte getByte				(String key);
	public Short getShort			(String key);
	public Integer getInt			(String key);
	public Long getLong				(String key);
	
	public Float getFloat			(String key);
	public Double getDouble			(String key);
	
	public Boolean getBoolean		(String key);
	
	public Character getCharacter	(String key);
	public String getString			(String key);
	
	

	//For general setting
	public void set(String key, Object obj);
}
