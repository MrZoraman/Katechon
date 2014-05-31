package apcs.katechon.sounds;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SimplePlayer implements IPlayer
{
	public SimplePlayer(InputStream stream) throws Exception
	{
		this.clip = AudioSystem.getClip();
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(stream);
		clip.open(inputStream);
	}
	
	private final Clip clip;
	
	@Override
	public synchronized void start()
	{
		clip.start();
	}

	@Override
	public synchronized void stop()
	{
		clip.setFramePosition(0);
		clip.stop();
	}

	@Override
	public synchronized void loop()
	{
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
		clip.close();
	}
}
