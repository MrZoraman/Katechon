package apcs.katechon.sounds;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Plays a single clip. Can be looped infinitely.
 * @author Matt
 */
public class SimplePlayer implements IPlayer
{
	public SimplePlayer(InputStream stream) throws Exception
	{
		InputStream bufferedIn = new BufferedInputStream(stream);
		
		this.clip = AudioSystem.getClip();
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
		clip.open(inputStream);
		
		this.started = false;
		this.finished = false;
	}
	
	private final Clip clip;
	
	private boolean started;
	private boolean finished;
	
	@Override
	public synchronized void start()
	{
		started = true;
		clip.start();
	}

	@Override
	public synchronized void stop()
	{
		started = false;
		clip.setFramePosition(0);
		clip.stop();
	}

	@Override
	public synchronized void loop()
	{
		started = true;
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	@Override
	public synchronized void pause()
	{
		clip.stop();
	}
	
	@Override
	public synchronized void close()
	{
		this.finished = true;
		clip.close();
	}

	@Override
	public void doTask()
	{
		if(!clip.isRunning() && !started)
		{
			close();
		}
	}

	@Override
	public boolean isFinished()
	{
		if(finished)
		{
			return true;
		}
		
		if(!started)
		{
			return false;
		}
		else
		{
			if(!clip.isRunning())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	public void setFinished(boolean finished)
	{
		this.finished = finished;		
	}
}
