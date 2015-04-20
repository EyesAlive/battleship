import java.util.Random;
import java.util.ArrayList;

public class ComputerMove implements MoveStrategy
{
	private ArrayList<Coordinate> availableMoves;
	private ArrayList<Coordinate> lastHit;
	
	public static boolean engaged; // 1 if last move was a hit
	private Coordinate coordinate;
	private int x;
	private int y;
	private int smartGuessDir = 0; // keeps track of the attacked coordinates by getNextHit() method
	
	// Constructor
	public ComputerMove(int size){
		x = 0;
		y = 0;
		availableMoves = new ArrayList<Coordinate>();
		int i, j;
		engaged = false;
		Coordinate coordinate;
		// All the coordinates are available in the beginning
		for (i = 0; i < size; i++){
			for (j = 0; j < size; j++){
				coordinate = new Coordinate(i, j);
				availableMoves.add(coordinate);
			}
		}
		lastHit = new ArrayList<Coordinate>();
	}
	
	
	//Methods
	public void move(int input_x, int input_y)
	{
		int rand_index;
		int inBoundary = 0;
		Coordinate nextGuess = null;
		Coordinate prevLastHit = null;
		
		
		// Otherwise make random moves
		if (engaged == false){
			Random rand = new Random();
			 
			rand_index = rand.nextInt(availableMoves.size());
			nextGuess = availableMoves.get(rand_index);
		}
	 
		else if (engaged == true){
			while (inBoundary == 0){
				nextGuess = getNextHit();
				if (availableMoves.contains(nextGuess) == true){
					inBoundary = 1;
				}
			}
		}
		
		input_x = nextGuess.x();
		input_y = nextGuess.y();
		
		availableMoves.remove(availableMoves.indexOf(nextGuess));
		
		x(input_x);
		y(input_y);
	}
	
	public void makeAMove(){
		int rand_index;
		int inBoundary = 0;
		int input_x;
		int input_y;
		Coordinate nextGuess = null;
		Coordinate prevLastHit = null;
		
		// If the last move was a hit we should make smart moves
		if (engaged == true){
			 prevLastHit = lastHit.get(lastHit.size()-1);
		}
		
		if (engaged == true ||  (lastHit.get(lastHit.size()-1) == prevLastHit)){
			while (inBoundary == 0){
				nextGuess = getNextHit();
				if (availableMoves.contains(nextGuess) == true){
					inBoundary = 1;
				}
			}
		}
		
		// Otherwise make random moves
		else if (engaged == false){
			Random rand = new Random();
			 
			rand_index = rand.nextInt(availableMoves.size());
			nextGuess = availableMoves.get(rand_index);
		}
		
		input_x = nextGuess.x();
		input_y = nextGuess.y();
		
		availableMoves.remove(availableMoves.indexOf(nextGuess));
		
		x(input_x);
		y(input_y);
		
	}

	
	// Don't know why this method is needed ???
	public void checkMoveHistory(int x, int y){
		
	}
	
	public Coordinate getNextHit(){
		
		Coordinate last_hit = lastHit.get(lastHit.size()-1);
		coordinate = last_hit;
		
		switch (smartGuessDir){
		
		case 0: //North
			coordinate.y(coordinate.y()+1);	
		case 1: //South
			coordinate.y(coordinate.y()-1);
		case 2: //East
			coordinate.x(coordinate.x()+1);
		case 3: //West
			coordinate.x(coordinate.x()-1);
		}
		
		smartGuessDir = (smartGuessDir + 1) % 4;
		
		return coordinate;
	}
	
	//Setters
	public void x(int input_x)
	{
		x = input_x;
	}
	
	public void y(int input_y)
	{
		y = input_y;
	}
	
	
	//Getters
	public int x() { return x; }
	public int y() { return y; }

	/*public void gameState(GameState state)
	{
		if(state == GameState.Hit)
			engaged = true;
		
		else if(state == GameState.Sunk)
			engaged = false;
			
	}*/
	
	/*public void lastHit(int x, int y){
		coordinate.x(x);
		coordinate.y(y);
		lastHit.add(coordinate);	
	}*/
}

/*
 * Notes:
 * 	main class should set engaged field
 * 	Should consider the case of having adjacent ships (//private ArrayList<Coordinate> adjacentShips;) 
 * 	How to find the size of board and ships 
 */

