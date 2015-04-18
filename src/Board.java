import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;

public class Board implements Observer
{
	//Fields
	private Coordinate coordinates[][];
	private int size;
	private MoveStrategy move;
	
	
	private ArrayList<Ship> ship_list;
	private ArrayList<Ship> sunk_ship_list;
	private int boardNum; 
	private Subject player;
	
	//Constructor
	public Board(int sz, int num_ships)
	{
		size = sz;
		ship_list = new ArrayList<>(num_ships);
		sunk_ship_list = new ArrayList<>(num_ships);
	}
	
	//Method to register the board to opponent player
		public void register(Subject player){
			this.player = player;
			player.add(this);
		}
	
	
	//Methods
	public void updateMoves(MoveStrategy newMove)
	{
		move = newMove;

		if (isValidLocation(move.x(),move.y()))
		{
			switch(coordinates[move.x()][move.y()].getState()) //wayyyyy too much nesting!
			{
			case EMPTY: coordinates[move.x()][move.y()].setState(CoordState.MISS);
				break;
			case SHIP: int hit = implementHit();
				if (-1 != hit)
				{
					if (ship_list.get(hit).checkSunk())
					{
						for (int i = 0; i < ship_list.get(hit).hit_list.size(); i++)
							coordinates[ship_list.get(hit).hit_list.get(i).x()][ship_list.get(hit).hit_list.get(i).y()].setState(CoordState.SUNK);
					//nesting overload
					}
					
				}
				else
					coordinates[move.x()][move.y()].setState(CoordState.HIT);
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
	
	//Method to place ships on board and into the ship_list
	public boolean updatePlacement(Ship ship)
	{
		int i =0;
		Coordinate pos[];
		pos = (Coordinate[])ship.position();
		
		for(;i<pos.length;i++){
			if(coordinates[pos[i].x()][pos[i].y()].getState()!=CoordState.SHIP)
				coordinates[pos[i].x()][pos[i].y()].setState(CoordState.SHIP);
			else
				return false;
		}
					
		ship_list.add(ship);
		return true;
	}
	
	
	
	
	

	//Methods
	public boolean isValidLocation(int x, int y)
	{
		if ((x > size) || (y > size) || (x < 1) || (y < 1))
			return false;
		return true;
	}
	

	
	//Potentially abusive if public
	private int implementHit()
	{
		int hit_ship = -1;
		for (int i = 0; i < size; i++)
		{
			if (ship_list.get(i).hitEmHard(move.x(), move.y()))
			{
				hit_ship = i;
				break;
			}
		}
		return hit_ship;
	}
	public void displayBoard(){
		
		
		
	}

	
	
	}
