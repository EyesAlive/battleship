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
	
	protected static ArrayList<Player> players;
	protected static Player            player;
	protected static Scanner           input;
	protected static String            userInput;
	protected static Board             board1;
	protected static Board             board2;
	
	private static void menu(int menuType){
		
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
	
	
	
	
	private static void boardSetup(){
		
		
		
	}
	
	
	private static void setupGame(){
		//creating arrayList of players
		players = new ArrayList<Player>();
		
		//player 1
		player  = new Player(0);
		players.add(player);
		
		//player 2
		player  = new Player(1);
		players.add(player);
		
		//variable to check if done
		Boolean done = false;
		
		menu(1);
		while(!done){
			System.out.println("Input: ");
			userInput = input.nextLine();
			userInput = userInput.toLowerCase();
			
			//Creates a large board
			if(userInput == " l"){
				LargeBoardFactory largeBoard = new LargeBoardFactory();
				board1 = new Board(largeBoard.size,largeBoard.num_ships,0,players.get(0));
				board2 = new Board(largeBoard.size,largeBoard.num_ships,1,players.get(1));
				board1.register(players.get(1));
				board2.register(players.get(0));
				done  = true;
			
			}
			//Creates a small board
			else if(userInput == "s"){
				SmallBoardFactory smallBoard = new SmallBoardFactory();
				board1 = new Board(smallBoard.size,smallBoard.num_ships,0,players.get(0));
				board2 = new Board(smallBoard.size,smallBoard.num_ships,1,players.get(1));
				board1.register(players.get(1));
				board2.register(players.get(0));
				done = true;
				
			}
			
			//Create a Custom board
			else if(userInput == "c"){
				CustomBoardFactory customBoard = new CustomBoardFactory();
				board1 = new Board(customBoard.size,customBoard.num_ships,0,players.get(0));
				board2 = new Board(customBoard.size,customBoard.num_ships,1,players.get(1));
				board1.register(players.get(1));
				board2.register(players.get(0));
				done = true;
				
			}
			//what was entered was not valid 
			else
				System.out.println("Error: NOT VALID INPUT");
			
		}
		
		
		
	}
	
	
	public static void main(String[] args)
	{
		
		input = new Scanner(System.in);
		menu(0);
		boolean quit = false;
		while (!quit)
		{
			System.out.print("Input: ");
			userInput = input.nextLine();
			userInput = userInput.toLowerCase();
			
			if(userInput == "s"){
				
				
				
			}
			else if(userInput == "q"){
				quit = true;
				
			}
			
			
			
		}
	}
}