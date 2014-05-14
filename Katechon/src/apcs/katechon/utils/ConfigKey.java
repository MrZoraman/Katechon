package apcs.katechon.utils;

/**
 * Keeps all of the keys for the config in a single place so there are no conflicts due to spelling mistakes.
 * @author Sean
 *
 */
public enum ConfigKey
{
	TITLE	("title"),
	FPS		("fps");
	
	
	private ConfigKey(String str)
	{
		this.name = str;
	}

	private final String name;
	
	public String toString()
	{
		return this.name;
	}
}