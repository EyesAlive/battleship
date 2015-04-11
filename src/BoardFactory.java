//Start by making a change, even if its comments
//Note how BoardFactory, the package, src, and the project now have a > prefixing it.
//You can push/commit from either location, whether you want to push one file or many.

public class BoardFactory
{
	private int size; //square board
	private int num_ships;
	
	//Constructors
	public BoardFactory()
	{
		size = 10;
		num_ships = 10;
	}
	
	//Methods
	public void createBoard()
	{
		//GUI based?
	}
	
	private void specifySize(int s)
	{
		size = s;
	}
	
	private void specifyNumberOfShips(int n_s)
	{
		num_ships = n_s;
	}
}