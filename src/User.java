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
		int shipsToPlace = numShips();
		while (shipsToPlace > 0) {
			System.out.println("Ships to place: "+shipsToPlace);
			ship_strategy.place(DefaultShipSize);
			notifyObservers(0);
			if (is_valid)
				--shipsToPlace;
			else
				System.out.println("invalid location");
			showBoard(0);
		}
	}
	
	public void endGame(int shipNum)
	{
		
	}
}