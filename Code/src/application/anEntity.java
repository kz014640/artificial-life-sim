package application;

import java.util.Random;
import java.util.Scanner;

public abstract class anEntity {
	public static enum Direction{
		North,
		East,
		South,
		West;
		
	public static Direction getrandomdirection(){
		Random rand = new Random();
		return values()[rand.nextInt(values().length)];
		

	}
}
	//public anEntity AE1 = new anEntity();
	String species;
	char aSymbol;
	int horizontal;
	int verticalPosition;
	int energy; 
	public aWorld myWorld = new aWorld();
	int ID=0;
	//local  varibles for an entity class
	
	public abstract String getSpecies();
	public abstract void setSpecies(String species);
	public abstract char getaSymbol();
	public abstract void setaSymbol(char aSymbol);
	public abstract int getHorizontal();
	public abstract void setHorizontal(int horizontal);
	public abstract int getVerticalPosition();
	public abstract void setVerticalPosition(int verticalPosition);
	public abstract int getEnergy();
	public abstract void setEnergy(int energy);
	public abstract int getID();
	public abstract void setID(int iD);
	// the above are the getters and setters for the an entity class
	public abstract boolean isHere (int sx, int sy);
	public abstract void displayEntity(anInterface r); //used to display an entity
	public abstract String toString();	
	public abstract String totext();
	/**
	 * Uses the string array and the array list to analyze where the food is from an entity 
	 * @param  d  	the direction to be checked
	 * @param 	range	the range in which the direction should be checked
	 * @return 			returns true if there is food in that range or false if there is not
	 */
public abstract Boolean smellFood(Direction d, int range);
	
	

}
