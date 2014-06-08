package apcs.katechon.windowingtoolkit;

import java.util.ArrayList;
import java.util.List;

import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.rendering.Layer;

public class KWT extends Layer implements ISchedulerTask
{
	private static KWT instance = null;
	
	public static synchronized KWT getInstance()
	{
		if (instance == null)
		{
			instance = new KWT();
		}
		
		return instance;
	}
	
	private List<Window> windows;
	
	private KWT()
	{
		super();
		this.windows = new ArrayList<Window>();
	}

	//remove closed windows
	@Override
	public void doTask()
	{
		for(int ii = windows.size() - 1; ii >= 0; ii--)
		{
			if(windows.get(ii).isFinished())
			{
				windows.remove(ii);
			}
		}
	}
	
	public void addWindow(Window window)
	{
		this.addDrawable(window);
	}

	@Override
	public boolean isFinished() {
		//Never finished!
		return false;
	}

	@Override
	public void setFinished(boolean finished)
	{
		//Never done
		
	}
}
