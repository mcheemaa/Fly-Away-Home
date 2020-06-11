/**
 * Muhammad Ahmed Cheema
 * FlyWorld.java
 **/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;

/**
 * Contains information about the world (i.e., the grid of squares)<br>
 * and handles most of the game play work that is NOT GUI specific
 */
public class FlyWorld
{
    private int numRows;
    private int numCols;

    private GridLocation [][] world;

    private GridLocation start;
    private GridLocation goal;

    private ArrayList<Predator> predators;
    
    private Fly mosca;


    /**
     * Reads a file containing information about<br>
     * the grid setup.  Initializes the grid<br>
     * and other instance variables for use by<br>
     * FlyWorldGUI and other pieces of code.
     *
     *@param fileName the file containing the world grid information
     */
    public FlyWorld(String fileName)
    {
	
	File inFile = new File (fileName) ;
	
	Scanner input = null ;

	try {

	    input = new Scanner (inFile) ;
	}
	
	catch (FileNotFoundException fnfe) {
	    
	    System.out.println ("File Not Found !! ") ;
	    
	    System.exit (1) ;
	}


	String number = input.nextLine () ;
	Scanner numScanner = new Scanner (number) ;
	numScanner.useDelimiter (" ") ;

	String numRowws = numScanner.next() ; // gives the first number in the file 
	String numColoumns = numScanner.next() ; // gives the second number in the file 

	numRows = Integer.parseInt(numRowws) ; // converts the string numbers into integers 
	numCols = Integer.parseInt(numColoumns) ; 

	world = new GridLocation [numRows][numCols] ; // creates a world 
	
	predators = new ArrayList<Predator>(); // initializes the predators to a new arraylist

 	int rowNum = -1 ; // sets the row number to -1 so it becomes 0 when the loop runs

	
	while (input.hasNextLine()){
	    
		int colNum = -1 ;
		String line = input.nextLine();
		Scanner lineScanner = new Scanner(line); // goes through every part of the line 
		lineScanner.useDelimiter(""); // separates each thing without anythin in between them
		rowNum = rowNum + 1 ;
		    
		while (lineScanner.hasNext()) {

		    colNum = colNum + 1 ;
		    String obj = lineScanner.next();
		    GridLocation loci = new GridLocation (rowNum, colNum) ;

		    world [rowNum][colNum] = loci ;   
  

		    if ( obj.equals ("s")) {

			 start = world [rowNum][colNum];
			 start.setBackgroundColor(Color.GREEN) ;
			 mosca = new Fly (start, this); // makes new fly and sets it on the start
		    }


		    if ( obj.equals ("h")) {

			goal =  world [rowNum][colNum];
			goal.setBackgroundColor(Color.RED);
		    }

		    
		    if ( obj.equals ("f")){
			
			Predator a = new Frog (loci, this);

			predators.add(a)  ; // adds each frog to the arraylist of predators 	
			
		    }
		    if (obj.equals ("b")) {

			Predator b = new Bird (loci, this);

			predators.add(b)  ; // adds each bird to the arraylist of predators 	
		
		    }

		    if (obj.equals ("a")) {

			Predator c = new Spider (loci, this);

			predators.add(c) ; // adds each spider to the arraylist of predators 
			

		    }
		}
	}
    }
    

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows()
    {
	return numRows;
    }

    /**
     * @return int, the number of columns in the world
     */
    public int getNumCols()
    {
	return numCols;
    }

    /**
     * Deterimes if a specific row/column location is<br>
     * a valid location in the world (i.e., it is not out of bounds)
     *
     * @param r a row
     * @param c a column
     *
     * @return boolean
     */
    public boolean isValidLoc(int r, int c)
    {

	int height = numRows  ;
	int width = numCols  ;

	
	if ( r >= height || c >= width || c < 0 || r < 0 ) {

	    return false ; // checks if any of the two numbers is out of the grid and returns false 
	}

	else {

	    return true ;
	}
    }


    

    /**
     * Returns a specific location based on the given row and column
     *
     * @param r the row
     * @param c the column
     *
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c)
    {

	return world[r][c];
    }

    

    /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation()
    {

	return mosca.getLocation();
	
    }

    

    /**
     * Moves the fly in the given direction (if possible)
     * Checks if the fly got home or was eaten
     *
     * @param direction the direction, N,S,E,W to move
     *
     * @return int, determines the outcome of moving fly<br>
     *              there are three possibilities<br>
     *              1. fly is at home, return ATHOME (defined in FlyWorldGUI)<br>
     *              2. fly is eaten, return EATEN (defined in FlyWorldGUI)<br>
     *              3. fly not at home or eaten, return NOACTION (defined in FlyWorldGUI)
     */


    public int moveFly(int direction)
    {
	

	mosca.update(direction); // calls the update function and gives in the direction 


	if ( mosca.getLocation().equals(goal)) {

	    return FlyWorldGUI.ATHOME;   
	}

	else {

	    return FlyWorldGUI.NOACTION ;
	}
	
    }




    /**
     * Moves all predators. After it moves a predator, checks if it eats fly
     *
     * @return boolean, return true if any predator eats fly, false otherwise
     */
    public boolean movePredators()
    {
	

	int size = predators.size() ;

	Predator a ; 

   
	for (int i = 0 ; i < size ; i++){

	    a = predators.get(i) ; // gives one predator from the arraylist at a time 

	    a.update(); // calls the update method for each preator 	    	    
	    
	    }
	

	for (int i = 0 ; i < size ; i++){

	    a = predators.get(i) ;

	    if (a.eatsFly() == true ){

		return true; // check each predator whether it has eaten a fly or not by calling eatfly

	    }
	}	
	
	return false;
    }
}
		    
			
			      
