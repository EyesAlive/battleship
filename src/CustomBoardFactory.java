import java.util.*;

public class CustomBoardFactory extends BoardFactory
{
	private static int maxSize = 20;
	private static int minSize = 4;

	/* ???? System.out implies console interface -- maybe it's bad to hardcode it into this class?*/
	// get size from user input
	//It'll need changing if we go ahead with a GUI.
	private void specifySize() {
		System.out.print("Specify the height/width of the board (between "+minSize+" and "+maxSize+"): ");
		size = readValueBoundedBy(minSize,maxSize);
	}

	// get number of ships from user input
	// number of ships must be positive and cannot exceed half the area of the board
	private void specifyNumShips() {
		System.out.print("Specify the number of ships: ");
		num_ships = readValueBoundedBy(1,size*size/2);
	}

	// create board
	public Board createBoard() {
		specifySize();
		specifyNumShips();
		return new Board(size, num_ships);
	}

	/* ???? should we move this method (reading input) to the client or user */
	// get a value from user input between a and b
	private int readValueBoundedBy(int a, int b) {
		// ensure that a is always the lower bound
		if ( a > b)
		{
			int swap = a;
			a = b;
			b = swap;
		}

		// read value from System.in (or other inputs???) until a valid value is found 
		int val=0;
		Scanner sc = new Scanner(System.in);	
		do {
			
			
			try{
				val = sc.nextInt();
				
			}catch(InputMismatchException e){
				sc.nextLine();
				System.out.print("Invalid input.Please enter a value between "+a+" and "+b+": ");	
				continue; 
			}
			if(val<a ||val>b)
				System.out.print("Please enter a value between "+a+" and "+b+": ");
		} while (val < a || val > b);

		return val;
	}

}