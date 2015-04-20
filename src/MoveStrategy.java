

public interface MoveStrategy
{
	public void move(int x, int y);
	
	public abstract void engaged(boolean x);
	
	public int x();
	public int y();
}