import java.security.SecureRandom;
import java.util.InputMismatchException;


public class Computer extends Player
{		private int size;
		private SecureRandom random = new SecureRandom();
		private int x;
		private int y;
		private ComputerMove computer;
		
	//creates a constructor for the computer
	public Computer(int newSize){
		is_user       = false;
		size          = newSize;
		move_strategy = new ComputerMove(size);
		computer      = new ComputerMove(size);
	
	}
	
	//method to randomly place ships on the board
	public void placeShip(){
		
		int tempCount = numShips(); 
		int randomChoice;
		char orientation = 0;
		//while there are ships to still place onto board
	 	while(tempCount!=0){
	 		//gets a random x and y coordinate
	 		x = random.nextInt(size);
	 		y = random.nextInt(size);
	 		
	 		
	 		//gets a random number to choose orientation
	 		randomChoice = random.nextInt(1);
	 		
	 		if(randomChoice == 0)
	 			orientation = 'v';
	 		
	 		else
	 			orientation = 'h';
	 	
				ship_strategy.place(x, y, DefaultShipSize,orientation); 
				super.placeShip();
				
			if(is_valid==1) 
						tempCount--;		
	
		}
		
	}
	
	public void makeMove(){
		move_strategy.move(move_strategy.x(), move_strategy.y());
		super.makeMove();
		computer.gameState(gameState);
		
		if(gameState==GameState.Hit)
			move_strategy.lastHit(move_strategy.x(),move_strategy.y());
		
		
	}
	
	
	
	
	
}
