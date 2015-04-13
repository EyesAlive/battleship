import java.util.ArrayList;



public abstract class Player implements Subject
{

	
	private ArrayList boards;
	protected MoveStrategy move_strategy; //private?
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

	
	
	public void makeMove(int x, int y){
		move_strategy.move(x, y);
		
	}
	
	
	//public makeMove()
	//public getState()
}
