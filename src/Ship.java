

import java.util.*;

public class Ship
{
	private int length; //# of blocks on board
	private boolean is_sunk; //initially ship is not sunk
	private ArrayList<Coordinate> position;
	public ArrayList<Coordinate> hit_list;
	
	//size= user entered length
	//coord_Xposition/coord_Yposition = user entered coordinate
	//side= user entered side of the ship(l or R)
	//Constructor 
	public Ship(int size, int coord_Xposition, int coord_Yposition, char side)
	{
		length = size;
		is_sunk = false;

		//case r and case l
		if(side == 'r')
		{
			for(int i = 0; i < size; i++)
			{
				//adding given coords to the List	
				Coordinate temp= new Coordinate(coord_Xposition, coord_Yposition);

				coord_Xposition++; //because assume horzontal ship

				//add to the list
				position.add(temp);
			} //closing for loop
		} //closing if
		else if (side == 'l')
		{
			for (int i = 0; i< size; i++)
			{
				//adding given coords to the List	
				Coordinate temp= new Coordinate(coord_Xposition, coord_Yposition);

				coord_Xposition--; //because assume horizontal ship

				//add to the list
				position.add(temp);
			} //closing for loop
		} //closing if
	}

	//method 1
	public boolean checkIfSunk()
	{
		return is_sunk;
	}

	//method 2
	//guess = user entered guess
	public boolean checkIfHit(Coordinate guess)
	{
		//checks given guess into ArrayList if yes then true else false
		boolean result=	position.contains(guess);
		if (result == true)
		{
			//set to HIT
			guess.setState(CoordState.HIT);
		}
		return result;	

	}
	
	//Need a better implementation of checkIfHit for Board hence hitEmHard (rename later)
	//Might be insecure as public
	public boolean hitEmHard(int x, int y)
	{
		for (int i = 0; i < position.size(); i++)
		{
			if (position.get(i).equals(x, y))
			{
				position.get(i).setState(CoordState.HIT);
				hit_list.add(position.get(i));
				checkSunk();
				position.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkSunk()
	{
		if (is_sunk)
			return true;
		if (position.size() == hit_list.size())
		{
			is_sunk = true;
			return true;
		}
		return false;
	}
}