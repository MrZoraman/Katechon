package apcs.shoppingMaul;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import apcs.katechon.test.SimpleCollidable;

public class LeopardPack extends SimpleCollidable
{
	private Set<SnowLeopard> leopards = new HashSet<SnowLeopard>();
	private SnowLeopard leader;

	public LeopardPack(int x, int y, boolean control, SnowLeopard leader)
	{
//		super(x, y, 1, 1, leader.getSpeed(), control);
		this.leader = leader;
		leopards.add(leader);
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
		leader.draw(g);
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
			sl.doTask();
			
			if (sl.getRightFace() > super.x + this.width)
			{
				super.width = (sl.getRightFace() - super.x);
			}
			
			if (sl.getBottomFace() > super.y + this.height)
			{
				super.height = (sl.getBottomFace() - super.y);
			}
			
			if (sl.getLeftFace() < super.x)
			{
				super.x = sl.getLeftFace();
			}
			
			if (sl.getTopFace() < super.y)
			{
				super.y = sl.getTopFace();
			}
		}
		
		super.doTask();
	}
}
