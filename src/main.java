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
	protected static Player[] players;
	protected static Scanner input;
	protected static String userInput;
	protected static Board[] boards;
	protected static int numOfShips;
	protected static int gameType;

	// method to display the menu
	public static void displayMenu(int menuType)
	{
		if (menuType == 0)
		{
			System.out.println("-----------BattleShip--------------");
			System.out.println("-----------Main menu--------");
			System.out.println("S - Start Game");
			System.out.println("Q - quit");
			System.out.println("? - Display Menu");
		}
		else if (menuType == 1)
		{
			System.out.println("-----------Game Type-------------");
			System.out.println("S - Single player");
			System.out.println("T - Two player");
		}
		else if (menuType == 2)
		{
			System.out.println("---------Board Type-------------");
			System.out.println("L - Large Board");
			System.out.println("S - Small Board");
			System.out.println("C - Custom Board");
			System.out.println("? - Display Menu");
		}
	}

	// Method that allows the user to setup their boards for the game
	private static void setupBoard()
	{
		int i = 0;
		for (; i < 2; i++)
		{
			// if player is human user
			if (players[i].is_user == true)
			{
				System.out.println("Player " + (i + 1) + " Board Setup");
				players[i].showBoard(0);
			}
			
			players[i].placeShip();
			
			if(players[i].is_user == true){
				input.nextLine();
				System.out.println("Press any key....");
				input.nextLine();
			}
		}
	}

	public static void runGame()
	{
		Boolean gameOver = false;
		int turn = 0;
		while (!gameOver)
		{
			
			if (players[turn % 2].is_user == true)
			{	
				System.out.println("Player " + ((turn % 2)+1));
				players[turn % 2].showBoard(1);
				players[turn % 2].makeMove();
			}
			else{
				players[turn % 2].makeMove();
			}
			if (players[turn % 2].gameState == GameState.GameOver)
			{
				
				System.out.println("Player " + ((turn % 2)+1) + " Wins!!!");
				gameOver = true;
			}
			else if (players[turn % 2].gameState != GameState.InvalidMove)
				turn++;
			
			else
				System.out.println("InvalidMove:The coordinates you picked are out of range.");
			
			if(players[turn % 2].is_user == true && gameType==1){
				System.out.println("Press any key....");
				input.nextLine();
			}
		}
	}

	public static void setupGame()
	{
		players = new Player[2];
		boards  = new Board[2];
	
		
		players[0] = new User();
		
		if (gameType == 1) 
			players[1] = new User();
		
		  
		 boolean done = false;
		  
		  displayMenu(2);
		BoardFactory boardFactory = null;
		//displayMenu(2);
		while (boardFactory == null)
		{
			System.out.print("Input: ");
			userInput = input.next().toLowerCase();
			// determine what kind of board to create
			if (userInput.equals("l"))
			{
				boardFactory = new LargeBoardFactory();
			}
			else if (userInput.equals("s"))
			{
				boardFactory = new SmallBoardFactory();
			}
			else if (userInput.equals("c"))
			{
				boardFactory = new CustomBoardFactory();
			}
			else
			{
				System.out.println("Error: NOT VALID INPUT");
			}
		}
		if (gameType == 0) 
			players[1] = new Computer(boardFactory.size);
		
		
		
		// create the board and register player to their own board
		for (int i = 0; i < 2; ++i)
		{
			//boardSetup = boardFactory.createBoard();
			boards[i] = boardFactory.createBoard();
			boards[i].register(players[i]);
			
			players[i].numShips(boardFactory.num_ships);
		 
		}
		
		numOfShips = boardFactory.num_ships;
		
		// The user is sent this method in which they place their ships on the
		// board
		setupBoard();
		
		// Boards are finally switched once they are setup
		boards[0].register(players[1]);
		boards[1].register(players[0]);
	}

	public static void main(String[] args)
	{
		// setup scanner for system input
		input = new Scanner(System.in);
		// display main menu
		displayMenu(0);
		boolean quit = false;
		boolean exit;
		
		
		// while user has not quit the program
		while (!quit)
		{
			// get user input for main menu
			System.out.print("Input: ");
			userInput = input.nextLine();
			userInput = userInput.toLowerCase();
			// start game
			if (userInput.equals("s"))
			{
				exit = false;
				while (exit == false)
				{
					displayMenu(1);
					System.out.print("Input: ");
					userInput = input.nextLine();
					userInput = userInput.toLowerCase();
					if ((userInput.equals("s")) || (userInput.equals("t")))
						exit = true;
				}
				if (userInput.equals("s"))
					gameType = 0;
				else
					gameType = 1;
				// lunch setup method
				setupGame();
				// start game
				runGame();
			}
			else if (userInput.equals("?"))
				displayMenu(0);
			else if (userInput.equals("q")) quit = true;
		}
	}
}