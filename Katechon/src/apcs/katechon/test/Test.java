package apcs.katechon.test;

import apcs.katechon.KatechonBase;
import apcs.katechon.KatechonEngine;
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
		
		//see this line of clode looks pretty clean to me
		new KatechonEngine(Test.class, config).start();
	}

	@Override
	public void onGameTick() {
		// TODO Auto-generated method stub
		
	}
}