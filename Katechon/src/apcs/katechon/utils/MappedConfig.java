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
	public void setInt(ConfigKey key, int value)
	{
		ints.put(key.toString(), value);
	}
	
	@Override
	public int getInt(ConfigKey key, int defaultValue)
	{
		return ints.containsKey(key.toString()) ? ints.get(key.toString()) : defaultValue;
	}

	//DOUBLES
	@Override
	public void setDouble(ConfigKey key, double value)
	{
		doubles.put(key.toString(), value);
	}
	
	@Override
	public double getDouble(ConfigKey key, double defaultValue)
	{
		return doubles.containsKey(key.toString()) ? doubles.get(key.toString()) : defaultValue;
	}
	
	//BOOLEANS
	@Override
	public void setBoolean(ConfigKey key, boolean value)
	{
		booleans.put(key.toString(), value);
	}
	
	@Override
	public boolean getBoolean(ConfigKey key, boolean defaultValue)
	{
		return booleans.containsKey(key.toString()) ? booleans.get(key.toString()) : defaultValue;
	}
	
	//STRINGS
	@Override
	public void setString(ConfigKey key, String value)
	{
		strings.put(key.toString(), value);
	}

	@Override
	public String getString(ConfigKey key, String defaultValue)
	{
		return strings.containsKey(key.toString()) ? strings.get(key.toString()) : defaultValue;
	}
}
