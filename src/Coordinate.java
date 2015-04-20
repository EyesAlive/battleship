

public class Coordinate
{
	private CoordState state; //state is deprecated for Ship, possibly alter Coordinate to take an interface?
	private int x;
	private int y;

	
	//Constructors
	public Coordinate (int x, int y)
	{
		this(x, y, CoordState.EMPTY);
		
	}
	
	public Coordinate (int i, int j, CoordState c)
	{
		x = i;
		y = j;
		state = c;
	}
	
	//Coordinates are equal if their x and y fields are the same
	public boolean equals(Coordinate c)
	{
		return (x == c.x) && (y == c.y);
	}
	
	public boolean equals(int cx, int cy)
	{
		return (x == cx) && (y == cy);
	}
	
	//Getters
	public int x() { return x; }
	public int y() { return y; }
	public CoordState getState() { return state; }
	
	//Setters
	public void setState(CoordState c) { state = c; }
	public void x(int newX){x = newX;}
	public void y(int newY){y = newY;}
}