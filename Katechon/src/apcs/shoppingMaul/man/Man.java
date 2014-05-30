package apcs.shoppingMaul.man;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.imgscalr.Scalr.Rotation;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.ColorChanger;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.rendering.ImageUtils;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.katechon.resources.SpritesheetLoader;
import apcs.shoppingMaul.ShoppingMaul;
import apcs.shoppingMaul.SimpleBoardCollidable;

/**
 * A person who will walk around the shopping mall.
 * @author Sean
 *
 */
@SuppressWarnings("unused")
public class Man extends SimpleBoardCollidable
{
	private static final Color ORIG_SHOE_COLOR = Color.RED;
	private static final Color ORIG_HAIR_COLOR = Color.BLACK;
	private static final Color ORIG_SKIN_COLOR = Color.GREEN;
	private static final Color ORIG_SHIRT_COLOR = Color.BLUE;
	
	private final AnimatedSequence<BufferedImage> frames;
	
	private Color hairColor;
	private final Color shirtColor;
	private final Color handColor;
	private final Color shoeColor;
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	
	private final ManAI ai;
	
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
		
		this.ai = new ManAI();
		
		SpritesheetLoader loader = new SpritesheetLoader(ShoppingMaul.class, "/apcs/shoppingMaul/assets/man.png", 8, 1);
		
		BufferedImage[] imageFrames = loader.loadWide(0, 0, 7);
		
//		for(BufferedImage image : imageFrames)
		for(int ii = 0; ii < imageFrames.length; ii++)
		{
			imageFrames[ii] = ImageUtils.changeColor(imageFrames[ii], ORIG_SHOE_COLOR, shoeColor);
			imageFrames[ii] = ImageUtils.changeColor(imageFrames[ii], ORIG_HAIR_COLOR, this.hairColor);
			imageFrames[ii] = ImageUtils.changeColor(imageFrames[ii], ORIG_SKIN_COLOR, handColor);
			imageFrames[ii] = ImageUtils.changeColor(imageFrames[ii], ORIG_SHIRT_COLOR, shirtColor);
		}
		
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
//		Log.debug("Moving Man");
		this.direction = ai.getNextDirection();
		
		if (direction.equals(Direction.TOP))
		{
			if (!collisions.contains(Direction.TOP))
			{
				super.setRealY(super.getRealY() - super.speed);
			}
		}
		
		if (direction.equals(Direction.BOTTOM))
		{
			if (!collisions.contains(Direction.BOTTOM))
			{
				super.setRealY(super.getRealY() + super.speed);
			}
		}
		
		if (direction.equals(Direction.LEFT))
		{
			if (!collisions.contains(Direction.LEFT))
			{
				super.setRealX(super.getRealX() - super.speed);
			}
		}
		
		if (direction.equals(Direction.RIGHT))
		{
			if (!collisions.contains(Direction.RIGHT))
			{
				super.setRealX(super.getRealX() + super.speed);
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
		
		g.drawImage(copy, x, y, null);
	}
}
