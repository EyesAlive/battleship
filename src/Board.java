import java.util.ArrayList;

public class Board implements Observer
{
	private Coordinate coordinates[][];
	private int size;
	private ArrayList<Ship> ship_list = new ArrayList<>();
	private ArrayList<Ship> sunk_ship_list = new ArrayList<>();
	
	//Methods - abstract
	public void update() {}
	
	//Methods
	public boolean isValidLocation(int x, int y)
	{
		if ((x > size) || (y > size))
			return false;
		return true;
	}
}