package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class anInterface {
	public static aWorld world;
	public static int sizev=0;
	public static int sizeh=0;
	public static double percentF=0;
	public static double percentO=0;
	public static int max=0;
	public static ArrayList<anEntity> food = null;
	public static ArrayList<LifeForm> AEwor =null;
	public static int count=0;
	public static int foodleft=0;
	public static int iteration=0;

	//varibles used for anInterface class	
	//object used for food in anInterface class
	public static ArrayList<anEntity> obstacles = null;
	public static void load(String[] input,ArrayList<LifeForm> AEwor1){
		
		percentF=Double.parseDouble(input[2]);
		percentO=Double.parseDouble(input[3]);
		// assigns the percentage of food and obstacles from the code array
		//System.out.println(percentF);
		//System.out.println(percentO);
		
		
		for (int i= 5;i<(input.length);i = i+2){
			
			max = max+ Integer.parseInt(input[i]);
			//used to calculate the max amount of entities which were input by the user
		}
		
		sizeh=Integer.parseInt(input[1]);
		//sets the vertical size equal to the second input of the array
		sizev=Integer.parseInt(input[0]);
		//sets the horizontal size equal to the first input of the array
		percentF= ((Double.parseDouble(input[0])*Double.parseDouble(input[1]))/100)*percentF;
		//calculates the actual amount of food in the display
		percentO= ((Double.parseDouble(input[0])*Double.parseDouble(input[1]))/100)*percentO;
		//calculates the actual amount of obstacles in the display
		food = new ArrayList<anEntity>((int)percentF);
		//creates an array of entities all to be used for food with a size of percentage of food
		obstacles = new ArrayList<anEntity>((int)percentO);
		//creates an array of entities all to be used for obstacle with a size of percentage of obstacle
		//System.out.println((int)percentF);
		//System.out.println((int)percentO);
		
		
		for(int i=0;i<(int)percentF;i++){
		//food[i] = new Food();
		anEntity foodO = new Food();
		foodO.species ="food";
		food.add(foodO);
		//for loop used to initalize all of the entites to be used for food and sets their species name to food
		}
		for(int i=0;i<(int)percentO;i++){
		anEntity ObstacleO = new Obstacle();
		ObstacleO.species ="obstacle";
		obstacles.add(ObstacleO);
		//for loop used to initalize all of the entites to be used for obstacles and sets their species name to obstacle
		
		}
		
		foodleft=(int)percentF;
		
		
		//creating the display
		//creates awolrd object using the inputed sizev, sizeh, and max amount of entiteis by the user
		 world = new aWorld(sizev,sizeh,(sizev*sizeh)-(int)percentF-(int)percentO);
		//creates an array of entities with a size of the amount of entities in the world
		AEwor = new ArrayList<LifeForm>((sizev*sizeh)-(int)percentF-(int)percentO);
		//creates a 2d string array to be used as the display using the vetical and horizontal size+2
		world.Display = new String[world.sizeh+2][world.sizev+2];
		world.Display[0][0]="/";
		world.Display[sizeh+1][sizev+1]="/";
		world.Display[0][sizev+1]="\\";
		world.Display[sizeh+1][0]="\\";
		//the above code wass used to set the coreners of the 2d display array
		for(int i=0;i<world.sizeh;i++){
			world.Display[i+1][0] = "|";
			world.Display[i+1][sizev+1] = "|";
			
			//sets the left and right side of the array to equal a | 
			}
		for(int i=0;i<world.sizev;i++){
			world.Display[0][i+1]="-";
			world.Display[sizeh+1][i+1] = "-";
		
		//sets the bottom and top of the array to equal -	
		}
		for(int i=1;i<world.sizeh+1;i++){
			for(int j=1;j<world.sizev+1;j++){
				world.Display[i][j] =" ";
				//sets the rest of the array equal to an empty slot
			}
		}
		/*
		  		for(int i=0; i<world.max;i++){
		 
			AEwor[i] = new LifeForm();
			//initalizes all of the anentity objects in the AEwor object
		}
		*/
		
	
		//list
		
			for(int m = 0; m<food.size();m++){
				
				//for loop used to randomly place the food in the world
				world.addworldrand(food.get(m),world,world.Display);
				world.Display[food.get(m).horizontal][food.get(m).verticalPosition]= "5";	
				
			}
		
			
			
			for(int m = 0; m<obstacles.size();m++){
				
				//for loop used to randomly place the obstacles in the world
				world.addworldrand(obstacles.get(m),world,world.Display);
				world.Display[obstacles.get(m).horizontal][obstacles.get(m).verticalPosition]= Character.toString(obstacles.get(m).aSymbol);
				
			}
			
			count=0;
			for (int i= 5;i<(input.length);i = i+2){
				//for loop which goes for how many animals the user inputed
				for(int j=0;j<Integer.parseInt(input[i]);j++){
					
					//nested for loop which goes for how many of one species the user inputed
					anEntity life = new LifeForm();
				life.species = input[i-1];
				AEwor.add((LifeForm) life);
				//System.out.println(AEwor.get(count).species);
				//asignes the species name to what the user inputed
				world.addworldrand(AEwor.get(count),world,world.Display);
				//adds the animal in a random location on the 2d display array
				world.Display[AEwor.get(count).horizontal][AEwor.get(count).verticalPosition]= Character.toString(AEwor.get(count).aSymbol);
				count++;
				
				}
				
			}
			
			for(int z=0;z<AEwor.size();z++){
				//for loop used to assign the 2d display array to each entity so when the entity is passed back to anentity class it can still access the 2d world array
				AEwor.get(z).myWorld.sizeh=sizeh;
				AEwor.get(z).myWorld.sizev=sizev;
				 AEwor.get(z).myWorld.Display = new String[world.sizeh+2][world.sizev+2];;
				 for(int j=0;j<world.sizeh+2;j++){
						for(int l=0;l<world.sizev+2;l++){
							 AEwor.get(z).myWorld.Display[j][l]=world.Display[j][l];
								
						}		
			 }
			
			 }
			
			
	}
	void showRobot(int x, int y) {
		/*
		 * function to show robot at position x,y
		 * It puts 'R' into x'th element in displayChars, if y is in the current row
		 */
		System.out.println(x + y);
	
}
	public static void main(String[] args) {
		int choice;
		
		String text="";
	
		//object used for obstacles in anInterface class
		String [] code = new String[0];
		//creates an array of strings to be used for the users input
		Scanner scan = new Scanner(System.in);
		for(int j=0;j>-1;j++){
			System.out.println("1.File");
			System.out.println("2. View");
			System.out.println("3. Edit");
			System.out.println("4. Simulation");
			System.out.println("5. Help");
			choice = scan.nextInt();
			if(choice==1){
		for(int i =0;i>-1;i++){
			System.out.println("File");
			System.out.println("1. New Config");
			System.out.println("2. open Config");
			System.out.println("3. save");
			System.out.println("4. save as");
			System.out.println("5. exit");
			choice = scan.nextInt();
			
			if(choice==1){
				//takes in a string called text and then splits it into sepearte strings using .split() method into the code array
				
				System.out.println("enter the string of text");	
				scan.nextLine();	
				text = scan.nextLine();	
				code = text.split(" ");
				load(code,AEwor);
					
				break;
			}else if(choice==2){
				System.out.println("1. open normal config");
				System.out.println("2. enter a file name");
				choice = scan.nextInt();
				String fileName="";
				if(choice==1){
				  fileName = "testForJava.txt" ; 
				}
				if(choice==2){
					 System.out.println("Enter Filename"); System.out.flush();
					 scan.nextLine();
					 fileName = scan.nextLine();
				}// creating a varible filename creating it as a string in the location
				   String line; // creating a varible which is string
					

				   try // The program is going to run the try block untill there is an error
				   {      
				     BufferedReader in = new BufferedReader( //creating a new varible which is called in with a data tyoe of buffer reader and it is also a constructor
				         new FileReader( fileName  ) ); // This is declaring filereader as a new data type and file name is an argument
				     line = in.readLine(); //Whatever the next line in the file is it will be called line inside the program
				     
						 System.out.println( line ); //this will print the current line
				       //this reads the current line into the program
				       System.out.println("line is" +line);
				
					   code = line.split(" ");
					   text=line;
				     in.close(); //this closes the writer
				    
				   }
				   catch ( IOException iox ) // If there is an error and IOexception is thrown out	
				   {
				     System.out.println("Problem reading " + fileName ); //this will print a line saying there was a problem with whatever the txt is called
				   }
				   load(code,AEwor);
				  
				
					break;
			
			}else if(choice==3){
			    // Get filename and create the file
			    PrintWriter out = null;
			    BufferedReader user = new BufferedReader(
			        new InputStreamReader( System.in ) );
			    String fileName = "";

			   
			    try
			    {
			      fileName = "testForJava.txt";
			      //System.out.print("Enter Filename-->"); System.out.flush();
			      //user.readLine().trim();
			      
			      // create the PrintWriter and enable automatic flushing
			      out = new PrintWriter( new BufferedWriter( 
			                new FileWriter( fileName )), true );
			    }
			    catch ( IOException iox )
			    {
			      System.out.println("Error in creating file");
			      return;
			    }
			      
			    // Write out the table.
			 
			    System.out.println("enter the string of text");	
				scan.nextLine();	
				text = scan.nextLine();	
				String[]textArray = text.split(" ");
				out.print(textArray[0]);
				for(int arrayct=1;arrayct<textArray.length;arrayct++){
					out.print(" ");
					out.print(textArray[arrayct]);
				}
			   
			    out.close();
				break;
			}else if(choice==4){
			    // Get filename and create the file
			    PrintWriter out = null;
			    BufferedReader user = new BufferedReader(
			        new InputStreamReader( System.in ) );
			    String fileName = "";
			    System.out.print("Enter Filename-->"); System.out.flush();
			   
			    try
			    {
			      fileName = user.readLine().trim();
			     
			      
			      
			      // create the PrintWriter and enable automatic flushing
			      out = new PrintWriter( new BufferedWriter( 
			                new FileWriter( fileName )), true );
			    }
			    catch ( IOException iox )
			    {
			      System.out.println("Error in creating file");
			      return;
			    }
			      
			    // Write out the table.
			 
			    System.out.println("enter the string of text");	
				scan.nextLine();	
				text = scan.nextLine();	
				String[]textArray = text.split(" ");
				out.print(textArray[0]);
				for(int arrayct=1;arrayct<textArray.length;arrayct++){
					out.print(" ");
					out.print(textArray[arrayct]);
				}
			   
			    out.close();
				break;
				
			}else if(choice==5){
				break;
				//System.exit(0);
			}
			}
		
			}else if(choice==2){
			for(int i =0;i>-1;i++){
				System.out.println("View");
				System.out.println("1. Display config");
				System.out.println("2. Edit Config");
				System.out.println("3. Display info about life");
				System.out.println("4. Display infor about map");
				
				choice = scan.nextInt();
				if(choice==1){
					for(int p=0;p<code.length;p++){
						System.out.print(code[p]);
						System.out.println(" ");
					}
					System.out.println();
					
					break;
				}else if(choice==2){
					System.out.println("enter the string of text as a new config");	
					scan.nextLine();	
					text = scan.nextLine();	
					code = text.split(" ");
					load(code,AEwor);
					break;
				}else if(choice==3){
					world.listWorld(world, AEwor);
					break;
				}else if(choice==4){
					
					System.out.println("the world is "+ code[0]+" by "+code[1]+" and there is: " +percentF+" food and there is: "+percentO+"obstacles");
					break;
				}
				}
			
			}else if(choice==3){
			for(int i =0;i>-1;i++){
				System.out.println("Edit");
				System.out.println("1. Modify current life form parameters");
				System.out.println("2. remove current life form");
				System.out.println("3. add a new life form");
				choice = scan.nextInt();
				if(choice==1){
					System.out.println("enter the name of the speicies you wish to edit");
					scan.nextLine();
					String speciesName =scan.nextLine();
					System.out.println("how many do you want to add");
					int remove=scan.nextInt();
					text= text+" "+speciesName+" "+remove;
					code= text.split(" ");
					max=AEwor.size();
					for(int z=0;z<remove;z++){
						anEntity life = new LifeForm();
						life.species = speciesName;
						AEwor.add((LifeForm) life);
						world.addworldrand(AEwor.get(max),world,world.Display);
						world.Display[AEwor.get(max).horizontal][AEwor.get(max).verticalPosition]= Character.toString(AEwor.get(max).aSymbol);
						max++;
					}
					for(int z=0;z<AEwor.size();z++){
						//for loop used to assign the 2d display array to each entity so when the entity is passed back to anentity class it can still access the 2d world array
						AEwor.get(z).myWorld.sizeh=sizeh;
						AEwor.get(z).myWorld.sizev=sizev;
						 AEwor.get(z).myWorld.Display = new String[world.sizeh+2][world.sizev+2];;
						 for(int ll=0;ll<world.sizeh+2;ll++){
								for(int l=0;l<world.sizev+2;l++){
									 AEwor.get(z).myWorld.Display[ll][l]=world.Display[ll][l];				
								}		
					 }
					 }
					break;
				}else if(choice==2){
					System.out.println("enter the name of the speicies you wish to remove");
					scan.nextLine();
					String speciesName =scan.nextLine();
					System.out.println("how many do you want to remove");
					int remove=scan.nextInt();
					code= text.split(" ");
					max = max-remove;
					for(int z=0;z<remove;z++){
					for(int p=0;p<AEwor.size();p++){
						if(AEwor.get(p).species.equals(speciesName)){
							AEwor.remove(p);
							break;
						}
					}
					}
					break;
				}else if(choice==3){
					System.out.println("enter the name of the speicies you wish to add");
					scan.nextLine();
					String speciesName =scan.nextLine();
					System.out.println("how many do you want to add");
					int remove=scan.nextInt();
					text= text+" "+speciesName+" "+remove;
					max=AEwor.size();
					System.out.println(text);
					for(int z=0;z<remove;z++){
						anEntity life = new LifeForm();
						life.species = speciesName;
						AEwor.add((LifeForm) life);
						world.addworldrand(AEwor.get(max),world,world.Display);
						world.Display[AEwor.get(max).horizontal][AEwor.get(max).verticalPosition]= Character.toString(AEwor.get(max).aSymbol);
						max++;
					}
					
					for(int z=0;z<AEwor.size();z++){
						//for loop used to assign the 2d display array to each entity so when the entity is passed back to anentity class it can still access the 2d world array
						AEwor.get(z).myWorld.sizeh=sizeh;
						AEwor.get(z).myWorld.sizev=sizev;
						 AEwor.get(z).myWorld.Display = new String[world.sizeh+2][world.sizev+2];;
						 for(int ll=0;ll<world.sizeh+2;ll++){
								for(int l=0;l<world.sizev+2;l++){
									 AEwor.get(z).myWorld.Display[ll][l]=world.Display[ll][l];				
								}		
					 }
					 }
					break;
				}
				}
			
			}else if(choice==4){
				int sim=0;
			for(int i =0;i>-1;i++){
				System.out.println("run");
				System.out.println("1. run");
				System.out.println("2. pause/start");
				System.out.println("3. reset");
				System.out.println("4. Display map at each iteration: ON/OFF   ");
				
				choice = scan.nextInt();
				if(choice==1){
					 //calls the function scan in aworld class to make changes to animals
					 world.scan(AEwor, world.Display);
					 for(int z=0;z<AEwor.size();z++){
						 
						 //a for loop which goes for all the entities in the world
					 for(int m=0;m<world.sizeh+2;m++){
						 //a for loop which goes for the vertical size of the array
							for(int l=0;l<world.sizev+2;l++){
								//a for loop which goes for the vertical size of the array
								
								 AEwor.get(z).myWorld.Display[m][l]=world.Display[m][l];
								 //sets the values in each entities display to the worlds display so they can see the changes
									
							}
				 }
					 }
						for(int m=1;m<world.sizeh+1;m++){
							for(int l=1;l<world.sizev+1;l++){
								//resets all the values in the world display to nothing
								world.Display[m][l] =" ";
							}
						}
						
						for(int m = 0; m<food.size();m++){
							for (int n= 0;n<AEwor.size();n++){
								if(food.get(m).horizontal==AEwor.get(n).horizontal &&AEwor.get(n).verticalPosition ==food.get(m).verticalPosition){
									food.get(m).horizontal = -10;
									food.get(m).verticalPosition = -10;
									
									//if an animal eats a food then its position is set to -10 essentially removing it from the display
								}
							}
						}
						foodleft=0;
						for(int m = 0; m<food.size();m++){
					
							if(food.get(m).horizontal!=-10){
								foodleft++;
							//if the food has not been set to -10 and been removed then add it to the display
							world.Display[food.get(m).horizontal][food.get(m).verticalPosition]= "5";
							}
							
						}
						for(int m = 0; m<obstacles.size();m++){
							//assigns all the obstacles on to the display
							world.Display[obstacles.get(m).horizontal][obstacles.get(m).verticalPosition]= Character.toString(obstacles.get(m).aSymbol);
					
						}
							count=0;
						//for (int m= 5;m<(code.length);m = m+2){
							//for(int n=0;n<Integer.parseInt(code[m]);n++){
							for(int m =0; m<AEwor.size();m++){
						System.out.println(world.Display[AEwor.get(count).horizontal][AEwor.get(count).verticalPosition]);
								//assigns all of the entity objects onto the world using their symbol
							world.Display[AEwor.get(count).horizontal][AEwor.get(count).verticalPosition]= Character.toString(AEwor.get(count).aSymbol);
							count++;
							}
						//}
						for(int m=0;m<world.sizeh+2;m++){
							for(int l=0;l<world.sizev+2;l++){
								//nested for loop used to print the array
								System.out.print(world.Display[m][l]);
							}
							System.out.println();
						}
						
					break;
				}else if(choice==2){
					System.out.println("how many times do you want to simulate the world");
					sim = scan.nextInt();
					for(int counter=0;counter<sim;counter++){
					 world.scan(AEwor, world.Display);
					 for(int z=0;z<AEwor.size();z++){
						 //a for loop which goes for all the entities in the world
					 for(int m=0;m<world.sizeh+2;m++){
						 //a for loop which goes for the vertical size of the array
							for(int l=0;l<world.sizev+2;l++){
								//a for loop which goes for the vertical size of the array
								 AEwor.get(z).myWorld.Display[m][l]=world.Display[m][l];
								 //sets the values in each entities display to the worlds display so they can see the changes
							}		
				 }
					 }
						for(int m=1;m<world.sizeh+1;m++){
							for(int l=1;l<world.sizev+1;l++){
								//resets all the values in the world display to nothing
								world.Display[m][l] =" ";
							}
						}
							if(!"hell0".equals("joe"))
						for(int m = 0; m<food.size();m++){
							for (int n= 0;n<AEwor.size();n++){
								if(food.get(m).horizontal==AEwor.get(n).horizontal &&AEwor.get(n).verticalPosition ==food.get(m).verticalPosition){
									food.get(m).horizontal = -10;
									food.get(m).verticalPosition = -10;
									//if an animal eats a food then its position is set to -10 essentially removing it from the display
								}
							}
						}
						foodleft=0;
						for(int m = 0; m<food.size();m++){
							if(food.get(m).horizontal!=-10){
								foodleft++;
							//if the food has not been set to -10 and been removed then add it to the display
							world.Display[food.get(m).horizontal][food.get(m).verticalPosition]= "5";
							}
							
						}
						for(int m = 0; m<obstacles.size();m++){
							//assigns all the obstacles on to the display
							world.Display[obstacles.get(m).horizontal][obstacles.get(m).verticalPosition]= Character.toString(obstacles.get(m).aSymbol);
					
						}
							count=0;
						for (int m= 5;m<(code.length);m = m+2){
							for(int n=0;n<Integer.parseInt(code[m]);n++){
								//assigns all of the entity objects onto the world using their symbol
							world.Display[AEwor.get(count).horizontal][AEwor.get(count).verticalPosition]= Character.toString(AEwor.get(count).aSymbol);
							count++;
							}
						}
						if(iteration==0){
							for(int m=0;m<world.sizeh+2;m++){
								for(int l=0;l<world.sizev+2;l++){
									//nested for loop used to print the array
									System.out.print(world.Display[m][l]);
								}
								System.out.println();
							}
							}
				}
					
					
					break;
				}else if(choice==3){
					for(int m=0;m<world.sizeh+2;m++){
						for(int l=0;l<world.sizev+2;l++){
							//nested for loop used to print the array
							System.out.print(world.Display[m][l]);
						}
						System.out.println();
					}
					code=text.split(" ");
					System.out.println(text);
					load(code,AEwor);
					break;
				}else if(choice==4){
					if(iteration==0){
						iteration =1;
					}else{
					iteration =0;
					}
					break;
				}
				}
			}else if(choice==5){
			
			for(int i =0;i>-1;i++){
				System.out.println("help");
				System.out.println("1. Display info about application ");
				System.out.println("2. Display info about author");
				choice = scan.nextInt();
				if(choice==1){
					System.out.println("this app shows how life works");
					break;
				}else if(choice==2){
					System.out.println("this was made by Andrew Holloway student number 24014640");
					break;
				}
			}
			}
		}
		}
	
	
	}

