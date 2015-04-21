public abstract class BoardFactory
{
	protected int size; //square board
	protected int num_ships;
	
	public Board createBoard() {
		return new Board(size, num_ships);
	}
	
	
	
	
	
	
	
}