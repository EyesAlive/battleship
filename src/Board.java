import java.util.ArrayList;

public class Board implements Observer
{
	//Fields
	private Coordinate coordinates[][];
	private int size;
	private MoveStrategy move;
	private ArrayList<Ship> ship_list;
	private ArrayList<Ship> sunk_ship_list;
	//private Ship ship;
	
<<<<<<< HEAD
	//Methods - abstract
	public void update(MoveStrategy newMove) {
		move = newMove;
	
		if(isValidLocation(move.x(),move.y())){
			
			if(coordinates[move.x()][move.y()].getState()==CoordState.SHIP){
				coordinates[move.x()][move.y()].setState(CoordState.HIT);
				for(Ship ship : ship_list){
					
					
					
					
				}
				
					
					
				
				
				
			}
			
			else
				coordinates[move.x()][move.y()].setState(CoordState.MISS);
			
		
		}
			
		
		else{
			
			
			
		}
		
		
	}
	
	// constructor
	public Board(int sz, int num_ships) {
=======
	//Constructor
	public Board(int sz, int num_ships)
	{
>>>>>>> origin/master
		size = sz;
		ship_list = new ArrayList<>(num_ships);
		sunk_ship_list = new ArrayList<>(num_ships);
	}

	//Methods
	public boolean isValidLocation(int x, int y)
	{
		if ((x > size) || (y > size) || (x < 1) || (y < 1))
			return false;
		return true;
	}
	
	//Methods
	public void update(MoveStrategy newMove)
	{
		move = newMove;

		if(isValidLocation(move.x(),move.y()))
		{
			switch(coordinates[move.x()][move.y()].getState()) //wayyyyy too much nesting!
			{
			case EMPTY: coordinates[move.x()][move.y()].setState(CoordState.MISS);
				break;
			case SHIP: coordinates[move.x()][move.y()].setState(CoordState.HIT);
				//Find ship in list and set hit / implement sunk?
				break;
			default:
				break;
			}	
		}
		else
		{
			//Communicate failure? Throw exception? Lose turn?
		}
	}
}