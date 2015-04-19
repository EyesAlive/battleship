import java.util.ArrayList;


//public abstract class Player implements Subject

public  class Player implements Subject
{
	//Fields
	private ArrayList boards;
	protected MoveStrategy move_strategy; //private?
	private ShipStrategy ship_strategy;
	protected boolean is_turn;
	private   int DefaultShipSize;
	protected boolean is_valid;

	//Constructor
	public Player()
	{
		boards = new ArrayList();
		DefaultShipSize = 2;
		ship_strategy = new UserShip();
	
		      
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
	
	//method to notify the Observers based upon a notification type
	public void notifyObservers(int notificationType)
	{	
		if(notificationType==0){
			Observer board = (Observer)boards.get(0);
			is_valid=board.updatePlacement(ship_strategy);
		}
		
		else if(notificationType==1){
			Observer board = (Observer)boards.get(1);
			board.updateMoves(move_strategy);
		}
		else{
			Observer board = (Observer)boards.get(0);
			board.updateShowBoard();
			
		}
			
	}
	
	//method to notify Observers of a ship placement
	private void newShipPlacement(){
		notifyObservers(0);
		
	}
	//method to notify Observers of a move
	private void newMove(){
		notifyObservers(1);
		
	}
	//method to notify Observers that the board needs to be displayed
	private void displayBoard(){
		notifyObservers(2);
		
		
	}
	
	
	//method that gets a move from the user
	public void makeMove(MoveStrategy newMove)
	{
		move_strategy = newMove;
		newMove();
	}

	//method to place ships on the board
	public void placeShip(int newX, int newY,char orientation)
	{
		
		ship_strategy.place(newX, newY, DefaultShipSize,orientation); 
		newShipPlacement();
		
	}
	//method to display the board
	public void showBoard(){
		displayBoard();
	}

	
	

	//public getState()
}
