package apcs.shoppingMaul.commands;

import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.parser.Command;
import apcs.katechon.logging.Log;
import apcs.shoppingMaul.Pillar;

public class AddPillarCommand implements Command
{

	@Override
	public void onCommand(String[] preArgs, String[] args)
	{
		if (args.length < 4)
		{
			showUsage();
		}
		else
		{
			try
			{
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int width = Integer.parseInt(args[2]);
				int height = Integer.parseInt(args[3]);
				
				Pillar pillar = new Pillar(x, y, width, height);
				
				KatechonEngine.getInstance().addDrawable(pillar, 1);
				KatechonEngine.getInstance().scheduleTask(pillar);
				
				Log.info("Pillar added at: " + x + ", " + y + " with dimensions: " + width + ", " + height);
			}
			catch(NumberFormatException e)
			{
				showUsage();
			}
		}
	}
	
	private void showUsage()
	{
		Log.chainLog()
			.info("USAGE:")
			.info("addpillar [x] [y] [width] [height]")
		.log();
	}
}
