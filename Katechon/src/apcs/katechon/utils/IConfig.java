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
	public void setInt(String key, int value);
	
	/**
	 * Sets a double in the config
	 * @param key The key to store the int back. This key will be used to reference the stored int in the cooresponding getter method.
	 * @param value The value to be s tored
	 */
	public void setDouble(String key, double value);
	
	/**
	 * Sets a boolean in the config
	 * @param key The key to store the int back. This key will be used to reference the stored int in the cooresponding getter method.
	 * @param value The value to be s tored
	 */
	public void setBoolean(String key, boolean value);
	
	/**
	 * Sets a string in the config
	 * @param key The key to store the int back. This key will be used to reference the stored int in the cooresponding getter method.
	 * @param value The value to be s tored
	 */
	public void setString(String key, String value);
	
	/**
	 * Returns an int value stored in the config
	 * @param key The key value that points to the int requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The int value located at a given key
	 */
	public int getInt(String key, int defaultValue);
	
	/**
	 * Returns a double value stored in the config
	 * @param key The key value that points to the double requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The double value located at a given key
	 */
	public double getDouble(String key, double defaultValue);
	
	/**
	 * Returns a boolean value stored in the config
	 * @param key The key value that points to the boolean requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The boolean value located at a given key
	 */
	public boolean getBoolean(String key, boolean defaultValue);
	
	/**
	 * Returns a string value stored in the config
	 * @param key The key value that points to the string requested
	 * @param defaultValue The value that is returned if they key does not exist in the config. 
	 * 		Whether the default value is written to the config or not depends on the implementation.
	 * @return The string value located at a given key
	 */
	public String getString(String key, String defaultValue);
}
