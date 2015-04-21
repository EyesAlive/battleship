import java.util.InputMismatchException;
import java.util.Scanner;



public class User extends Player
{
	protected Scanner  input;
	protected int x;
	protected int y;
	//private board_factory
	
	//no-arg constructor
	public User(){
		is_user = true;
		move_strategy = new UserMove();
		input = new Scanner(System.in);	
	}
	
	//method to make a move
	public void makeMove()
	{	
		
		Boolean validInput = false;
		String userInput;
		
		//as along as input is not valid continue
		while(!validInput){
			
			System.out.println("S- Show your board");
			System.out.print("Input: ");
			
			//makes sure that input is a int
			if(input.hasNextInt()){
				//catches any invalid input that is not an integer
				try{
					
					//gets x and y coordinates to make a move
					//then places it into move_strategy
					x = input.nextInt();
					x = x - 1;
				
					y = input.nextInt();
					y = y - 1;
					
					
					validInput = true;
					
					move_strategy.move(x, y);
					super.makeMove();
					
				}catch(InputMismatchException e){
					input.nextLine();
					System.out.println("Invalid input. Please enter in a valid number.");
					continue;
				}
			}
			//if it is not there must be something else
			else{
				userInput = input.next();
				userInput.toLowerCase();
				input.nextLine();
				if(userInput=="s")
					showBoard(0);
			}
			showBoard(1);
		}
		
		
	}
	//method to place ships onto the board
	public void placeShip()
	{
		String userInput;
		int tempCount = numShips(); 
		int x = 0,y=0;

		//while there are ships to still place onto board
		while (tempCount > 0)
		{
			System.out.println("Ships to place: "+tempCount);
			System.out.print("Enter in a valid location in the form ROW COLUMN, e.g. 5 9: ");
			//check input for anything that is not a int
			
			if (input.hasNextLine()) {
				String s[] = input.nextLine().trim().split("\\s+");
				
				if (s.length == 2) {
					try {
						x = Integer.parseInt(s[0]) - 1;
						y = Integer.parseInt(s[1]) - 1;
					}
					catch (NumberFormatException e ) {
						System.out.println("Invalid input.");
						continue;
					}
				}
				else {
					System.out.println("Invalid input");
					continue;
				}
			}

			//get orientation of ships
			char orientation = 0;
			while (orientation != 'h' && orientation != 'v') {
				System.out.println("Enter V for vertical (going down) or H for horizontal (going right)");
				if (input.hasNextLine()) {
					userInput = input.nextLine().trim().toLowerCase();
					if (userInput.length() == 1) {
						orientation = userInput.charAt(0);
						if (orientation == 'h' || orientation == 'v')
							break;
					}
				} else break;
			}
			
			// place the ship
			ship_strategy.place(x, y, DefaultShipSize, orientation);
			notifyObservers(0);
			if (is_valid == true)
				tempCount--;
			else {
				System.out.println("invalid location");
			}
			showBoard(0);
		}
	}
	
	public void endGame(int shipNum)
	{
		
	}
}