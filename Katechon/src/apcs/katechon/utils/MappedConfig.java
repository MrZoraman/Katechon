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
	private final Map<String, Byte> bytes;
	private final Map<String, Short> shorts;
	private final Map<String, Integer> ints;
	private final Map<String, Long> longs;

	private final Map<String, Float> floats;
	private final Map<String, Double> doubles;

	private final Map<String, Boolean> booleans;

	private final Map<String, Character> characters;
	private final Map<String, String> strings;
	
	public MappedConfig()
	{
		bytes 		= new HashMap<String, Byte>();
		shorts 		= new HashMap<String, Short>();
		ints 		= new HashMap<String, Integer>();
		longs 		= new HashMap<String, Long>();
		
		floats 		= new HashMap<String, Float>();
		doubles 	= new HashMap<String, Double>();
		
		booleans 	= new HashMap<String, Boolean>();
		
		characters 	= new HashMap<String, Character>();
		strings 	= new HashMap<String, String>();
	}
	
	//BYTES
	@Override
	public void setByte(String key, byte value)
	{
		bytes.put(key, value);
	}
	
	@Override
	public Byte getByte(String key)
	{
		return bytes.get(key);
	}
	
	//SHORTS
	@Override
	public void setShort(String key, short value)
	{
		shorts.put(key, value);
	}
	
	@Override
	public Short getShort(String key)
	{
		return shorts.get(key);
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
	
	//LONGS
	@Override
	public void setLong(String key, long value)
	{
		longs.put(key, value);
	}
	
	@Override
	public Long getLong(String key)
	{
		return longs.get(key);
	}
	
	//FLOATS
	@Override
	public void setFloat(String key, float value)
	{
		floats.put(key, value);
	}
	
	@Override
	public Float getFloat(String key)
	{
		return floats.get(key);
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
	
	//CHARACTERS
	@Override
	public void setCharacter(String key, char value)
	{
		characters.put(key, value);
	}
	
	@Override
	public Character getCharacter(String key)
	{
		return characters.get(key);
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
	
	
	
	
	
	//For general setting
	/**
	 * Used for general setting when the type of the object is not known.
	 */
	public void set(String key, Object obj)
	{
		if (obj instanceof Byte)
		{
			setByte(key, (Byte)(obj));
		}
		else if (obj instanceof Short)
		{
			setShort(key, (Short)(obj));
		}
		else if (obj instanceof Integer)
		{
			setInt(key, (Integer)(obj));
		}
		else if (obj instanceof Long)
		{
			setLong(key, (Long)(obj));
		}
		else if (obj instanceof Float)
		{
			setFloat(key, (Float)(obj));
		}
		else if (obj instanceof Double)
		{
			setDouble(key, (Double)(obj));
		}
		else if (obj instanceof Boolean)
		{
			setBoolean(key, (Boolean)(obj));
		}
		else if (obj instanceof Character)
		{
			setCharacter(key, (Character)(obj));
		}
		else if (obj instanceof String)
		{
			setString(key, (String)(obj));
		}
		else
		{
			//May want this to throw an exception
			System.out.println("Incorrect type used");
		}
	}
}
