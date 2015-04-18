

public class UserShip implements ShipStrategy
{
	Coordinate location;
	public void place(Coordinate coord)
	{
		location = coord;
		location.setState(CoordState.SHIP);
		// TODO Auto-generated method stub
		
	}

	public void remove(Coordinate coord)
	{
		
		
	}

	
	
	}
