
public class ComputerShip implements ShipStrategy
{
	
	public void place(Coordinate coord)
	{
		// TODO Auto-generated method stub
		//placing coordinate set to Ship
		coord.setState(CoordState.SHIP);
	}

	
	public void remove(Coordinate coord)
	{
		// TODO Auto-generated method stub
		//set state to  empty
		coord.setState(CoordState.EMPTY);
		
	}

	
	public void clear()
	{
		// TODO Auto-generated method stub
	}
	
	public void finalize(){
		
	}
}
