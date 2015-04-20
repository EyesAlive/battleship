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
		
		int i =0;
		Coordinate pos[];
		int x;
		int y;
		
		//creates a new ship with the following information
		ship = new Ship(shipInfo.shipSize(),shipInfo.x(),shipInfo.y(),shipInfo.shipOrientation());
		
		pos = ship.position();
	
		for(;i<pos.length;i++){
			x = pos[i].x();
			y = pos[i].y();
			
			if(isValidLocation(x,y)==true){
					if(coordinates[x][y].getState()==CoordState.EMPTY){
				
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
	private void displayBoard(Boolean is_player){
		
		int i = 0;
		int j = 0;
		int row_limit = size+1;
		int col_limit = size+1;
		int col_index =1;
		int row_index =1;
		for(i=0;i<=row_limit;i++){
			
			for(j=0;j<=col_limit;j++){
				
				if((i==0 && j==0) || (i == row_limit && j==0))
						System.out.print(" "+".");//needed extra space to lineup
					
				else if((i==0 && j==(col_limit))||(i==row_limit && j == col_limit) )
						System.out.print(" "+"."); //needed extra space to lineup
					
				
				
				if((i==0 && (j<col_limit-1)) || (i==(row_limit)&&(j<col_limit-1))){
				
					if(j>9){
					System.out.print(" "+(j+1)); // j instead of col_index
					col_index++;
					}
					else{
					System.out.print(" "+(j+1)); // j instead of col_index
					col_index++;
					}
				}
				
				if((j==0 &&  (i>0) && (i<(row_limit))) || (j==col_limit &&  (i>0) && (i<(row_limit))) ){
					if(j==0 && i>=1 && i<=9 )
						System.out.print(" " + row_index);
					else if(j==0){
						System.out.print( row_index);
					}
					
					else if(i>=1 && i<=9)
					{
						System.out.print(" "+row_index);
					}
					else 
					{
						System.out.print(" "+row_index);
					}
					if(j==col_limit)
					row_index++;
				}
				if((i>0 && i<(row_limit)) && ( j>0 && j<(col_limit))){
				
					 if(coordinates[i-1][j-1].getState()==CoordState.SHIP && is_player==true){
						if(j>9){
							System.out.print("  "+"S"); 	
						}
						else{
						System.out.print(" "+"S"); 
						}
					}//closing 1st elseif
					
					 else if(coordinates[i-1][j-1].getState()==CoordState.HIT){
							
						if(j>9){
							System.out.print("  "+"X"); 	
						}
						else{
						System.out.print(" "+"X"); 
						}
					}//closing 2nd elseif
					else if(coordinates[i-1][j-1].getState()==CoordState.SUNK){
						
						if(j>9){
							System.out.print("  "+"#");  	
						}
						else{
						System.out.print(" "+"#");
						}
					}//closing 3rd elseif
					
					else if(coordinates[i-1][j-1].getState()==CoordState.MISS)
					{	
						if(j>9){
							System.out.print("  "+"O"); 
						}
						else{
						System.out.print(" "+"O"); 
						}
					}//closing 4th elseif
					
					else{
					 if(j>9){
							System.out.print("  "+" "); // just for the simplicity used - instead of x 	
						}
						else{
						System.out.print(" "+" "); // just for the simplicity used - instead of x 
						}
					}
					
					
					
					
				}
				
					
				
			}
			
			col_index = 1;
			System.out.println();	
			
		}
		
			System.out.println("x = hit | o = miss | # = sunk");
		
	}

	
	

	}
