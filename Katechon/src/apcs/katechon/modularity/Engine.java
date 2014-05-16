package apcs.katechon.modularity;

/**
 * <p>
 * Any other types of engines that are Katechon compatible should extend this. (e.g. physics engines, damage engines, etc.)
 * </p>
 * 
 * <p>
 * It is recommended that all classes extending Engine should give the user access to as much as possible statically.
 * </p>
 * 
 * @author Sean
 */
public abstract class Engine
{
	public abstract Class<? extends Engine> getInstance();
	
	public abstract void init();
	
	public abstract void start();
	
	public abstract void onGameEnd();
}
