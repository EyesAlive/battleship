import java.util.ArrayList;



public abstract class Player implements Subject
{

	
	private ArrayList boards;
	protected Coordinate move; 
	//protected is_turn
	//protected move_strategy
	//protected ship_strategy

	
	public Player(){
		boards = new ArrayList();
	}
	
	//Method to add a new board to the list of Observers
	public void add(Observer b)
	{
		boards.add(b);
		// TODO Auto-generated method stub
		
	}

	//Method to remove a board from the list of Observers
	public void remove(Observer b)
	{
		int i = boards.indexOf(b);
		if(i>=0)
			boards.remove(b);
		// TODO Auto-generated method stub
		
	}

	//Method to update the boards with a move 
	public void makeMove()
	{
		for(int i = 0; i< boards.size();i++){
			Observer board = (Observer)boards.get(i);
			board.update(move);
		}	
		
	}
	
<<<<<<< HEAD
=======
	protected MoveStrategy move_strategy; //private?
	
	public void makeMove(){
		move_strategy.move();
	}
	
	//public makeMove()
>>>>>>> origin/master
	//public getState()
}