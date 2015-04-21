

public interface ShipStrategy
{	
	public abstract void place(int x,  int y,  int shipSize, char newOrientation);
	public abstract void automatedShipPlacer(int newShipSize);
	public abstract int x();
	public abstract int y();
	public abstract int shipSize();
	public abstract char shipOrientation(); 
	//public abstract void clear();
	
}