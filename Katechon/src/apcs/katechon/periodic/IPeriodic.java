package apcs.katechon.periodic;

/**
 * An interface for objects that are not necessarily drawn to the screen, but are still dependant on the game ticking.
 * @author Matt
 */
public interface IPeriodic
{
	/**
	 * What the Periodic object does when a tick occurs.
	 */
	public void onTick();
}
