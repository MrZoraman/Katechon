package apcs.katechon.sounds;

import java.io.Closeable;

import apcs.katechon.engine.scheduler.ISchedulerTask;

/**
 * It's a music/sound player!
 * @author Matt
 *
 */
public interface IPlayer extends Closeable, ISchedulerTask
{
	/**
	 * Start the song playing
	 */
	public void start();
	
	/**
	 * Stop the song from playing and reset the track to the beginning
	 */
	public void stop();
	
	/**
	 * Make the song loop forever! (still stoppable with {@link #stop()} though!)
	 */
	public void loop();
	
	/**
	 * Pauses the song. Starting it will have it continue where it left off
	 */
	public void pause();
}
