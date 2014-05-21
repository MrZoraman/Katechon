package apcs.katechon.engine;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>If you touch this class I will give you a small, really cute puppy. 
 * I will then proceed to let you grow an emotional attachment to said puppy before I kill it while you're cradling it in your arms.</p>
 * 
 * <p> This class manages the engines that the katechon will juggle during runtime.
 * @author Matt
 *
 */
public class EngineManager
{
	private static EngineManager instance = null;
	
	/**
	 * @return The singleton instance of the EngineManager. 
	 * 		It's lazily loaded but that doesn't mean too much to you because it's probably going to get loaded almost immediately anyways.
	 */
	public static synchronized EngineManager getInstance()
	{
		if(instance == null)
		{
			instance = new EngineManager();
		}
		
		return instance;
	}
	
	private final Set<EngineModuleBase<?>> engines;
	
	/**
	 * Ctor
	 */
	private EngineManager()
	{
		engines = new HashSet<EngineModuleBase<?>>();
	}
	
	/**
	 * Adds an engine :D
	 * @param engine :D
	 */
	public void addEngine(EngineModuleBase<?> engine)
	{
		engines.add(engine);
	}
	
	/**
	 * This is the scary shit. Good stuff. Cool beans. 'Nuff said. Potato Salad.
	 * @param clazz This is the type of object that the engine works with. 
	 * 		It will try to return an engine that works with this type, but if it can't find one, 
	 * 		it will then proceed to stockpile lots of silverware in preparation for the judgment day.
	 * @return An engine if it can, or <b>null</b> if it cannot find one you
	 */
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
	
	/**
	 * This will have all of the engines do the stuff that needs to be done 
	 * 		as decreed by those who require that the stuff that needs to be 
	 * 		done should be done by those who need to do what needs to be done.
	 */
	public void doWork()
	{
		for(EngineModuleBase<?> engine : engines)
		{
			engine.doWork();
		}
	}
}
