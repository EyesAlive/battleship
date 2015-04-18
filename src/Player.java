import java.util.ArrayList;

<<<<<<< HEAD
=======
//public abstract class Player implements Subject
>>>>>>> origin/master
public  class Player implements Subject
{
	//Fields
	private ArrayList boards;
	protected MoveStrategy move_strategy; //private?
	protected boolean is_turn;
	protected ShipStrategy ship_strategy;

	//Constructor
	public Player()
	{
		
		boards = new ArrayList();		
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

	public void makeMove(MoveStrategy newMove)
	{
		move_strategy = newMove;
		Observer board = (Observer)boards.get(0);
		board.update(move_strategy);
	}

	//public getState()
}
