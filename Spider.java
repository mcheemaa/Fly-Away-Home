/**
 * Muhammad Ahmed Cheema
 * Spider.java
 **/



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random; 

/**
 * Handles display, movement, and fly eating capabalities for Spider
 */

public class Spider extends Predator

{
    private static final String imgFile = "spider.jpg";

  
  
    public Spider(GridLocation loc, FlyWorld fw)
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
	
	// Puts the spider on the GridLocation so image displays
	location.setCreature(this);
    }


    
    public String toString(){
    	String s = "Spider in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getColumn() + ")";
    	return s;
    }

    
    /**
     * Generates a list of all possible legal moves 
     */

    
    public ArrayList<GridLocation> generateLegalMoves()
    {
	
	GridLocation flyRC = world.getFlyLocation() ;
	int flyR = flyRC.getRow() ;
	int flyC  = flyRC.getColumn();
	
	int locR = location.getRow() ;
	int locC = location.getColumn() ;

	
	ArrayList <GridLocation> legalF = new ArrayList<GridLocation>(); // creates an arraylist 
	
	
	if ( locR > flyR ){ 
	    
	    GridLocation newF = world.getLocation (locR -1 , locC) ; // moves close in the same column 

	    if (newF.hasPredator() == false) { // makes sure that there is no predator at that location

		legalF.add (newF) ; // adds it to the arraylist of moves 
		    	    
	    }
	}
	
	if ( locR < flyR ){ 
	    
	    GridLocation newF = world.getLocation (locR  + 1 , locC) ;
	    
	    if (newF.hasPredator() == false) { 
		
		legalF.add (newF) ;
		
	    }
	}

	if ( locC > flyC ){ 

	    GridLocation newF = world.getLocation (locR  , locC - 1) ; // moves close in the same row
	    
	    if (newF.hasPredator() == false) { // makes sure that there is no predator at that locatio 

		legalF.add (newF) ;
		
	    }
	}

	if ( locC < flyC ){ 

	    GridLocation newF = world.getLocation (locR  , locC + 1) ;

	    if (newF.hasPredator() == false) {

		legalF.add (newF) ;
		
	    }
	}
	
	
	return legalF; 
    }
    
  

    /**
     * This method helps determine if a spider is in a location
     * where it can eat a fly or not.
     */
    
    
    public boolean eatsFly()
    {
	

	int flyR = world.getFlyLocation().getRow() ;
	int flyC = world.getFlyLocation().getColumn() ;

	int spiderR = location.getRow() ;
	int spiderC = location.getColumn() ;
	    

	if ( spiderR == flyR && spiderC == flyC) { // eats if they are on the same grid location 
	    
	    return true;
	    
	}	
	
	return false; 
    }   
}
