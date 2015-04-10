

public class Coordinate
{
	private CoordState state;
	private int x;
	private int y;
	
	// constructors
	public Coordinate (int x, int y) {
		this(x,y, CoordState.EMPTY);
	}
	public Coordinate (int i, int j, CoordState c) {
		x = i;
		y = j;
		state = c;
	}
	
	// Coordinates are equal if their x and y fields are the same
	public boolean equals(Coordinate c) {
		return x == c.x && y == c.y;
	}
	public boolean equals(int cx, int cy) {
		return x == cx && y == cy;
	}
	
	// getters
	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
	public CoordState getState() {
		return state;
	}
	
	// setter
	public void setState(CoordState c) {
		state = c;
	}
}