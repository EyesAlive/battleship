import java.util.ArrayList;


//public abstract class Player implements Subject

public  class Player implements Subject
{
	//Fields
	private ArrayList boards;
	protected MoveStrategy move_strategy; //private?
	protected boolean is_turn;
	protected Ship ship;
	private   int DefaultShipSize;
	protected boolean is_valid;

	//Constructor
	public Player()
	{
		boards = new ArrayList();
		DefaultShipSize = 2;
		      
	}
	
	//Method to add a new board to the list of Observers
	public void add(Observer b)
	{
		boards.add(b);
	}

	//Method to remove a board from the list of Observers
	public void remove(Observer b)
	{
		int i = boards.indexOf(b);
		if (i>=0)
			boards.remove(b);
	}

	public void notifyObserves(int notificationType)
	{	
		if(notificationType==0){
		Observer board = (Observer)boards.get(0);
		is_valid=board.updatePlacement(ship);
		}
		
		else{
			Observer board = (Observer)boards.get(0);
			board.updateMoves(move_strategy);
			
		}
			
	}
	
	private void newShipPlacement(){
		
		
	}
	private void newMove(){
		
		
		
	}
	
	public void makeMove(MoveStrategy newMove)
	{
		move_strategy = newMove;
		newMove();
	}

	
	public void placeShip(int x, int y,char orientation )
	{
		ship = new Ship(DefaultShipSize,x,y,orientation);
		newShipPlacement();
		
	}

	
	

	//public getState()
}
