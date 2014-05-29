package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.imgscalr.Scalr.Rotation;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.ImageUtils;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.resources.SpritesheetLoader;

/**
 * A person who will walk around the shopping mall.
 * @author Sean
 *
 */
@SuppressWarnings("unused")
public class Man extends SimpleBoardCollidable
{
	private final AnimatedSequence<BufferedImage> frames;
	
	private final Color hairColor;
	private final Color shirtColor;
	private final Color handColor;
	private final Color shoeColor;
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	
	private Direction direction;
	
	private Set<Direction> collisions;
	
	public Man(int x, int y, int speed, Color hairColor, Color shirtColor, Color handColor, Color shoeColor)
	{
		super(x, y, WIDTH, HEIGHT, speed, false);
		this.hairColor = hairColor;
		this.shirtColor = shirtColor;
		this.handColor = handColor;
		this.shoeColor = shoeColor;
		
		this.direction = Direction.TOP;
		
		this.collisions = new HashSet<Direction>();
		
		SpritesheetLoader loader = new SpritesheetLoader(ShoppingMaul.class, "/apcs/shoppingMaul/assets/man.png", 8, 1);
		BufferedImage[] imageFrames = loader.loadWide(0, 0, 7);
		
		this.frames = new AnimatedSequence<BufferedImage>(imageFrames, 1);
	}

	@Override
	public int getTopFace() {
		return super.y;
	}

	@Override
	public int getBottomFace() {
		return super.y + HEIGHT;
	}

	@Override
	public int getLeftFace() {
		return super.x;
	}

	@Override
	public int getRightFace() {
		return super.x + WIDTH;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public void onCollision(Set<Direction> types)
	{
		this.collisions = types;
	}
	
	@Override
	public boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doTask()
	{
		Log.debug("Moving Man");
		Random r = new Random();
		Direction dir = Direction.values()[r.nextInt(Direction.values().length - 1)];
		
		if (dir.equals(Direction.TOP))
		{
			if (!collisions.contains(Direction.TOP))
			{
				super.setY(super.y - super.speed);
			}
		}
		
		if (dir.equals(Direction.BOTTOM))
		{
			if (!collisions.contains(Direction.BOTTOM))
			{
				super.setY(super.y + super.speed);
			}
		}
		
		if (dir.equals(Direction.LEFT))
		{
			if (!collisions.contains(Direction.LEFT))
			{
				super.setX(super.x - super.speed);
			}
		}
		
		if (dir.equals(Direction.RIGHT))
		{
			if (!collisions.contains(Direction.RIGHT))
			{
				super.setX(super.x + super.speed);
			}
		}
	}

	@Override
	public void draw(Graphics g)
	{
		BufferedImage copy = ImageUtils.deepCopy(frames.getCurrentFrame());
		
		Rotation rotation = direction.getRotation();
		
		if(rotation != null)
		{
			copy = ImageUtils.rotateImage(copy, rotation);
		}
		
//		Log.debug("Drawing Man");
		
		g.drawImage(copy, super.x, super.y, null);
	}
}
