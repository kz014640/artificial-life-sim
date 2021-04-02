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
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{
String array[];
	int count1=0;
	int count2=0;
	VBox rtPane;
	int counter=0;
	private static aWorld world;
	private static int sizev=0;
	private static int sizeh=0;
	private static double percentF=0;
	private static double percentO=0;
	private static int max=0;
	private static ArrayList<anEntity> food = null;
	private static ArrayList<LifeForm> AEwor =null;
	private static int count=0;
	private static int foodleft=0;
	String text="";
	String [] code = new String[0];
	
	//varibles used for anInterface class	
	//object used for food in anInterface class
	private static ArrayList<anEntity> obstacles = null;
	 Alert alert = new Alert(AlertType.INFORMATION);
	AnimationTimer timer;
	GraphicsContext gc;
	/**
	 * Uses the string array and the array list to create a world
	 * @param input 	the user inputs a string array
	 * @param AEwor1 	the user inputs an arraylist of type entities

	 */
	
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
	MenuBar setMenu() {
		//object used for obstacles in anInterface class
		//creates an array of strings to be used for the users input
		Scanner scan = new Scanner(System.in);
		/**
		 * Function to set up the menu
		 */
		MenuBar menuBar = new MenuBar();
		Menu mFile = new Menu("File");
		MenuItem mcon = new MenuItem("New Config");
		mcon.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
            	//asks the user to enter a string of text and saves it to code breaking it apart by spaces
            	text = JOptionPane.showInputDialog("enter the string of text");
				code = text.split(" ");
				load(code,AEwor);
            }	
		});
		
		MenuItem mopen = new MenuItem("Open Config");
		mopen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
            	String Filename = JOptionPane.showInputDialog("enter testForJava.txt for normal or enter another file name");
            	if(Filename.equals("")){
            	Filename ="testForJava.txt";
            	System.out.println("hi");
            	}
            	   String line; // creating a varible which is string
				   try // The program is going to run the try block untill there is an error
				   {      
				     BufferedReader in = new BufferedReader( //creating a new varible which is called in with a data tyoe of buffer reader and it is also a constructor
				         new FileReader( Filename ) ); // This is declaring filereader as a new data type and file name is an argument
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
				     System.out.println("Problem reading " + Filename); //this will print a line saying there was a problem with whatever the txt is called
				   }
				   load(code,AEwor);
            }	
		});
		MenuItem msave = new MenuItem("Save");
		msave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
            	 PrintWriter out = null;
 			    BufferedReader user = new BufferedReader(
 			        new InputStreamReader( System.in ) );
 			    String fileName = "";
 			    try
 			    {
 			      fileName = "testForJava.txt";
 			      // create the PrintWriter and enable automatic flushing
 			      out = new PrintWriter( new BufferedWriter( 
 			                new FileWriter( fileName )), true );
 			    }
 			    catch ( IOException iox )
 			    {
 			    	//prints an error if it doesnt work
 			      System.out.println("Error in creating file");
 			      return;
 			    }
 			    // Write out the table.
 			   text = JOptionPane.showInputDialog("Enter the String of text");
 				String[]textArray = text.split(" ");
 				//prompts the user to input a string of text to be saved
 				out.print(textArray[0]);
 				//saves it to the first line of the txt file
 				for(int arrayct=1;arrayct<textArray.length;arrayct++){
 					out.print(" ");
 					out.print(textArray[arrayct]);
 				}
 			    out.close();
            }	
		});
		MenuItem msavea = new MenuItem("Save as");
		msavea.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
            	 PrintWriter out = null;
 			    BufferedReader user = new BufferedReader(
 			        new InputStreamReader( System.in ) );
 			    String fileName = "";
 			    //resets the filename
 			    try
 			    {
 			      fileName = JOptionPane.showInputDialog("enter the Filename");
 			      // create the PrintWriter and enable automatic flushing
 			      out = new PrintWriter( new BufferedWriter( 
 			                new FileWriter( fileName+".txt" )), true );
 			    }
 			    catch ( IOException iox )
 			    {
 			    	//prints out an error if ther is an error while creating the file
 			      System.out.println("Error in creating file");
 			      return;
 			    }
 			      
 			    // Write out the table.
 			 
 			  text = JOptionPane.showInputDialog("enter the string of text");
 			  //asks the user to input a string of text to be saved
 				String[]textArray = text.split(" ");
 				out.print(textArray[0]);
 				//saves the text to the first line of the txt document
 				for(int arrayct=1;arrayct<textArray.length;arrayct++){
 					out.print(" ");
 					out.print(textArray[arrayct]);
 				}
 			   
 			    out.close();
            }	
		});
		
		mFile.getItems().addAll(mcon, mopen, msave, msavea);

		
		Menu mquit = new Menu("quit");
		MenuItem mExit = new MenuItem("Exit");
		mExit.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	System.exit(0);
		    	//exits the program when selected
		    }
		});
		mquit.getItems().addAll(mExit);
		
		Menu mView = new Menu("View");
		MenuItem mDis = new MenuItem("Display config");
		mDis.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	if(text.equals("")){
		    		//a catch to make sure the user chooses a config first
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText("please select a config first");
			    alert.showAndWait();
			    
		    	}else{
				// displays the current config to the user
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText(text);
			    alert.showAndWait();
		    	}
		    }
		});
		MenuItem medit = new MenuItem("Edit Config");
		medit.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	//prompts the user to enter a new config and then saves it to text which is then passed to the load method
		    	text = JOptionPane.showInputDialog("enter the string of text as a new config");
				code = text.split(" ");
				load(code,AEwor);
		    }
		});
		MenuItem mdisl = new MenuItem("Display info about life");
		mdisl.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	if(text.equals("")){
		    		//a catch to make sure the user chooses a config first
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText("please select a config first");
			    alert.showAndWait();
			    
		    	}else{
		    	//an alert box to display information about the life in the world
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText(world.listWorld(world, AEwor));
			    alert.setResizable(true);
			    alert.showAndWait();
		    	}
		    }
		});
		MenuItem mdism = new MenuItem("Display info about map");
		mdism.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	if(text.equals("")){
		    		//a catch to make sure the user chooses a config first
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText("please select a config first");
			    alert.showAndWait();
			    
		    	}else{
		    	//an alert box to display information about the world
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText("the world is "+ code[0]+" by "+code[1]+" and there is: " +percentF+" food and there is: "+percentO+"obstacles");
			    alert.showAndWait();
		    	}
		    }
		});
		mView.getItems().addAll(mDis, medit, mdisl, mdism);
		Menu mEdit= new Menu("Edit");
		MenuItem mmod = new MenuItem("Modify current life form parameters");
		mmod.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
				String speciesName =JOptionPane.showInputDialog("enter the name of the speicies you wish to edit");
				int remove=Integer.parseInt(JOptionPane.showInputDialog("how many do you want to add"));
				//asks the user to enter a species to add and how many of them
				text= text+" "+speciesName+" "+remove;
				//adds both values onto text
				code= text.split(" ");
				max=AEwor.size();
				for(int z=0;z<remove;z++){
					//goes for how many to be added
					anEntity life = new LifeForm();
					life.species = speciesName;
					AEwor.add((LifeForm) life);
					//adds it to the world in a random position
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
		    }
		});
		MenuItem mrem = new MenuItem("remove current life form ");
		mrem.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
				String speciesName =JOptionPane.showInputDialog("enter the name of the speicies you wish to remove");
				int remove=Integer.parseInt(JOptionPane.showInputDialog("how many do you want to add"));
				//takes in the species and how many of them to remove
				code= text.split(" ");
				max = max-remove;
				for(int z=0;z<remove;z++){
					//a for loop which goes for how many times the user inputs
				for(int p=0;p<AEwor.size();p++){
					//a for loop which goes for the size of the array list until it finds the species to be deleted
					if(AEwor.get(p).species.equals(speciesName)){
						//then deletes the species
						AEwor.remove(p);
						break;
					}
				}
				}
		    }
		});
		MenuItem madd = new MenuItem("add a new life form ");
		madd.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
				String speciesName = JOptionPane.showInputDialog("enter the name of the species you wish to add");
				int remove=Integer.parseInt(JOptionPane.showInputDialog("how many do you want to add"));
				//asks the user to enter a species to add and how many of them
				text= text+" "+speciesName+" "+remove;
				//adds both values onto text
				max=AEwor.size();
				for(int z=0;z<remove;z++){
					//a for loop which goes for how many times the user inputs
					anEntity life = new LifeForm();
					life.species = speciesName;
					//sets the species name to whatever the user inputted
					AEwor.add((LifeForm) life);
					//adds the species to the AEwor arraylist
					world.addworldrand(AEwor.get(max),world,world.Display);
					world.Display[AEwor.get(max).horizontal][AEwor.get(max).verticalPosition]= Character.toString(AEwor.get(max).aSymbol);
					//adds it to the world
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
		    }
		});
		mEdit.getItems().addAll(mmod, mrem, madd);
		//adds the options to the menu Edit
		Menu mSimulation= new Menu("Simulation");
		MenuItem mrun = new MenuItem("run");
		mrun.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	if(text.equals("")){
		    		//a catch to make sure the user chooses a config first
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText("please select a config first");
			    alert.showAndWait();
			    
		    	}else{
            	timer.start();
		    	}
            	//starts the animation
		    }
		});
		MenuItem mstar = new MenuItem("pause");
		mstar.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	if(text.equals("")){
		    		//a catch to make sure the user chooses a config first
			    	alert.setTitle("Information Dialog");
				    alert.setHeaderText(null);
				    alert.setContentText("please select a config first");
				    alert.showAndWait();
				    
			    	}else{
		    	 timer.stop();
			    	}
		    	 //pauses the animation
		    }
		});
		MenuItem mreset = new MenuItem("Reset");
		mreset.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	//resets the animation and calls the load method
		    	if(text.equals("")){
		    		//a catch to make sure the user chooses a config first
			    	alert.setTitle("Information Dialog");
				    alert.setHeaderText(null);
				    alert.setContentText("please select a config first");
				    alert.showAndWait();
				    
			    	}else{
		    	code=text.split(" ");
				load(code,AEwor);
			    	}
		    }
		});
	
		mSimulation.getItems().addAll(mrun, mstar, mreset);
		//adds the options to the menu simulation
		
		Menu mHelp= new Menu("Help");
		MenuItem mappl = new MenuItem("Display info about the application");
		mappl.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	//creates an alertbox to inforn the user about the application
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText("this app shows how life works");
			    alert.showAndWait();
		    }
		});
		MenuItem mauth = new MenuItem("Display info about the author");
		mauth.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	//creates an alertbox to inforn the user about the author
		    	alert.setTitle("Information Dialog");
			    alert.setHeaderText(null);
			    alert.setContentText("this was made by Andrew Holloway student number 24014640");
			    alert.showAndWait();
		    }
		});
		mHelp.getItems().addAll(mappl, mauth);
		//adds the options to the menu help
		menuBar.getMenus().addAll(mFile, mView,mEdit, mSimulation,mHelp, mquit);
		//adds all the menus to the menubar
		return menuBar;
	
	}

	public void start(Stage theStage) throws Exception {
		   theStage.setTitle( "World of food" );
		   BorderPane bp = new BorderPane();
		   //creates a boarderpane
		    bp.setPadding(new Insets(10, 20, 10, 20));
		    bp.setTop(setMenu());
		    //sets the menu to the top border pane
		    Group root = new Group();
		    Canvas canvas = new Canvas(800, 800 );
		    //creates a canvas of size 800 by 800
		    root.getChildren().add( canvas ); 
		    //adds the canvas to the group
		    bp.setStyle("-fx-background-color: white");
		    //sets the background colour to white
		    bp.setLeft(root);
		    //creates an array of size 10 of type colour to be used for the colour of entities
		    Color colour[]=new Color[10];
		    for(int i=0;i<10;i++){
		    		colour[i]=Color.color(Math.random(), Math.random(), Math.random());
		    }
		    GraphicsContext gc = canvas.getGraphicsContext2D();
		    
		    final long startNanoTime = System.nanoTime();
		   timer = new AnimationTimer()			// create timer
		    {
		        public void handle(long currentNanoTime) {
		        				// define handle for what do at this time
		        	gc.clearRect(0,  0,  800, 800);
		          //clears the screen
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
		           for(int m = 0; m<obstacles.size();m++){
						//assigns all the obstacles on to the display
						world.Display[obstacles.get(m).horizontal][obstacles.get(m).verticalPosition]= Character.toString(obstacles.get(m).aSymbol);
				
					}
		            for(int m = 0; m<food.size();m++){
						for (int n= 0;n<AEwor.size();n++){
							if(food.get(m).horizontal==AEwor.get(n).horizontal &&AEwor.get(n).verticalPosition ==food.get(m).verticalPosition){
								food.get(m).horizontal = -1000;
								food.get(m).verticalPosition = -1000;
								
								//if an animal eats a food then its position is set to -1000 essentially removing it from the display
							}
						}
					}
		            foodleft=0;
		            for(int m = 0; m<food.size();m++){
						if(food.get(m).horizontal!=-1000){
							foodleft++;
						//if the food has not been set to -10 and been removed then add it to the display
						world.Display[food.get(m).horizontal][food.get(m).verticalPosition]= "5";
						}
						
					}
		        	count=0;
						for(int m =0; m<AEwor.size();m++){
							//assigns all of the entity objects onto the world using their symbol
						world.Display[AEwor.get(count).horizontal][AEwor.get(count).verticalPosition]= Character.toString(AEwor.get(count).aSymbol);
						count++;
						}
						
					//draws the boarder of the world
		            gc.strokeLine(1, 1, (Integer.parseInt(code[0])*20+20), 1);
		            gc.strokeLine(1, 1, 1, (Integer.parseInt(code[1])*20+20));
		            gc.strokeLine((Integer.parseInt(code[0])*20+20), 1, (Integer.parseInt(code[0])*20+20), (Integer.parseInt(code[1])*20+20));
		            gc.strokeLine(1, (Integer.parseInt(code[1])*20+20), (Integer.parseInt(code[0])*20+20), (Integer.parseInt(code[1])*20+20));
		            //draws the objects onto the world in a green colour
		            for(int i =0;i<obstacles.size();i++){
		            	 gc.setFill(Color.GREEN);
		            	gc.fillOval(obstacles.get(i).horizontal*20, obstacles.get(i).verticalPosition*20, 20,20);
		            	 
			           }
		            //draws the food onto the world in a orange colour
		            for(int i =0;i<food.size();i++){
		            	 gc.setFill(Color.ORANGE);
		        		gc.fillOval(food.get(i).horizontal*20, food.get(i).verticalPosition*20, 20,20);
		           }
		            //sets the first entitiy to the world in a red colour and then the rest to a random colour from the colour array
		            	gc.setFill(Color.RED);
		            	int counter=0;
		            	for(int i=0;i<AEwor.size();i++){
		            		//only checks once its past the first varible in the array
		            		if(i>0){
		            			if(!AEwor.get(i-1).species.equals(AEwor.get(i).species)){
		            				gc.setFill(colour[counter]);
		            				counter++;
		            			}
		            		}
				     	   gc.fillOval(AEwor.get(i).horizontal*20, AEwor.get(i).verticalPosition*20, 20,20);  
				     	   //draws a circle onto the world to represent an animal
				        	   }
		            	//pauses the animation for a short time to help the animation
		            try {
		                Thread.sleep(100);
		            } catch(InterruptedException ex) {
		                Thread.currentThread().interrupt();
		            }
		        }
		    };					// start it
		    //creating a vbox
		    rtPane = new VBox();
			rtPane.setAlignment(Pos.CENTER);
			rtPane.setPadding(new Insets(25, 25, 25, 25));	 
			bp.setRight(rtPane);
			//adds the vbox to the boarderpane
		    Scene scene = new Scene(bp, 800, 800);
		    //adds the boarderpane to the scene
	        bp.prefHeightProperty().bind(scene.heightProperty());
	        bp.prefWidthProperty().bind(scene.widthProperty());
		    //timer.start();
		    theStage.setScene(scene);
		    theStage.show();			// show the stage
		    //shows an alert to welcome the user to the program
		    
			alert.setTitle("Information Dialog");
		    alert.setHeaderText(null);
		    alert.setContentText("Welcome to Andrew's project");
		    alert.showAndWait();
		}
	
	public static void main(String[] args) {
		// Just launch the application
        Application.launch(args);
	}
}
