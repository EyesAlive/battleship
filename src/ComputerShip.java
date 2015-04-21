import java.security.SecureRandom;

public class ComputerShip implements ShipStrategy
{
	private int x,y;
	private int shipSize;
	private char shipOrientation;
	private static SecureRandom random = new SecureRandom();
	
	@Override
	public void place(int shipSize)
	{
		this.shipSize = shipSize;
		// gets a random location on the board
		x = random.nextInt(shipSize);
		y = random.nextInt(shipSize);

		//gets a random number to choose orientation
		shipOrientation = random.nextBoolean() ? 'v' : 'h';
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
