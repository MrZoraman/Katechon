package apcs.katechon.test;

import apcs.katechon.logging.Log;
import apcs.katechon.periodic.IPeriodic;

public class TestPeriodic implements IPeriodic
{
	public TestPeriodic()
	{
		
	}

	@Override
	public void onTick()
	{
		Log.debug("Tick!");
	}
}
