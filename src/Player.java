import java.util.ArrayList;


//public abstract class Player implements Subject

public  class Player implements Subject
{
	//Fields
	private ArrayList<Observer> boards;
	private   int numShips;
	protected int DefaultShipSize;
	protected MoveStrategy move_strategy; //private?
	protected ShipStrategy ship_strategy;
	protected boolean is_user = false;
	protected boolean is_turn;
	protected boolean is_valid;
	protected GameState gameState;

	//Constructor
	public Player()
	{
		boards = new ArrayList<Observer>();
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
		if (notificationType==0)
		{
			is_valid=boards.get(0).updatePlacement(ship_strategy);
		}
		else if (notificationType==1)
		{
			if (boards.size()>0) // quick patches not really great
			gameState = boards.get(1).updateMoves(move_strategy);
		}
		else if (notificationType==2)
		{
			boards.get(0).updateShowBoard(true);	
		}
		else if (notificationType==3)
		{
			if (boards.size() > 0)
			boards.get(1).updateShowBoard(false);
		}
	}
	
	//method to notify Observers of a move
	private void newMove(){
		notifyObservers(1);
	}
	//method to notify Observers that the board needs to be displayed
	private void displayBoard(int notificationType)
	{
		if (0 == notificationType)
			notifyObservers(2);
		else
			notifyObservers(3);
	}
	
	//method that gets a move from the user
	public void makeMove()
	{
		newMove();
	}
	
	public void placeShip()
	{
		ship_strategy.place(DefaultShipSize);
		notifyObservers(0);
	}

	//method to display the board
	public void showBoard(int notificationType)
	{
		displayBoard(notificationType);
	}
	
	public void numShips(int newNumShips) { numShips = newNumShips; }
	public int numShips() { return numShips; }
	
	public ShipStrategy getShipState() {
		return ship_strategy;
	}
	public MoveStrategy getMoveState() {
		return move_strategy;
	}
}