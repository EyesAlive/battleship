

public interface ShipStrategy
{
	public abstract void place(Coordinate coord);
	public abstract void remove(Coordinate coord);
	public abstract void clear();
	public abstract void finalize();
}