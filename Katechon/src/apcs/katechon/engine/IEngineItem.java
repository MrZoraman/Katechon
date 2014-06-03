package apcs.katechon.engine;

/**
 * All objects that are plugged into an engine must implement this interface.
 * 		This provides a single isFinished() method which will allow the engine to stop referencing the object so that the garbage collector can do it's job.
 * 		In a perfect world, once isFinished() returns true and the object goes out of scope, it is garbage collected.
 * @author Matt
 *
 */
public interface IEngineItem
{
	/**
	 * Checks if the object is finished or not
	 * @return True if the object is ready to be destroyed, false if otherwise.
	 */
	public boolean isFinished();
	
	public void setFinished(boolean finished);
}
