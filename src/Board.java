import java.util.ArrayList;

public class Board implements Observer
{
	private Coordinate coordinates[][];
	private int size;
	private MoveStrategy move;
	private ArrayList<Ship> ship_list;
	private ArrayList<Ship> sunk_ship_list;
	
	//Methods - abstract
	public void update(MoveStrategy newMove) {
		move = newMove;
	
		if(isValidLocation(move.x(),move.y())){
			
			
		}
		
		else{
			
			
			
		}
		
		
	}
	
	// constructor
	public Board(int sz, int num_ships) {
		size = sz;
		ship_list = new ArrayList<>(num_ships);
		sunk_ship_list = new ArrayList<>();
	}

	//Methods
	public boolean isValidLocation(int x, int y)
	{
		if ((x > size) || (y > size))
			return false;
		return true;
	}
}