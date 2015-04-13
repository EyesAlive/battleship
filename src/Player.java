

public abstract class Player implements Subject
{
	//protected is_turn
	//protected move_strategy
	//protected ship_strategy
	
	protected MoveStrategy move_strategy; //private?
	
	public void makeMove(){
		move_strategy.move();
	}
	
	//public makeMove()
	//public getState()
}