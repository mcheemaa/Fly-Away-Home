/**
 * Muhammad Ahmed Cheema
 * Frog.java
 **/


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random; 

/**
 * Handles display, movement, and fly eating capabalities for frogs
 */

public class Frog extends Predator
{
    private static final String imgFile = "frog.jpg";

  
    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
    public Frog(GridLocation loc, FlyWorld fw)
    {
	
	location = loc ;
	
	world = fw ;
	try
	    {
		image = ImageIO.read(new File(imgFile));
	    }
	catch (IOException ioe)
	    {
		System.out.println("Unable to read image file: " + imgFile);
		System.exit(0);
	    }
	
	// Puts the frog on the GridLocation so image displays
	location.setCreature(this);
    }


    
    public String toString(){
    	String s = "Frog in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getColumn() + ")";
    	return s;
    }

    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Frogs can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a location or not.<br>
     *
     * @return ArrayList<GridLocation> a list of legal grid locations from<br>
     * the <strong>world</strong> that the frog can move to
     */


    public ArrayList<GridLocation> generateLegalMoves()
    {
	

	int height = world.getNumRows() ;
	int width  = world.getNumCols();
	
	int locR = location.getRow() ;
	int locC = location.getColumn() ;

	ArrayList <GridLocation> legalF = new ArrayList<GridLocation>(); // creates an arraylist 
	
	
	if (world.isValidLoc(locR - 1, locC)){ // gives a location one point above

	    GridLocation newF = world.getLocation (locR -1 , locC) ; 

	    if (newF.hasPredator() == false) { // makes sure that there is no frog at that location

		legalF.add (newF) ; // adds it to the arraylist of moves 
		    	    
	    }
	}
	
	if (world.isValidLoc(locR + 1, locC)){ // gives a location one point below
	    
	    GridLocation newF = world.getLocation (locR  + 1 , locC) ;
	    
	    if (newF.hasPredator() == false) { // makes sure that there is no frog at that location
		
		legalF.add (newF) ;
		
	    }
	}

	if (world.isValidLoc(locR , locC + 1)){ // gives a location one point right

	    GridLocation newF = world.getLocation (locR  , locC + 1) ;
	    
	    if (newF.hasPredator() == false) {  // makes sure that there is no frog at that location

		legalF.add (newF) ;
		
	    }
	}

	if (world.isValidLoc(locR , locC -1  )){ // gives a location one point left

	    GridLocation newF = world.getLocation (locR  , locC - 1) ;

	    if (newF.hasPredator() == false) { // makes sure that there is no frog at that location

		legalF.add (newF) ;
		
	    }
	}
	
	
	return legalF; 
    }
    
  

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
	
	int flyR = world.getFlyLocation().getRow() ;
	int flyC = world.getFlyLocation().getColumn() ;

	int frogR = location.getRow() ;
	int frogC = location.getColumn() ;
	    

	if ( frogR == flyR && frogC == flyC) { // eats if they are on the same box 
	    
	    return true;

	}


	if ( (flyR - 1) == frogR  && frogC == flyC) { // eats if the fly is on top

	    return true;

	}

	if ( (flyR + 1)== frogR  && frogC == flyC) { // eats if the fly is down

	    return true;

	}

	if ( frogR == flyR && (flyC + 1) == frogC ) { // eats if the fly is to the right

	    return true;

	}

	if ( frogR == flyR && (flyC - 1 == frogC )) { // eats if the fly is to the left 

	    return true;

	}
	
	return false; 
    }   
}
