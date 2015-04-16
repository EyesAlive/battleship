

import java.util.*;

public class Ship
{
	private int length; //# of blocks on board
	private boolean is_sunk;//initially ship is not sunk
	private ArrayList<Coordinate> position;
	
	//size= user entered length
	//coord_Xposition/coord_Yposition = user entered coordinate
	//side= user entered side of the ship(l or R)
	//constructor 
	public Ship(int size, int coord_Xposition,int coord_Yposition, char side){
		length = size;
		is_sunk = false;
		
		//case r and case l
		if(side == 'r'){
		for(int i=0 ;i< size;i++){
		//adding given coords to the List	
		Coordinate temp= new Coordinate(coord_Xposition, coord_Yposition);
		
		coord_Xposition++; //because assume horzontal ship
		
		//add to the list
		position.add(temp);
		}//closing for loop
		}//closing if
		if(side == 'l'){
		for(int i=0 ;i< size;i++){
		//adding given coords to the List	
		Coordinate temp= new Coordinate(coord_Xposition, coord_Yposition);
		
		coord_Xposition--; //because assume horizontal ship
		
		//add to the list
		position.add(temp);
		}//closing for loop
		}//closing if
		
		
	}
	
	//method 1
	public boolean checkIfSunk(){
		return is_sunk;
	}
	
	//method 2
	//guess = user entered guess
	public boolean checkIfHit(Coordinate guess){
		//checks given guess into ArrayList if yes then true else false
	boolean result=	position.contains(guess);
	if(result == true){
		//set to HIT
		guess.setState(CoordState.HIT);
	}
		return result;	
		
	}

}