package apcs.katechon.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores configuration options with hashmaps. This config cannot be saved to the file system (yet).
 * @author Matt
 *
 */
public class MappedConfig implements IConfig 
{
	private final Map<String, Integer> ints;
	
	private final Map<String, Double> doubles;

	private final Map<String, Boolean> booleans;

	private final Map<String, String> strings;
	
	public MappedConfig()
	{
		ints 		= new HashMap<String, Integer>();
		
		doubles 	= new HashMap<String, Double>();
		
		booleans 	= new HashMap<String, Boolean>();
		
		strings 	= new HashMap<String, String>();
	}
	
	//INTS
	@Override
	public void setInt(String key, int value)
	{
		ints.put(key, value);
	}
	
	@Override
	public Integer getInt(String key)
	{
		return ints.get(key);
	}

	//DOUBLES
	@Override
	public void setDouble(String key, double value)
	{
		doubles.put(key, value);
	}
	
	@Override
	public Double getDouble(String key)
	{
		return doubles.get(key);
	}
	
	//BOOLEANS
	@Override
	public void setBoolean(String key, boolean value)
	{
		booleans.put(key, value);
	}
	
	@Override
	public Boolean getBoolean(String key)
	{
		return booleans.get(key);
	}
	
	//STRINGS
	@Override
	public void setString(String key, String value)
	{
		strings.put(key, value);
	}

	@Override
	public String getString(String key)
	{
		return strings.get(key);
	}
}