
import java.util.Scanner;

public class UserMove implements MoveStrategy
{	
	private int x;
	private int y; // char or int?
	
	Scanner input = new Scanner (System.in);
		
	public void move() // return move?
	{
		System.out.println("Please enter your guess:");
		System.out.println("row:");
		y = input.nextInt();
		input.nextLine();
		System.out.println("column:");
		x = input.nextInt();
		input.nextLine();
		System.out.format("attacking row %d and column %d ..\n", y, x);
	}
	
	// getters
	public int x(){
		return x;
	}
	
	public int y(){
		return y;
	}	
	
}