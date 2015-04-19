import java.util.InputMismatchException;
import java.util.Scanner;



public class User extends Player
{
	//private board_factory
	
	//no-arg constructor
	public User(){
		is_user = true;
		move_strategy = new UserMove();
		
	}
	
	public void makeMove(MoveStrategy newMove)
	{
		super.makeMove(newMove);
	}
	@SuppressWarnings("resource")
	public void placeShip(){
		
	 Scanner  input;
	 String   userInput;
	 char     orientation;
	 int x;
	 int y;
	 int tempCount = numShips();
	 
	 
	 input = new Scanner(System.in);
	 	while(tempCount!=0){
	 		
	 		System.out.print("Enter in a valid location: ");
			try{
				x = input.nextInt();
				x = x - 1;
				
				y = input.nextInt();
				y = y - 1;
				
				}catch(InputMismatchException e){
					input.nextLine();
					System.out.println("Invalid input. Please enter in a valid number.");
					continue;
				}
			
			
			
			userInput = input.next();
			userInput = userInput.toLowerCase();
			orientation = userInput.charAt(0);
			System.out.println(orientation);
			
			//System.out.println("x: " +x+ " y: "+ y);
			if(((orientation=='v')||(orientation=='h'))){
				ship_strategy.place(x, y, DefaultShipSize,orientation); 
				super.placeShip();
				if(is_valid==true) 
						tempCount--;		
			}
			else
				System.out.println("Please choose V(vertical) or H(horizontal)");
			
			showBoard();
		}
		
		
		
	}
	//public endGame()
	
	
}