package application;

import java.util.Random;



public class Food extends anEntity{
	
	//public anEntity AE1 = new anEntity();	
	
	//local  varibles for an entity class
	public Food(){
		ID++;
		horizontal =0;
		energy =0;
		verticalPosition =0;
		aSymbol ='z';
		species ="dummy";
		//initialise all attributes with suitable default values 
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public char getaSymbol() {
		return aSymbol;
	}
	public void setaSymbol(char aSymbol) {
		this.aSymbol = aSymbol;
	}
	public int getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}

	public int getVerticalPosition() {
		return verticalPosition;
	}
	public void setVerticalPosition(int verticalPosition) {
		this.verticalPosition = verticalPosition;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	// the above are the getters and setters for the an entity class
	public boolean isHere (int sx, int sy) {
		return sx == horizontal && sy == verticalPosition;			// is robot at x,y?
	}
	
	public void displayEntity(anInterface r) {
		/**
		 * display robot in the interface r
		 */
		r.showRobot(horizontal, verticalPosition);							// just send details of robot to interface
	} //used to display an entity

	
	public Food(anEntity AE){
		
		species =AE.species;
		ID = AE.ID;
		aSymbol =AE.aSymbol;
		horizontal =AE.horizontal;
		energy = AE.energy;
		verticalPosition =AE.verticalPosition;
		aSymbol =AE.aSymbol;
		//setter for AE object
	}
	public String toString(){
		String posSpe;
		posSpe = species + horizontal + verticalPosition;
		
		return posSpe;
		//returns the species and position
	}
	
	public String totext() {
		return "anEntity [ species=" + species + ", aSymbol="
				+ aSymbol + ", horizontal=" + horizontal + 
				", verticalPosition=" + verticalPosition
				+ ", energy=" + energy + ", ID=" + ID + "]";
		//totext function to print all of the varibles of the anentity class
	}
public Boolean smellFood(Direction d, int range){

	// function to determine if there is food in a given direction and range
	int count=0;

	switch(d){
		case North:
			//case statement to check if there is food above the entity
			//System.out.println("state 1");
			count=0;
			for(int i=horizontal-1;i>0;i--){
				if(count==range){
					break;
					//if the loop does not find something within the range break
				}
				count++;
				//System.out.println(myWorld.Display[horizontal][i]);
				if(myWorld.Display[i][verticalPosition].equals("5")){
					//if the loop does find something withing the range then return true
					//System.out.println("state 11");
					return true;
				}
			}
			break;
			//if it does not find anything within the range and the size of the display then break
		case East:
			//System.out.println("state 2");
			count=0;
			for(int i=verticalPosition+1;i<myWorld.sizev+1;i++){
				if(count==range){
					break;
					//if the loop does not find something within the range break
				}
				count++;
				if(myWorld.Display[horizontal][i].equals("5")){
					//System.out.println("state 22");
					return true;
				}
			}
			break;
			//if it does not find anything within the range and the size of the display then break
		case South:
			count=0;
			//System.out.println("state 3");
			for(int i=horizontal+1;i<myWorld.sizeh+1;i++){
				if(count==range){
					break;
					//if the loop does not find something within the range break
				}
				count++;
				if(myWorld.Display[i][verticalPosition].equals("5")){
					//System.out.println("state 33");
					return true;
				}
			}
			break;
			//if it does not find anything within the range and the size of the display then break
		case West:
			count=0;
			//System.out.println("state 4");
			for(int i=verticalPosition-1;i>0;i--){
				if(count==range){
					break;
					//if the loop does not find something within the range break
				}
				count++;
				if(myWorld.Display[horizontal][i].equals("5")){
					//System.out.println("state 44");
					return true;
			}
			}
			break;
			//if it does not find anything within the range and the size of the display then break
		}
		
		return false;
		//return false if there was no food in the given direction and range
	}
}
