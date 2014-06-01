package apcs.katechon.sounds;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import apcs.katechon.utils.Utils;

public class RandomPlayer implements IPlayer
{
	private Set<Clip> clips;
	
	private Clip playing;
	
	private boolean started;
	private boolean finished;
	private boolean isLooping;
	
	public RandomPlayer(InputStream defaultClip) throws Exception
	{
		this.clips = new HashSet<Clip>();
		
		InputStream bufferedIn = new BufferedInputStream(defaultClip);
		
		this.playing = AudioSystem.getClip();
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
		playing.open(inputStream);
		
		this.clips.add(playing);
		
		this.started = false;
		this.finished = false;
	}
	
	public void addAllClips(Set<InputStream> clips) throws Exception
	{
		for(InputStream c : clips)
		{
			addClip(c);
		}
	}
	
	public void addClip(InputStream clip) throws Exception
	{
		InputStream bufferedIn = new BufferedInputStream(clip);
		Clip temp = AudioSystem.getClip();
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
		temp.open(inputStream);
				
		this.clips.add(temp);
	}
	
	@Override
	public synchronized void close()
	{
		this.finished = true;
		playing.close();
	}

	@Override
	public synchronized void start()
	{
		this.started = true;
		this.playing.start();
	}

	@Override
	public synchronized void stop()
	{
		started = false;
		playing.setFramePosition(0);
		playing.stop();
	}

	@Override
	public synchronized void loop()
	{
		isLooping = true;
		start();
	}

	@Override
	public synchronized void pause()
	{
		playing.stop();
	}
	
	@Override
	public void doTask()
	{
		if (!playing.isRunning())
		{
			if (isLooping)
			{
				Clip temp = playing;
				while (temp.equals(playing))
				{
					temp = Utils.getRandomItem(clips);
				}
				this.playing = temp;
				System.out.println("Moving to the next clip");
				playing.start();
			}
			else if(!started)
			{
				System.out.println("Closing for some reason...");
				close();
			}
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
			if(!playing.isRunning())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

}
