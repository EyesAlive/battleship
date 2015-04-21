import java.util.Random;
import java.util.ArrayList;

public class ComputerMove implements MoveStrategy
{
	private ArrayList<Coordinate> availableMoves;
	private ArrayList<Coordinate> lastHit;
	
	public static boolean engaged; // 1 if last move was a hit
	private Coordinate coordinate;
	private int boardSize;
	private int x;
	private int y;
	private int index;
	private int smartGuessDir = 0; // keeps track of the attacked coordinates by getNextHit() method
	
	// Constructor
	public ComputerMove(int size){
		x = 0;
		y = 0;
		availableMoves = new ArrayList<Coordinate>();
		boardSize = size;
		int i, j;
		engaged = false;
	  
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
		Random rand = new Random();
		// If the last move was a hit we should make smart moves
		if (engaged == true){
			 prevLastHit = lastHit.get(lastHit.size()-1);
		}
		
	
		if (engaged == false){
					rand_index = rand.nextInt(availableMoves.size());
					index      = rand_index;
					nextGuess  = availableMoves.get(rand_index);
		}
		
		
		else if (engaged == true ||  (lastHit.get(lastHit.size()-1) == prevLastHit)){
			while (inBoundary == 0){
				nextGuess = getNextHit();
				if (containGuess(nextGuess) == true){
					System.out.println("move3");
					inBoundary = 1;
				}
				else if(smartGuessDir==0){
					rand_index = rand.nextInt(availableMoves.size());
					index      = rand_index;
					nextGuess  = availableMoves.get(rand_index);
					inBoundary = 1;
				}
					
				System.out.println("move4");

			}
		}
		
		
		
		input_x = nextGuess.x();
		input_y = nextGuess.y();
		
		//availableMoves.remove(availableMoves.indexOf(nextGuess));
		availableMoves.remove(index);
		x(input_x);
		y(input_y);
	}
	
	
	
	// Don't know why this method is needed ???
	public void checkMoveHistory(int x, int y){
		
	}
	
	public boolean containGuess(Coordinate nextGuess){
		
		int midPoint;
		int i;
		int j;
		int size = availableMoves.size();
		Coordinate iCoordinate;
		Coordinate jCoordinate;
		if(size%2==0)
			midPoint = size/2;
		
		else
			midPoint = (size+1)/2;
		
		i = midPoint;
		j = midPoint;
		
		
		for(;i<size ||  j>=0;i++,j--){
			
			if(i<size){
				iCoordinate = availableMoves.get(i);
				
				if(iCoordinate.contains(nextGuess)==true){
					index = i;
					return true;
				}
			}
			
			if(j>=0){
				jCoordinate = availableMoves.get(j);
			
			 if(jCoordinate.contains(nextGuess)==true){
				index = j;
				return true;
			 }	
			}
			
		}
		return false;
	}
	
	
	public Coordinate getNextHit(){
		
		int x;
		int y;
		Coordinate last_hit = lastHit.get(lastHit.size()-1);
		x = last_hit.x();
		y = last_hit.y();
		
		//North
		if(smartGuessDir==0)
			y++;
		 //South
		else if(smartGuessDir==1)
			y--;
		//East
		else if(smartGuessDir==2)
			x++;
		//West
		else if(smartGuessDir==3)
			x--;
		
		smartGuessDir = (smartGuessDir + 1) % 4;
		
		coordinate = new Coordinate(x,y);
		
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

	public void gameState(GameState state)
	{
		if(state == GameState.Hit)
			engaged = true;
		
		else if(state == GameState.Sunk)
			engaged = false;
		
		else if(state == GameState.Miss)
			engaged = false;
			
			
	}
	
	public void lastHit(int x, int y){
		coordinate = new Coordinate(x,y);
		lastHit.add(coordinate);	
	}
}

/*
 * Notes:
 * 	main class should set engaged field
 * 	Should consider the case of having adjacent ships (//private ArrayList<Coordinate> adjacentShips;) 
 * 	How to find the size of board and ships 
 */

