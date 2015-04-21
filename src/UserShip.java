

public class UserShip implements ShipStrategy
{
	protected int x;
	protected int y;
	protected int shipSize;
	protected char shipOrientation;
	
	public void place(int newX, int newY,int newShipSize,char newOrientation)
	{
		x = newX;
		y = newY;
		shipSize = newShipSize;
		shipOrientation = newOrientation;
	}
	

	
	public int x(){ return x;}
	public int y(){ return y;}
	public int shipSize(){ return shipSize;}
	public char shipOrientation(){return shipOrientation;}

	
	public void automatedShipPlacer(int newShipSize)
	{
		// TODO Auto-generated method stub
		
	} 

	

	
	
	}
