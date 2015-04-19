import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;

public class Board implements Observer
{
	//Fields
	private Coordinate coordinates[][];
	private int size;
	private MoveStrategy move;
	//private Boolean setupMode;
	
	private ArrayList<Ship> ship_list;
	private ArrayList<Ship> sunk_ship_list;
	private int boardNum; 
	protected Ship ship;
	private Subject player;
	
	
	//Constructor
	public Board(int sz, int num_ships)
	{
		int i = 0;
		int j;
		size            = sz;
		ship_list       = new ArrayList<>(num_ships);
		sunk_ship_list  = new ArrayList<>(num_ships);
		coordinates     = new Coordinate[size][size];
		
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
	public boolean updatePlacement(ShipStrategy shipInfo)
	{
		
		int i =0;
		Coordinate pos[];
		int x;
		int y;
		
		
		ship = new Ship(shipInfo.shipSize(),shipInfo.x(),shipInfo.y(),shipInfo.shipOrientation());
		
		pos = ship.position();
	
		for(;i<pos.length;i++){
			x = pos[i].x();
			y = pos[i].y();
			//System.out.println("x: "+ x +" y: "+ y);
			if(isValidLocation(x,y)==true){
					if(coordinates[x][y].getState()==CoordState.EMPTY){
				//System.out.println("x: "+ x +" y: "+ y);
				coordinates[x][y].setState(CoordState.SHIP);
			
					}
					else {
						System.out.println("Ship is already in this location.Choose another location to place your ship.");
						return false;
					}
					
			}
			else{
				System.out.println("Location picked for ship is out of bounds");
				return false;
			}
		}
					
		ship_list.add(ship);
		return true;
	}
	//method to display the board after something has been changed on it,after a turner, or on request by the user
	public void updateShowBoard(){
		
		displayBoard(true);
		
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
	private void displayBoard(Boolean is_player){
		
		int i = 0;
		int j = 0;
		int row_limit = size+1;
		int col_limit = size+1;
		int col_index =1;
		int row_index =1;
		System.out.println(size);
		
		for(;i<=row_limit;i++){
			
			for(j=0;j<=col_limit;j++){
				
				if((i==0 && j==0) || (i == row_limit && j==0))
						System.out.print(".");
					
				else if((i==0 && j==(col_limit))||(i==row_limit && j == col_limit) )
						System.out.print(" "+".");
					
				
				
				if((i==0&&(j<col_limit-1)) || (i==(row_limit)&&(j<col_limit-1))){
					System.out.print(" "+col_index);
					col_index++;
				}
				
				if((j==0 &&  (i>0) && (i<(row_limit))) || (j==col_limit &&  (i>0) && (i<(row_limit))) ){
					if(j==0)
						System.out.print(row_index);
					
					else
						System.out.print(" "+row_index);
					
					if(j==col_limit)
					row_index++;
				}
				if((i>0 && i<(row_limit)) && ( j>0 && j<(col_limit))){
					if(coordinates[i-1][j-1].getState()==CoordState.EMPTY)
						System.out.print(" "+"x");
					
					else if(coordinates[i-1][j-1].getState()==CoordState.SHIP && is_player==true){
						System.out.print(" "+"S");
					}
					
					else if(coordinates[i-1][j-1].getState()==CoordState.HIT)
						System.out.print(" "+"X");
					
					else if(coordinates[i-1][j-1].getState()==CoordState.SUNK)
						System.out.print(" "+"#");
					
					else if(coordinates[i-1][j-1].getState()==CoordState.MISS)
						System.out.print(" "+"O"); 
				}
				
					
				
			}
			
			col_index = 1;
			System.out.println();	
			
		}
		
			System.out.println("x = hit | o = miss | # = sunk");
		
	}

	
	
	}
