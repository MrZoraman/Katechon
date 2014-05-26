package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import apcs.katechon.test.SimpleCollidable;

public class LeopardPack extends SimpleCollidable
{
	private Set<SnowLeopard> leopards = new HashSet<SnowLeopard>();
	private SnowLeopard leader;

	public LeopardPack(int x, int y, int speed, boolean control, SnowLeopard leader)
	{
		super(x, y, 1, 1, speed, control);
		this.leader = leader;
	}
	
	public int getPackSize()
	{
		return leopards.size();
	}
	
	public SnowLeopard getLeader()
	{
		return this.leader;
	}
	
	@Override
	public void draw(Graphics g)
	{
		for(SnowLeopard sl : leopards)
		{
			sl.draw(g);
		}
	}
	
	public void addLeopard(SnowLeopard leopard)
	{
		leopard.relocate(leader);
		this.leopards.add(leopard);
	}
	
	public void removeLeopard(SnowLeopard leopard)
	{
		this.leopards.remove(leopard);
	}
	
	@Override
	public void doTask()
	{
		for (SnowLeopard sl : leopards)
		{
			sl.relocate(leader);
			
			if (sl.getRightFace() > this.x + this.width)
			{
				this.width = (sl.getRightFace() - this.x);
			}
			
			if (sl.getBottomFace() > this.y + this.height)
			{
				this.height = (sl.getBottomFace() - this.y);
			}
			
			if (sl.getLeftFace() < this.x)
			{
				this.x = sl.getLeftFace();
			}
			
			if (sl.getTopFace() < this.y)
			{
				this.y = sl.getTopFace();
			}
		}
	}
}
