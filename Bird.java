/**
 * Muhammad Ahmed Cheema
 * Bird.java
 **/

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random; 

/**
 * Handles display, movement, and fly eating capabalities for Birds
 */

public class Bird extends Predator
{
    private static final String imgFile = "bird.jpg";

  
    /**
     * Creates a new Bird object.<br>
     */
    
    public Bird(GridLocation loc, FlyWorld fw)
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
	
	// Puts the bird on the GridLocation so image displays
	location.setCreature(this);
    }


    
    public String toString(){
    	String s = "Bird in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getColumn() + ")";
    	return s;
    }

    /**
     * Generates a list of all possible legal moves<br>
     * for a bird.
     */


    public ArrayList<GridLocation> generateLegalMoves()
    {
	

	int height = world.getNumRows() ;
	int width  = world.getNumCols();
	
	int locR = location.getRow() ;
	int locC = location.getColumn() ;

	ArrayList <GridLocation> legalF = new ArrayList<GridLocation>(); // creates an arraylist



	for (int i = 0 ; i < height; i ++) { // gives row number from 0 to the max number of rows 

	    int newR = i ;	    
	    	    
	    for (int b = 0; b < width; b++){ // gives column number for each column in the same row

		int newC = b ;

		GridLocation newF = world.getLocation (newR , newC) ; // gets the grid location for the row and column numbers
		
		if (newF.hasPredator() == false) { // makes sure that there is no frog at that location

		    legalF.add(newF) ;

		}
			
	    }
	    
	}
	return legalF;
	
    }
    

    /**
     * This method helps determine if a bird is in a location<br>
     * where it can eat a fly or not. 
     */
    
    public boolean eatsFly()
    {

	int flyR = world.getFlyLocation().getRow() ;
	int flyC = world.getFlyLocation().getColumn() ;

	int birdR = location.getRow() ;
	int birdC = location.getColumn() ;
	    

	if ( birdR == flyR && birdC == flyC) { // eats if they are on the same grid location 
	    
	    return true;
	    
	}
	
	
	
	return false;
    }   
}
