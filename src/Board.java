import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;

public class Board implements Observer
{
	//Fields
	private int size;
	private int sunkenShips;

	private Boolean gameOver;
	private Coordinate coordinates[][];
	private MoveStrategy move;
	private ArrayList<Ship> ship_list;
	//private ArrayList<Ship> sunk_ship_list;

	protected Ship ship;
	private Subject player;

	private GameState gameState;


	//Constructor
	public Board(int sz, int num_ships)
	{
		int i = 0;
		int j;
		size            = sz;
		ship_list       = new ArrayList<>(num_ships);
		//sunk_ship_list  = new ArrayList<>(num_ships);
		sunkenShips     = 0;
		coordinates     = new Coordinate[size][size];
		gameState = GameState.GameOn;


		for(;i<size;i++)
			for(j=0;j<size;j++)
				coordinates[i][j] = new Coordinate(i,j);


	}

	//Method to register the board to opponent player
	public void register(Subject player){
		this.player = player;
		player.add(this);
	}


	//Methods
	public GameState updateMoves(MoveStrategy newMove)
	{
		move = newMove;
		gameState = GameState.GameOn;
		if (isValidLocation(move.x(),move.y()))
		{
			switch(coordinates[move.x()][move.y()].getState()) //wayyyyy too much nesting!
			{
			case EMPTY: coordinates[move.x()][move.y()].setState(CoordState.MISS);
			break;
			case SHIP: int hit = implementHit();

			System.out.println("hit:"+hit);
			if (-1 != hit)
			{

					if (ship_list.get(hit).checkSunk())
					{
						for (int i = 0; i < ship_list.get(hit).hit_list.size(); i++)
							coordinates[ship_list.get(hit).hit_list.get(i).x()][ship_list.get(hit).hit_list.get(i).y()].setState(CoordState.SUNK);
						
						sunkenShips++;
						gameState = GameState.Sunk;
						
						if(sunkenShips==ship_list.size())
							gameState = GameState.GameOver;
					//nesting overload
					}
					
					else{
						coordinates[move.x()][move.y()].setState(CoordState.HIT);
						gameState = GameState.Hit;
					}
				
			}
			break;
			default:
				break;
			}	
		}
		else
		{

			gameState = GameState.InvalidMove;
			//Communicate failure? Throw exception? Lose turn?

		}

		return gameState;

	}

	//Method to place ships on board and into the ship_list
	public boolean updatePlacement(ShipStrategy shipInfo)
	{
		shipInfo = ((Player)player).getShipState(); // sorry i'm playing with push AND pull method... i kinda think the pull is more elegant
		int x = shipInfo.x();
		int y = shipInfo.y();
		char orientation = shipInfo.shipOrientation();
		int length = shipInfo.shipSize();
		
		for (int i=0; i < length; ++i) {
			// check if coordinate is out of bounds or assigned to an existing ship
			try {
				if (coordinates[x][y].getState() != CoordState.EMPTY)
					return false;
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("Ship is not in bounds");
				return false;
			}
			
			// go to next coordinate
			if (orientation == 'h')
				++y;
			else
				++x;
		}
		
		// ship placement location is valid!! add it to the board properly
		x = shipInfo.x();
		y = shipInfo.y();
		ship_list.add(new Ship(length, x, y, orientation));
		for (int i=0; i < length; ++i) {
			coordinates[x][y].setState(CoordState.SHIP);
			
			if (orientation == 'h')
				++y;
			else
				++x;
		}
		return true;
	}

	//method to display the board after something has been changed on it,after a turner, or on request by the user
	public void updateShowBoard(boolean is_player){
		displayBoard(is_player);
	}

	//Methods
	public boolean isValidLocation(int x, int y)
	{
		if ((x > (size-1)) || (y > (size-1)) || (x < 0) || (y < 0))
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

	//method to display the board
	private void displayBoard(Boolean is_player)
	{
		for (int row = 0; row < size+2; ++row) {
			for (int col = 0; col < size+2; ++col) {
				
				// print top and bottom rows
				if (row == 0 || row == size+1) {
					if (col == 0 || col == size+1)
						System.out.print(".  ");
					else
						System.out.printf("%-2d ", col);
				}
				
				// print middle rows
				else {
					if (col == 0 || col == size+1)
						System.out.printf("%-2d ", row);
					else {
						CoordState state = coordinates[row-1][col-1].getState();
						char c = 0;
						switch (state) {
						case EMPTY:
							c = ' ';
							break;
						case SHIP:
							c = (player.getClass() == User.class) ? 's' : '.'; // possible alternative to using is_player?
							break;
						case MISS:
							c = (player.getClass() == User.class) ? '.' : 'o';
							break;
						case HIT:
							c = 'x';
						case SUNK:
							c = '#';
							break;
						}
						System.out.print(c+"  ");
					}
				}
			}
			System.out.println(); // done with this row
		}

		System.out.println("x = hit | o = miss | # = sunk");
	}
}