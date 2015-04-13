
import java.util.Scanner;

public class UserMove implements MoveStrategy
{	
	private int x;
	private int y;
	
	Scanner input = new Scanner (System.in);
		
	public void move(int input_x, int input_y)
	{
		
		x(input_x);
		y(input_y);

	}
	
	//setters
	public void x(int input_x){
		x = input_x;
	}
	public void y(int input_y){
		y = input_y;
	}
	
	// getters
	public int x(){
		return x;
	}
	
	public int y(){
		return y;
	}
	
}