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
		ship_strategy = new ComputerShip();
		computer      = new ComputerMove(size);
	
	}
	
	//method to randomly place ships on the board
	public void placeShip(){
		int shipsToPlace = numShips(); 
		//while there are ships to still place onto board
		while(shipsToPlace>0){
			ship_strategy.place(DefaultShipSize);
			notifyObservers(0);
			if(is_valid==true) 
				shipsToPlace--;
		}
	}
	
	public void makeMove(){
		move_strategy.move(move_strategy.x(), move_strategy.y());
		super.makeMove();
		//computer.gameState(gameState);
		//move_strategy.lastHit(move_strategy.x(),move_strategy.y());
		
		
	}
	
	
	
	
	
}
