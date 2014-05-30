package apcs.shoppingMaul.man;

import java.awt.Point;
import java.util.Random;

import apcs.katechon.engine.collisions.Direction;
import apcs.katechon.rendering.sprites.AnimatedSequence;
import apcs.shoppingMaul.AI;

public class ManAI extends AI
{
	private static final Direction[] directions;
	
	static
	{
		directions = Direction.values();
	}
	
	private final Random rand;
	private final AnimatedSequence<Direction> sequence;
	
	public ManAI()
	{
		super(0);
		
		this.rand = new Random();
		
		Direction[] directions = new Direction[64];
		
		for(int ii = 0; ii < directions.length; ii++)
		{
			directions[ii] = getRandomDirection();
		}
		
		sequence = new AnimatedSequence<Direction>(directions, 5);
	}
	
	public Direction getNextDirection()
	{
		return sequence.getCurrentFrame();
	}
	
//	public Point getNextLocation(int speed, int destinationDistance, int x, int y)
//	{
//		Direction dir = sequence.getCurrentFrame();
//		
//		int destinationX = x;
//		int destinationY = y;
//		
//		switch(dir)
//		{
//		case TOP:
//			destinationY -= destinationDistance;
//			break;
//		case BOTTOM:
//			destinationY += destinationDistance;
//			break;
//		case LEFT:
//			destinationX -= destinationDistance;
//			break;
//		case RIGHT:
//			destinationX += destinationDistance;
//			break;
//		case NONE:
//			return new Point(x, y);
//		}
//		
//		return moveTowardsDestination(speed, x, y, destinationX, destinationY);
//	}
	
	private Direction getRandomDirection()
	{
		return directions[rand.nextInt(directions.length - 1)];
	}
}
