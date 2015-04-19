

public interface ShipStrategy
{	
	public abstract void place(int x,  int y,  int shipSize, char newOrientation);
	public abstract void remove(int x, int y, int shipSize);
	public abstract int x();
	public abstract int y();
	public abstract int shipSize();
	public abstract char shipOrientation(); 
	//public abstract void clear();
	//public abstract void finalize();
}