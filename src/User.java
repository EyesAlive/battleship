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
	
	public void makeMove()
	{
		System.out.println("Enter in your move: ");
		
		super.makeMove();
	}
	@SuppressWarnings("resource")
	public void placeShip(){
		
	
	 String   userInput;
	 char     orientation;
	 int tempCount = numShips(); 
	 
	 	while(tempCount!=0){
	 		
	 		System.out.println("Ships:"+tempCount);
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
				System.out.println("Please choose V(vertical) or H(horizontal):");
			
			showBoard();
		}
		
		
		
	}
	//public endGame()
	
	
}