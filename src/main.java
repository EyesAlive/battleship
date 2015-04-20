import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//Ryan Jones
//Kristine Lee
//Kaeyan Jones
//Sepideh Roghanchi
//Tulsi Patel

//Main loop, in standard engine fashion

public class main
{
	
	protected static Player   player1;
	protected static Player   player2;
	protected static Scanner  input;
	protected static String   userInput;
	protected static Board    board1;
	protected static Board    board2;
	protected static int      numOfShips;
	

	//method to display the menu
	public static void displayMenu(int menuType){
		
		if(menuType==0){
			System.out.println("-----------BattleShip--------------");
			System.out.println("-----------Main menu--------");
			System.out.println("S - Start Game");
			System.out.println("Q - quit");
			System.out.println("? - Display Menu");
		}
		
		else if(menuType==1){
			System.out.println("---------Board Type-------------");
			System.out.println("L - Large Board");
			System.out.println("S - Small Board");
			System.out.println("C - Custom Board");
			System.out.println("? - Display Menu");
		}
		
		
	}
	
	
	
	//Method that allows the user to setup their boards for the game
	private static void setupBoard(){
		
		//player1 is allows a human player
		System.out.println("Player 1 Board Setup");
		player1.showBoard(0);
		player1.placeShip();
	
	
		//if player2 is a human player
		if(player2.is_user==true){
			System.out.println("Player 2 Board Setup");	
			player2.showBoard(0);
			player2.placeShip();
		
			}
		
	}
	
	public static void runGame(){
		Boolean gameOver   = false;
		player1.is_turn    = true;
		
		while(!gameOver){
			
			if(player1.is_turn==true){
				System.out.println("Player 1");
				player1.showBoard(1);
				player1.makeMove();
				
				if(player1.gameState==GameState.GameOver){
					System.out.print("Player one Wins!!!");
					gameOver=true;
				}
				
				if(player1.gameState!= GameState.InvalidMove){
					player1.is_turn=false;
					player2.is_turn=true;
				}
				else
					System.out.println("InvalidMove:The coordinates you picked are out of range.");
				
				
			}
			
			else{
				System.out.println("Player 2");
				player2.showBoard(1);
				player2.makeMove();
			
				if(player2.gameState==GameState.GameOver){
					System.out.print("Player two Wins!!!");
					gameOver=true;
				}
				
				if(player2.gameState!= GameState.InvalidMove){
					player2.is_turn=false;
					player1.is_turn=true;
				}
				else
					System.out.println("InvalidMove:The coordinates you picked are out of range.");
			}
			
		}
		
		
		
	}
	
	public static void setupGame(){
		
		//player 1
		player1  = new User();
		
		//player 2
		player2  = new User();
		
		//variable to check if done
		Boolean done = false;
		
		
		displayMenu(1);
		
		while(!done){
			System.out.print("Input: ");
			userInput = input.next();
			userInput = userInput.toLowerCase();
			
			//Creates a large board
			if(userInput .equals("l")){
				LargeBoardFactory largeBoard = new LargeBoardFactory();
				
				board1 = new Board(largeBoard.size,largeBoard.num_ships);
				board2 = new Board(largeBoard.size,largeBoard.num_ships);
				
				board1.register(player1);
				board2.register(player2);
				player1.numShips(largeBoard.num_ships);
				player2.numShips(largeBoard.num_ships);
				numOfShips = largeBoard.num_ships;
				
				done  = true;
			
			}
			//Creates a small board
			else if(userInput.equals("s")){
				SmallBoardFactory smallBoard = new SmallBoardFactory();
				
				board1 = new Board(smallBoard.size,smallBoard.num_ships);
				board2 = new Board(smallBoard.size,smallBoard.num_ships);
				
				board1.register(player1);
				board2.register(player2);
				player1.numShips(smallBoard.num_ships);
				player2.numShips(smallBoard.num_ships);
				
				numOfShips = smallBoard.num_ships;
				done       = true;
				
			}
			
			//Create a Custom board
			else if(userInput.equals("c")){
				CustomBoardFactory customBoard = new CustomBoardFactory();
				customBoard.createBoard();
				
				board1 = new Board(customBoard.size,customBoard.num_ships);
				board2 = new Board(customBoard.size,customBoard.num_ships);
				
				board1.register(player1);
				board2.register(player2);
				player1.numShips(customBoard.num_ships);
				player2.numShips(customBoard.num_ships);
				
				numOfShips = customBoard.num_ships;
				done       = true;	
			}
			//what was entered was not valid 
			else
				System.out.println("Error: NOT VALID INPUT");
			
		}
		
		
		//The user is sent this method in which they place their ships on the board
		setupBoard();
		
		//Boards are finally switched once they are setup
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
			if(userInput.equals("s")){
				
		
				//lunch setup method	
				setupGame();
			
				
				//start game 
				runGame();
				
			}
			else if(userInput.equals("q")){
				quit = true;
			}
			
			
			
		}
	}
}