import java.security.SecureRandom;
import java.util.InputMismatchException;


public class Computer extends Player
{		private int size;
		private SecureRandom random = new SecureRandom();
		private int x;
		private int y;
		
		
	//creates a constructor for the computer
	public Computer(int newSize){
		is_user       = false;
		size          = newSize;
		move_strategy = new ComputerMove(size);
	}
	
	//method to randomly place ships on the board
	public void placeShip(){
		
		int shipCount = numShips(); 
		
		//while there are ships to still place onto board
	 	while(shipCount!=0){
	 		
	 	
				ship_strategy.automatedShipPlacer(DefaultShipSize); 
				super.placeShip();
				
			if(is_valid==1) 
						shipCount--;		
	
		}
		
	}
	
	public void makeMove(){
		move_strategy.move(move_strategy.x(), move_strategy.y());
		super.makeMove();
		
		
		if(gameState==GameState.Hit)
			move_strategy.lastHit(move_strategy.x(),move_strategy.y());
		
		
	}
	
	
	
	
	
}
