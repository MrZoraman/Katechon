package apcs.katechon.engine;

import java.util.HashSet;
import java.util.Set;

public class EngineManager
{
	private final Set<EngineModuleBase<?>> engines;
	
	public EngineManager()
	{
		engines = new HashSet<EngineModuleBase<?>>();
	}
	
	public void addEngine(EngineModuleBase<?> engine)
	{
		engines.add(engine);
	}
	
	@SuppressWarnings("unchecked")
	public <T> EngineModuleBase<T> getEngine(Class<T> clazz)
	{
		for(EngineModuleBase<?> engine : engines)
		{
			if(clazz == engine.getEngineType())
			{
				return (EngineModuleBase<T>) engine;
			}
		}
		
		return null;
	}
	
	public void doWork()
	{
		for(EngineModuleBase<?> engine : engines)
		{
			engine.doWork();
		}
	}
}
