package apcs.katechon.test;

import apcs.katechon.KatechonBase;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;

/**
 * This is a test class for general Katechon testsing
 * @author Matt
 *
 */
public class Test extends KatechonBase
{
	/**
	 * Entry point
	 * @param args command line arguments (unused [probably])
	 */
	public static void main(String[] args)
	{
		IConfig config = new MappedConfig();
		config.set(5);
		config.set("abba jeezles");
		config.set(true);
		config.set(4.2);
		config.set(4.2f);
		Integer i = new Integer(5);
		config.set(i);
	}

	@Override
	public void onGameTick() {
		// TODO Auto-generated method stub
		
	}
}
