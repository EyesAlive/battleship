

public class UserShip implements ShipStrategy
{
	Coordinate location;
	@Override
	public void place(Coordinate coord)
	{
		location = coord;
		location.setState(CoordState.SHIP);
		// TODO Auto-generated method stub
		
	}

	public void remove(Coordinate coord)
	{
		
		
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		
	}
	public void finalize(){
		
		
		
	}
}