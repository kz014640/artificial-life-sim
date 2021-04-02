package application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class aWorld {
	int max;
	int sizev;
	int sizeh;
	String[][] Display;
	//local varibles for aworld class
	
	public aWorld(){
		sizev = 50;
		sizeh=50;	
		max =3;
		//deafult values
	}
	public aWorld(int sizev1, int sizeh1,int max1){
		sizev = sizev1;
		sizeh = sizeh1;
		max = max1;
		//constrcutor used to set values
	}
	/**
	 * Uses the string array and the array list to analyze where the food is from an entity 
	 * @param  AE 	an entity which is to be added to the world
	 * @param 	world	aworld which is to have an entity added
	 * @return 			returns the entity once it has been added
	 */
	public anEntity addworld(anEntity AE, aWorld world){
		//add function to place an entity in a specfic place
		Random rand = new Random();
		//the following asks and assigns values to specific varibles
		Scanner scan = new Scanner(System.in);	
		System.out.println("enter a species");
		AE.species = scan.nextLine();
		AE.aSymbol = AE.species.charAt(0);
		System.out.println("enter a horizontal");
		AE.horizontal = scan.nextInt();
		System.out.println("enter the verticalPostition");
		AE.verticalPosition = scan.nextInt();
		//if  the species is not an object then the energy of the entity is equal to 5 as it will be a food or an animal
		if(AE.species != "obstacle"){
			AE.energy = 5;
		}
		else{
			//while if it is an obstacle it will not have any energy
			AE.energy =0;
		}
		
		//System.out.println("enter the energy");
		//AE.energy = scan.nextInt();
		//System.out.println(AE.totext());
		//System.out.println(AE.toString());
		return AE;
	}
	/**
	 * Uses the string array and the array list to analyze where the food is from an entity 
	 * @param  array	the user inputs a string 2d array to be used as the world
	 * @param 	AE	Anentity object to be added to the 2d array
	 * @param 	world 	the world in which the objects exist
	 * @return 			returns an entity once it has been given random x and y values
	 */
	public anEntity addworldrand(anEntity AE, aWorld world, String[][] array){
		//add function to place an entity in a random place
		Random rand = new Random();
		
		Scanner scan = new Scanner(System.in);
		//if the species name has not already been determined asked the user to define it
		if(AE.species.equals("dummy")){
		System.out.println("enter a species");
		AE.species = scan.nextLine();
		}
		//the symbol of the species is the first character of the string
		AE.aSymbol = AE.species.charAt(0);
		for(int i=0;i>-1;i++){
			// an infinte loop which will only break when it finds a vertical and horizontal slot not already taken
		AE.horizontal = rand.nextInt(world.sizeh)+1;
		AE.verticalPosition = rand.nextInt(world.sizev)+1;
		if(array[AE.horizontal][AE.verticalPosition] == " "){
			break;
			//once it finds an empty slot break
		}
		}
		//if  the species is not an object then the energy of the entity is equal to 5 as it will be a food or an animal
		if(AE.species != "obstacle"){
			AE.energy = 5;
		}
		else{
			//while if it is an obstacle it will not have any energy
			AE.energy =0;
		}
		
		//System.out.println("enter the energy");
		//AE.energy = scan.nextInt();
		//System.out.println(AE.totext());
		//System.out.println(AE.toString());
		return AE;
	}
	//function used to list the entities in the world object using the totext function from anentity class
	/**
	 * Uses the string array and the array list to analyze where the food is from an entity 
	 * @param  world	the user inputs a world which contains the array list
	 * @param 	aEwor	an arraylist to be used to be printed
	 * @return 			returns a string of all the animals
	 */
	public String listWorld(aWorld world, ArrayList<LifeForm> aEwor){
		String word="";
		System.out.println(world.max +" " + aEwor.size());
		for(int i=0;i<aEwor.size();i++){
			if(aEwor.get(i)!=null){
			System.out.println(aEwor.get(i).species);
			if (aEwor.get(i) instanceof LifeForm) {
				word= word+ "\n"+"Calling LifeForm to Text: " + ((LifeForm)aEwor.get(i)).totext();
			} else {
				System.out.println("Not a LifeForm so calling generic to Text: " + aEwor.get(i).totext());
			}
		}
		}
		return word;
	}
	//function used to check if there is food at a give position7
	/**
	 * Uses the string array and the array list to analyze where the food is from an entity 
	 * @param  AE	an array list to check if its food
	 * @param 	x	x location
	 * @param 	y	y location
	 * @return 			returns 1 if there is food at the location
	 */
	public int checkfood(anEntity[] AE, int x, int y){
		for(int i =0; i<AE.length;i++){
			if(AE[i].species.equals("food")){
				//check to see if the entity is food and not an obstacle or an animal
				if(AE[i].horizontal == x && AE[i].verticalPosition==y){
					return 1;
					//return one if there is food at the given position
				}
			}
		}
		
		return 0;
	}
	//check to see if the slot is empty at a given position and return 1 if it is
	/**
	 * Uses the string array and the array list to analyze where the food is from an entity 
	 * @param  dip	the user inputs a string 2d array to be used as the world
	 * @param 	x	x location
	 * @param 	y	y location
	 * @return 			returns 1 if there is nothing there
	 */
	public int checkmove(String dip[][], int x, int y){
		if(dip[x][y]== " "){
		return 1;
		}else{
		return 0;
		}
	}
	//function used to move animals towards food on the given display
	/**
	 * Uses the string array and the array list to analyze where the food is from an entity 
	 * @param  dip	the user inputs a string 2d array to be used as the world
	 * @param aEwor	the user inputs an arraylist of type entities
	 */
	public void scan(ArrayList<LifeForm> aEwor, String dip[][]){

		//varibles used to determine if there is food north south east or west
		int size=0;
		for(int i =0;i<aEwor.size();i++){
			if(aEwor.get(i)==null){
				break;
			}

		//resets the values of n,s,e,w
		if(sizeh>sizev){
			size=sizeh;
		}else{
			size = sizev;
		
		}
		//the if statement above is used to determine if the vertical or horizontal is bigger and sets size equal to the biggest one

		for(int j =0;j<size+1;j++){
			//creates a for loop which will loop to the size of the display
			if(!aEwor.get(i).species.equals("food")){
				//if the species is an animal and not a food then check to see the possible food around the animal
				if(aEwor.get(i).smellFood(anEntity.Direction.North, j)== true ){
					//an if statement to check to see if there is food north of the animal
					if(dip[aEwor.get(i).horizontal-1][aEwor.get(i).verticalPosition].equals(" ")||dip[aEwor.get(i).horizontal-1][aEwor.get(i).verticalPosition].equals("5")){
						//if the above slot above the food is not equal to an animal or obstacle then the animal will move upwards
						aEwor.get(i).horizontal=aEwor.get(i).horizontal-1;
						
						if(dip[aEwor.get(i).horizontal-1][aEwor.get(i).verticalPosition].equals("5")){
							//if the empty slot above the animal is a food then the animal will gain 5 energy
							
							aEwor.get(i).energy = aEwor.get(i).energy +5;
						}
						//breaks from the current for loop so the animal does not move multiple times
						break;
					} 
					}
					if(aEwor.get(i).smellFood(anEntity.Direction.East, j)== true ){
						//an if statement to check to see if there is food east of the animal
						if(dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition+1].equals(" ")||dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition+1].equals("5")){
							//if the above slot right of the food is not equal to an animal or obstacle then the animal will move upwards
							aEwor.get(i).verticalPosition=aEwor.get(i).verticalPosition+1;
							
							if (dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition+1].equals("5")){
								//if the empty slot right of the animal is a food then the animal will gain 5 energy
								
								aEwor.get(i).energy = aEwor.get(i).energy +5;
							
						}
							//breaks from the current for loop so the animal does not move multiple times
							break;
						}
					}
					if(aEwor.get(i).smellFood(anEntity.Direction.South, j)== true ){
						//an if statement to check to see if there is food south of the animal
						if(dip[aEwor.get(i).horizontal+1][aEwor.get(i).verticalPosition].equals(" ")||dip[aEwor.get(i).horizontal+1][aEwor.get(i).verticalPosition].equals("5")){
							//if the above slot below the food is not equal to an animal or obstacle then the animal will move upwardsAE.get(i).horizontal=AE.get(i).horizontal+1;
							
							aEwor.get(i).horizontal=aEwor.get(i).horizontal+1;
							if(dip[aEwor.get(i).horizontal+1][aEwor.get(i).verticalPosition].equals("5")){
								//if the empty slot below the animal is a food then the animal will gain 5 energy
								aEwor.get(i).energy = aEwor.get(i).energy +5;
								
							
						}
							//breaks from the current for loop so the animal does not move multiple times
							break;
						}
					}
					if(aEwor.get(i).smellFood(anEntity.Direction.West, j)==true){
						//an if statement to check to see if there is food west of the animal
						if(dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition-1].equals(" ")||dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition-1].equals("5")){
							//if the above slot left of the food is not equal to an animal or obstacle then the animal will move upwards
							aEwor.get(i).verticalPosition=aEwor.get(i).verticalPosition-1;
							
							if (dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition-1].equals("5")){
								//if the empty slot left of the animal is a food then the animal will gain 5 energy
								aEwor.get(i).energy = aEwor.get(i).energy +5;
								
								
						} 
							//breaks from the current for loop so the animal does not move multiple times
							break;
						}
					}
							
					if(j==size-1){
						Random rand = new Random();
						int ran= rand.nextInt(4)+1;
					if(dip[aEwor.get(i).horizontal-1][aEwor.get(i).verticalPosition].equals(" ")&&ran==1){
						
							//if the above slot above the food is not equal to an animal or obstacle then the animal will move upwards
							aEwor.get(i).horizontal=aEwor.get(i).horizontal-1;
							//aEwor.get(i).setHorizontal(aEwor.get(i).getHorizontal()-1);
						break;
							
						}				
					else if(dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition+1].equals(" ")&&ran==2){
						
								//if the above slot right of the food is not equal to an animal or obstacle then the animal will move upwards
								aEwor.get(i).verticalPosition=aEwor.get(i).verticalPosition+1;
								break;
								

					}else if(dip[aEwor.get(i).horizontal+1][aEwor.get(i).verticalPosition].equals(" ")&&ran==3){
						
								//if the above slot below the food is not equal to an animal or obstacle then the animal will move upwardsAE.get(i).horizontal=AE.get(i).horizontal+1;
								
								aEwor.get(i).horizontal=aEwor.get(i).horizontal+1;
								break;
								
								//breaks from the current for loop so the animal does not move multiple times
					}else if(dip[aEwor.get(i).horizontal][aEwor.get(i).verticalPosition-1].equals(" ")&&ran==4){
						
						//if the above slot left of the food is not equal to an animal or obstacle then the animal will move upwards
						aEwor.get(i).verticalPosition=aEwor.get(i).verticalPosition-1;
						break;
						
						
				}
				
				
			}
			}
				}
			//System.out.println("n = " + n + "e = " + e + "s = " + s + "w = " + w  );
			//a print to check if the entity is moving correctly and to help spot errors in the movements of the animals
		
		}
		
	}
	}

