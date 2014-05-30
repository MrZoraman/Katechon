package apcs.katechon.utils;

import java.awt.Point;

public class AI
{
	private final int snapToConstant;
	
	public AI(int snapToConstant)
	{
		this.snapToConstant = snapToConstant;
	}
	
	/**
	 * Causes the leopard to 'step' towards the direction that it's destination is at
	 */
	public Point moveTowardsDestination(int speed, int x, int y, int destinationX, int destinationY)
	{		
		//find the difference in Cartesian coordinates
		int preX = destinationX - x;
		int preY = destinationY - y;
		
		//check if at destination or not
		if(preX != 0 || preY != 0)
		{
			//find the hypotnuse (distance)
			double hypotnuse = Math.sqrt((preX * preX) + (preY * preY));
			
			//the farther away the leopard is to it's destination, the faster it goes
			speed += hypotnuse * hypotnuse / snapToConstant;
			
			if(Math.abs(hypotnuse) < speed)
			{
				//we are going to overshoot so we just go to our destination
				return new Point(destinationX, destinationY);
			}
			else if (preX == 0)
			{
				//moving only in the y direction
				if (preY < 0)
				{
					//negative, need to go up
					y -= speed;
				}
				else
				{
					//positive, need to go down
					y += speed;
				}
			}
			else
			{
				//Cartesian coordinates
				double radius = speed;
				double angle = Math.atan(preY / preX);
				
				if(preX < 0 && preY >= 0)
				{
					//quadrant II
					angle += Math.PI;//180 degrees
				}
				else if (preX < 0 && preY < 0)
				{
					//quadrant III
					angle += Math.PI;//180 degrees
				}
				else if (preX > 0 && preY < 0)
				{
					//quadrant IV
					angle += Math.PI * 2;//360 degrees
				}
				
				//back to Cartesian coordinates
				int cX = (int) (radius * Math.cos(angle));
				int cY = (int) (radius * Math.sin(angle));
				
				//add the difference to the coordinates
				x += cX;
				y += cY;
			}
		}
		
		return new Point(x, y);
	}
}
