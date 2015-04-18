import java.util.ArrayList;
import java.util.Scanner;

//Ryan Jones
//Kristine Lee
//Kaeyan Jones
//Sepideh Roghanchi
//Tulsi Patel

//Main loop, in standard engine fashion

public class main
{
	
	protected static Player            player1;
	protected static Player            player2;
	protected static Scanner           input;
	protected static String            userInput;
	protected static Board             board1;
	protected static Board             board2;
	
	//method to displaymenu
	private static void displayMenu(int menuType){
		
		if(menuType==0){
			System.out.println("-----------BattleShip--------------");
			System.out.println("-----------Main menu--------");
			System.out.println("S - Start Game");
			System.out.println("Q - quit");
		}
		
		else if(menuType==1){
			System.out.println("-------Board Type-------------");
			System.out.println("L - Large Board ");
			System.out.println("S - Small Board");
			System.out.println("C - Custom Board");
		}
	}
	
	
	
	
	private static void setupBoard(){
		
		
		
	}
	
	private static void runGame(){
		Boolean gameOver       = false;
		player1.is_turn = true;
		
		while(!gameOver){
			
			if(player1.is_turn==true){
			
			
			
			
			}
			
			else{
			
			
			
			
			}
			
			
			
		}
		
		
		
	}
	
	private static void setupGame(){
		
		//player 1
		player1  = new Player();
		
		//player 2
		player2  = new Player();
		
		//variable to check if done
		Boolean done = false;
		
		displayMenu(1);
		while(!done){
			System.out.println("Input: ");
			userInput = input.nextLine();
			userInput = userInput.toLowerCase();
			
			//Creates a large board
			if(userInput == " l"){
				LargeBoardFactory largeBoard = new LargeBoardFactory();
				board1 = new Board(largeBoard.size,largeBoard.num_ships);
				board2 = new Board(largeBoard.size,largeBoard.num_ships);
				board1.register(player1);
				board2.register(player2);
				done  = true;
			
			}
			//Creates a small board
			else if(userInput == "s"){
				SmallBoardFactory smallBoard = new SmallBoardFactory();
				board1 = new Board(smallBoard.size,smallBoard.num_ships);
				board2 = new Board(smallBoard.size,smallBoard.num_ships);
				board1.register(player1);
				board2.register(player2);
				done = true;
				
			}
			
			//Create a Custom board
			else if(userInput == "c"){
				CustomBoardFactory customBoard = new CustomBoardFactory();
				board1 = new Board(customBoard.size,customBoard.num_ships);
				board2 = new Board(customBoard.size,customBoard.num_ships);
				board1.register(player1);
				board2.register(player2);
				done = true;
				
			}
			//what was entered was not valid 
			else
				System.out.println("Error: NOT VALID INPUT");
			
		}
		
		//The user is sent this method in which they place their ships on the board
		setupBoard();
		
		//Boards are finally switched once they are setup
		player1.remove(board1);
		player2.remove(board2);
		board1.register(player2);
		board2.register(player1);		
		
	}
	
	
	public static void main(String[] args)
	{
		//setup scanner for system input
		input = new Scanner(System.in);
		
		//display main menu 
		displayMenu(0);
		boolean quit = false;
		
		//while user has not quit the program
		while (!quit)
		{
			//get user input for main menu 
			System.out.print("Input: ");
			userInput = input.nextLine();
			userInput = userInput.toLowerCase();
			
			//start game
			if(userInput == "s"){
				
			//lunch setup method	
			setupGame();
			
			//start game 
			runGame();
				
			}
			else if(userInput == "q"){
				quit = true;
				
			}
			
			
			
		}
	}
}