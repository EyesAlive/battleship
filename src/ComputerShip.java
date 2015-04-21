
public class ComputerShip implements ShipStrategy
{
	private int boardSize;
	public ComputerShip(int newBoardSize){
		boardSize = newBoardSize;
		System.out.println(boardSize);
	}
	
	
	@Override
	public void place(int x, int y, int shipSize, char newOrientation)
	{
		// TODO Auto-generated method stub
		
	}


	
	public void remove(int x, int y, int shipSize)
	{
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public int x()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int y()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int shipSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public char shipOrientation()
	{
		// TODO Auto-generated method stub
		return 0;
	}



}
