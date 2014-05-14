package apcs.katechon.utils;


/**
 * This represents something that will store and retrieve data.
 * @author Matt
 *
 */
public interface IConfig
{
	/**
	 * Sets an int in the config
	 * @param key The key to store the int back. This key will be used to reference the stored int in the cooresponding getter method.
	 * @param value The value to be s tored
	 */
	public void setInt(ConfigKey key, int value);
	
	/**
	 * Sets a double in the config
	 * @param key The key to store the int back. This key will be used to reference the stored int in the cooresponding getter method.
	 * @param value The value to be s tored
	 */
	public void setDouble(ConfigKey key, double value);
	
	/**
	 * Sets a boolean in the config
	 * @param key The key to store the int back. This key will be used to reference the stored int in the cooresponding getter method.
	 * @param value The value to be s tored
	 */
	public void setBoolean(ConfigKey key, boolean value);
	
	/**
	 * Sets a string in the config
	 * @param key The key to store the int back. This key will be used to reference the stored int in the cooresponding getter method.
	 * @param value The value to be s tored
	 */
	public void setString(ConfigKey key, String value);
	
	/**
	 * Returns an int value stored in the config
	 * @param key The key value that points to the int requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The int value located at a given key
	 */
	public int getInt(ConfigKey key, int defaultValue);
	
	/**
	 * Returns a double value stored in the config
	 * @param key The key value that points to the double requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The double value located at a given key
	 */
	public double getDouble(ConfigKey key, double defaultValue);
	
	/**
	 * Returns a boolean value stored in the config
	 * @param key The key value that points to the boolean requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The boolean value located at a given key
	 */
	public boolean getBoolean(ConfigKey key, boolean defaultValue);
	
	/**
	 * Returns a string value stored in the config
	 * @param key The key value that points to the string requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The string value located at a given key
	 */
	public String getString(ConfigKey key, String defaultValue);
}
