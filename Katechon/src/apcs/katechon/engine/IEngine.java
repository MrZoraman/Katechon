package apcs.katechon.engine;

public interface IEngine<I>
{
	public void addItem(final I item);
	
	public void removeItem(final I item);
	
	public void process();
}
