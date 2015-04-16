import java.util.ArrayList;

public class Board implements Observer
{
	//Fields
	private Coordinate coordinates[][];
	private int size;
	private MoveStrategy move;
	private ArrayList<Ship> ship_list;
	private ArrayList<Ship> sunk_ship_list;
	
	//Constructor
	public Board(int sz, int num_ships)
	{
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
				int hit = implementHit();
				if (hit != -1)
				{
					if (ship_list.get(hit).checkSunk())
					{
						for (int i = 0; i < ship_list.get(hit).needsAccess(); i++)
							coordinates[ship_list.get(hit).needsAccess(i)][ship_list.get(hit).needsAccess(i)].setState(CoordState.SUNK);
					}
				}
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
}