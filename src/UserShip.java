

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
	
	public void remove(int x, int y, int shipSize)
	{
		// TODO Auto-generated method stub
		
	}
	
	public int x(){ return x;}
	public int y(){ return y;}
	public int shipSize(){ return shipSize;}
	public char shipOrientation(){return shipOrientation;} 

	

	
	
	}
