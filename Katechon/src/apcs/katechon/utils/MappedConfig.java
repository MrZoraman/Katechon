package apcs.katechon.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores configuration options with hashmaps. This config cannot be saved to the file system. This config does not write default values to the config either.
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
	public int getInt(String key, int defaultValue)
	{
		return ints.containsKey(key) ? ints.get(key) : defaultValue;
	}

	//DOUBLES
	@Override
	public void setDouble(String key, double value)
	{
		doubles.put(key, value);
	}
	
	@Override
	public double getDouble(String key, double defaultValue)
	{
		return doubles.containsKey(key) ? doubles.get(key) : defaultValue;
	}
	
	//BOOLEANS
	@Override
	public void setBoolean(String key, boolean value)
	{
		booleans.put(key, value);
	}
	
	@Override
	public boolean getBoolean(String key, boolean defaultValue)
	{
		return booleans.containsKey(key) ? booleans.get(key) : defaultValue;
	}
	
	//STRINGS
	@Override
	public void setString(String key, String value)
	{
		strings.put(key, value);
	}

	@Override
	public String getString(String key, String defaultValue)
	{
		return strings.containsKey(key) ? strings.get(key) : defaultValue;
	}
}
