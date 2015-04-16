

public class User extends Player
{
	//private board_factory
	
	//no-arg constructor
	public User(){
		
		move_strategy = new UserMove();
	}
	
	public void makeMove(MoveStrategy newMove)
	{
		super.makeMove(newMove);
	}
	
	//public endGame()
	//public placeShips()
	
}