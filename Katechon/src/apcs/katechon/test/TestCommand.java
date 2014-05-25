package apcs.katechon.test;

import java.util.Arrays;

import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;

public class TestCommand implements Command
{
	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		Log.info("Command executed! " + Arrays.asList(args).toString());
	}
}
