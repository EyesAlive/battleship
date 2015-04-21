import java.util.Scanner;

public class UserShip implements ShipStrategy
{
	private int x,y;
	private int shipSize;
	private char shipOrientation;
	
	public void place(int shipSize) {
		this.shipSize = shipSize;
		String userInput;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a valid location in the form ROW COLUMN, e.g. 5 7: ");
		
		//check input for anything that is not a int
		while (input.hasNextLine()) {
			String s[] = input.nextLine().trim().split("\\s+");
			
			if (s.length == 2) {
				try {
					x = Integer.parseInt(s[0]) - 1;
					y = Integer.parseInt(s[1]) - 1;
					break;
				}
				catch (NumberFormatException e ) {
				}
			}
			System.out.println("Invalid input, enter 2 integers.");
		}

		//get orientation of ships
		shipOrientation = 0;
		System.out.println("Enter V for vertical (going down) or H for horizontal (going right)");
		while (input.hasNextLine()) {
			userInput = input.nextLine().trim().toLowerCase();
			if (userInput.length() == 1) {
				shipOrientation = userInput.charAt(0);
				if (shipOrientation == 'h' || shipOrientation == 'v')
					break;
			}
			System.out.println("Invalid Input, enter v or h");
		}
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
